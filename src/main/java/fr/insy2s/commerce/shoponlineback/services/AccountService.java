package fr.insy2s.commerce.shoponlineback.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.beans.Role;
import fr.insy2s.commerce.shoponlineback.dtos.AccountDTO;
import fr.insy2s.commerce.shoponlineback.enums.Civility;
import fr.insy2s.commerce.shoponlineback.exceptions.beansexptions.AccountNotFountException;
import fr.insy2s.commerce.shoponlineback.exceptions.generic_exception.WebservicesGenericServiceException;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.mappers.AccountMapper;
import fr.insy2s.commerce.shoponlineback.mappers.AccountMapperImpl;
import fr.insy2s.commerce.shoponlineback.mappers.RoleMapper;
import fr.insy2s.commerce.shoponlineback.mappers.RoleMapperImpl;
import fr.insy2s.commerce.shoponlineback.repositories.AccountRepository;
import fr.insy2s.commerce.shoponlineback.repositories.RoleRepository;
import fr.insy2s.commerce.shoponlineback.secure.JwtService;
import fr.insy2s.commerce.shoponlineback.secure.beanresponse.AuthenticationResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService implements Webservices<AccountDTO, WebservicesGenericServiceException>{

    private final AccountRepository accountRepository;

    private final RoleRepository roleRepository;

    private final AccountMapper accountMapper = new AccountMapperImpl();

    private final RoleMapper roleMapper = new RoleMapperImpl();

    private final UuidService uuidService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    
    private final EmailSenderService emailSenderService;
    
    // Validité du token en jours
    private static final int RESET_PASSWORD_TOKEN_VALIDITY = 30;
    
    @Value("${reset-password.url}")
    private String resetPasswordUrl;



    @Override
    public void add(AccountDTO e) {
        e.setRefAccount(this.uuidService.generateUuid());
        String roleName = "ROLE_CLIENT";
        List<Role> roleList = new ArrayList<>();
        roleList.add(this.roleRepository.findByName(roleName));
        e.setRoles(roleList.stream().map(this.roleMapper::fromRole).collect(Collectors.toList()));
        this.accountRepository.save(this.accountMapper.fromAccountDTO(e));
    }


    public AccountDTO addNew(AccountDTO e) {
        e.setRefAccount(this.uuidService.generateUuid());
        String roleName = "ROLE_CLIENT";
        List<Role> roleList = new ArrayList<>();
        roleList.add(this.roleRepository.findByName(roleName));
        e.setPassword(passwordEncoder.encode(e.getPassword()));
        e.setRoles(roleList.stream().map(this.roleMapper::fromRole).collect(Collectors.toList()));
        if (e.getCivility() == null)
            e.setCivility(Civility.OTHER.toString());
        Account account = this.accountRepository.save(this.accountMapper.fromAccountDTO(e));
        return this.accountMapper.fromAccount(account);
    }

    public AuthenticationResponse login(AccountDTO accountDTO)
    {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountDTO.getEmail(), accountDTO.getPassword()));
        
        Optional<Account> account = this.accountRepository.findByEmail(accountDTO.getEmail());
        if (account.isEmpty())
            throw new AccountNotFountException("Account not found");
        var jwtToken = this.jwtService.generateToken(account.get());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AccountDTO update(Long id, AccountDTO e) {
        return this.accountMapper.fromAccount(this.accountRepository.findById(id)
                .map(p -> {
                    if (p.getRefAccount() == null)
                        p.setRefAccount(this.uuidService.generateUuid());
                    if (p.getName() != null)
                        p.setName(e.getName());
                    if (p.getFirstName() != null)
                        p.setFirstName(e.getFirstName());
                    if(p.getEmail() != null)
                        p.setEmail(e.getEmail());
                    if (p.getPassword() != null)
                        p.setPassword(this.passwordEncoder.encode(e.getPassword()));
                    if (p.getResetToken() != null)
                        p.setResetToken(e.getResetToken());
                    if (p.getCivility() != null || p.getCivility() == null)
                        p.setCivility(e.getCivility());
                    if (p.getActive() != null)
                        p.setActive(e.getActive());
                    if(p.getRoles() != null){
                        List<Role> roleList = e.getRoles().stream().map(this.roleMapper::fromRoleDTO).collect(Collectors.toList());
                        p.setRoles(roleList);
                    }
                    return this.accountRepository.save(p);

                }).orElseThrow(() -> new AccountNotFountException("Account with id " +id+ " was not found")));
    }

    public void disable(Long id) {

        Optional<Account> account = this.accountRepository.findById(id);
        if (account.isEmpty()) {
            throw new NotFoundException("error.user.notFound");
        }

        account.get().setActive(false);

        this.accountRepository.save(account.get());

    }

    @Override
    public void remove(Long id)
    {
        Optional<Account> account = this.accountRepository.findById(id);
        if (account.isEmpty())
            throw new AccountNotFountException("account not found");

        account.get().setActive(false);
        account.get().setName("anonymous");
        account.get().setFirstName("anonymous");
        account.get().setEmail("anonymous@anonymous.fr");
        account.get().setCivility(Civility.NONE.toString());

        this.accountRepository.save(account.get());
    }

    @Override
    public Optional<AccountDTO> getById(Long id) {

        return this.accountRepository.findById(id)
                .map(this.accountMapper::fromAccount)
                .map(Optional::of)
                .orElseThrow(() -> new AccountNotFountException("Account with id " +id+ " was not found"));
    }

    @Override
    public Page<AccountDTO> all(Pageable pageable){

        return this.accountRepository.findByActiveIsTrue(pageable)
                .map(this.accountMapper::fromAccount);
    }


    public void addRoleToUser(String userName, String roleName){
        Optional<Account> account = this.accountRepository.findByEmail(userName);
        Role role = this.roleRepository.findByName(roleName);
        account.get().getRoles().add(role);
        this.accountRepository.save(account.get());
    }
   
    /***/
    public void forgetPassword(String email) {
    	Account account = accountRepository.findByEmail(email)
    			.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "email is invalid"));
    	
    	final String token = UUID.randomUUID().toString();
    	account.setResetPasswordToken(token);
    	account.setResetPasswordTokenCreationDate(Timestamp.from(Instant.now()));


    	
    	emailSenderService.sendSimpleEmail(account.getEmail(), "Mot de passe oublié", String.format(resetPasswordUrl, token));
    	
    	accountRepository.save(account);
    } 
    
    /***/
    public void resetPassword(String token, String password) {
    	Account account = accountRepository.findByResetPasswordToken(token)
    			.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "token is invalid"));
    	
    	if(account.getResetPasswordTokenCreationDate() == null) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "reset password token creation date is null");
    	}
    	
    	// vérifier si le token est expiré
    	Instant tokenExpirationDate = account.getResetPasswordTokenCreationDate().toInstant().plus(RESET_PASSWORD_TOKEN_VALIDITY, ChronoUnit.DAYS);
    	if(Instant.now().isAfter(tokenExpirationDate)) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "token is expired");
    	}
    	
    	account.setPassword(passwordEncoder.encode(password));
    	account.setResetPasswordToken(null);
    	account.setResetPasswordTokenCreationDate(null);
    	accountRepository.save(account);
    }
    
    /***/
    public void updatePassword(String oldPassword, String newPassword) {
    	Account authenticatedAccount = (Account)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	// vérifier si l'ancien mot de passe est correct
    	if(!passwordEncoder.matches(oldPassword, authenticatedAccount.getPassword())) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "old password is invalid");
    	}
    	
    	authenticatedAccount.setPassword(passwordEncoder.encode(newPassword));
    	authenticatedAccount.setResetPasswordToken(null);
    	authenticatedAccount.setResetPasswordTokenCreationDate(null);
    	accountRepository.save(authenticatedAccount);
    }
}
