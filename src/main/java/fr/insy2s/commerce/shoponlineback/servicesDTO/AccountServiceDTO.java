package fr.insy2s.commerce.shoponlineback.servicesDTO;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.dtos.AccountDTO;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.mappers.AccountMapper;
import fr.insy2s.commerce.shoponlineback.mappers.AccountMapperImpl;
import fr.insy2s.commerce.shoponlineback.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceDTO  implements Webservices<AccountDTO> {

    private final AccountRepository accountRepository;

    private AccountMapper accountMapper = new AccountMapperImpl();


    @Override
    public List<AccountDTO> all()
    {
        return this.accountMapper.allDTOFromAccount(this.accountRepository.findAll());
    }

    @Override
    public void add(AccountDTO e) throws GeneralSecurityException {

        e.setRefAccount(UUID.randomUUID().toString());
        e.setPassword(this.crypte(e.getPassword()));

        this.accountRepository.save(this.accountMapper.fromAccountDTO(e));
    }

    @Override
    public AccountDTO update(Long id, AccountDTO e) {
        return this.accountMapper.fromAccount(this.accountRepository.findById(id)
                .map(p -> {
                    p.setRefAccount(UUID.randomUUID().toString());
                    if (p.getName() != null)
                        p.setName(e.getName());
                    if (p.getFirstName() != null)
                        p.setFirstName(e.getFirstName());
                    if (p.getPassword() != null) {
                        try {
                            p.setPassword(this.crypte(e.getPassword()));
                        } catch (GeneralSecurityException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    if (p.getResetToken() != null)
                        p.setResetToken(e.getResetToken());
                    return this.accountRepository.save(p);

                }).orElseThrow(() -> new RuntimeException("sorry not found this id for account user")));
    }

    @Override
    public void remove(Long id) {

        Optional<Account> account = this.accountRepository.findById(id);
        if (account.isPresent())
            this.accountRepository.deleteById(id);
    }

    @Override
    public AccountDTO getById(Long id) {


        return this.accountMapper.fromAccount(this.accountRepository.findById(id).orElseThrow());
    }

    public String crypte(String password) throws GeneralSecurityException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();

    }
}
