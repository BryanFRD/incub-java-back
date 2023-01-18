package fr.insy2s.commerce.shoponlineback.servicesDTO;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.dtos.AccountDTO;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.mappers.AccountMapper;
import fr.insy2s.commerce.shoponlineback.mappers.AccountMapperImpl;
import fr.insy2s.commerce.shoponlineback.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void add(AccountDTO e) {

        e.setRefAccount(UUID.randomUUID().toString());

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
                    if (p.getPassword() != null)
                        p.setPassword(e.getPassword());
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
}
