package fr.insy2s.commerce.shoponlineback.services;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.bricole.MapperBricolage;
import fr.insy2s.commerce.shoponlineback.dtos.AccountDTO;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.mappers.AccountMapper;
import fr.insy2s.commerce.shoponlineback.mappers.AccountMapperImpl;
import fr.insy2s.commerce.shoponlineback.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService implements Webservices<AccountDTO>{

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper = new AccountMapperImpl();

//    private MapperBricolage mapperBricolage = new MapperBricolage();




    @Override
    public List<AccountDTO> all()
    {
        return null;
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
        if (account.isEmpty()){
            throw new NotFoundException("error.user.notFound");
        }
            this.accountRepository.deleteById(id);

    }

    @Override
    public AccountDTO getById(Long id) {


        return this.accountMapper.fromAccount(this.accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Sorry user id not found")));
    }

//    public Page<AccountDTO> findAll(Pageable pageable){
//        return this.accountRepository.findAll(pageable)
//                .map(this.mapperBricolage::toAccount);
//    }
    public Page<AccountDTO> findAll(Pageable pageable){
        return this.accountRepository.findAll(pageable)
                .map(this.accountMapper::fromAccount);
    }

//    public Page<AccountDTO> findAll(Pageable pageable) {
//        return this.accountRepository.findAll(pageable)
//                .map(accountDto -> this.accountMapper.fromAccount(accountDto));
//    }



}
