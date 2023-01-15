package fr.insy2s.commerce.shoponlineback.servicesDTO;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.dtos.AccountDTO;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.mappers.MapperForAll;
import fr.insy2s.commerce.shoponlineback.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceDTO implements Webservices<AccountDTO> {

    private final AccountRepository accountRepository;

    private final MapperForAll mapperForAll;

    @Override
    public List<AccountDTO> all()
    {
        List<Account> accounts = this.accountRepository.findAll();

        return accounts.stream().map(this.mapperForAll::fromAccount).collect(Collectors.toList());
    }

    @Override
    public void add(AccountDTO e) {

        Account account = new Account();

       account = this.mapperForAll.fromAccountDTO(e);
        this.accountRepository.save(account);
    }

    @Override
    public AccountDTO update(Long id, AccountDTO e) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public AccountDTO getById(Long id) {
        return null;
    }
}
