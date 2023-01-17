package fr.insy2s.commerce.shoponlineback.servicesDTO;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.dtos.AccountDTO;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.mappers.MapperAllDTO;
import fr.insy2s.commerce.shoponlineback.mappers.MapperAllDTOImpl;
import fr.insy2s.commerce.shoponlineback.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceDTO  implements Webservices<AccountDTO> {

    private final AccountRepository accountRepository;

    private MapperAllDTO mapperAllDTO = new MapperAllDTOImpl();


    @Override
    public List<AccountDTO> all()
    {
        List<Account> accounts = this.accountRepository.findAll();

        return this.mapperAllDTO.allDTOFromAccount(accounts);
    }

    @Override
    public void add(AccountDTO e) {

        Account account = this.mapperAllDTO.fromAccountDTO(e);

        this.accountRepository.save(account);
    }

    @Override
    public AccountDTO update(Long id, AccountDTO e) {
        return this.mapperAllDTO.fromAccount(this.accountRepository.findById(id)
                .map(p -> {
                    if (e.getName() != null)
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

        Account account = this.accountRepository.findById(id).get();

        if (account != null)
            this.accountRepository.delete(account);
    }

    @Override
    public AccountDTO getById(Long id) {


        return this.mapperAllDTO.fromAccount(this.accountRepository.findById(id).orElseThrow());
    }
}
