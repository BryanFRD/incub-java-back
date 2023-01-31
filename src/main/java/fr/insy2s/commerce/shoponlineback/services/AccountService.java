package fr.insy2s.commerce.shoponlineback.services;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.beans.Role;
import fr.insy2s.commerce.shoponlineback.dtos.AccountDTO;
import fr.insy2s.commerce.shoponlineback.dtos.RoleDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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



    @Override
    public void add(AccountDTO e) {
        e.setRefAccount(this.uuidService.generateUuid());
        String roleName = "ROLE_CLIENT";
        List<Role> roleList = new ArrayList<>();
        roleList.add(this.roleRepository.findByName(roleName));
        e.setRoles(roleList.stream().map(this.roleMapper::fromRole).collect(Collectors.toList()));
        this.accountRepository.save(this.accountMapper.fromAccountDTO(e));
//        var jwtToken = jwtService.generateToken(this.accountMapper.fromAccountDTO(e));
//        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//        System.out.println(authenticationResponse.getToken());
    }


    public AccountDTO addNew(AccountDTO e) {
        e.setRefAccount(this.uuidService.generateUuid());
        String roleName = "ROLE_CLIENT";
        List<Role> roleList = new ArrayList<>();
        roleList.add(this.roleRepository.findByName(roleName));
        e.setRoles(roleList.stream().map(this.roleMapper::fromRole).collect(Collectors.toList()));
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
                    if(p.getRoles() != null){
                        List<Role> roleList = e.getRoles().stream().map(this.roleMapper::fromRoleDTO).collect(Collectors.toList());
                        p.setRoles(roleList);
                    }
                    return this.accountRepository.save(p);

                }).orElseThrow(() -> new AccountNotFountException("Account with id " +id+ " was not found")));
    }

    @Override
    public void remove(Long id) {

        Optional<Account> account = this.accountRepository.findById(id);
        if (account.isEmpty()){
            throw new NotFoundException("error.user.notFound");
        }
            this.accountRepository.deleteById(id);

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
        return this.accountRepository.findAll(pageable)
                .map(this.accountMapper::fromAccount);
    }


    public void addRoleToUser(String userName, String roleName){
        Optional<Account> account = this.accountRepository.findByEmail(userName);
        Role role = this.roleRepository.findByName(roleName);
        account.get().getRoles().add(role);
        this.accountRepository.save(account.get());
    }




}
