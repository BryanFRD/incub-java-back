package fr.insy2s.commerce.shoponlineback.services;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService implements Webservices<Account> {

    private final AccountRepository accountRepository;

    @Override
    public List<Account> all() {
        return this.accountRepository.findAll();
    }

    @Override
    public void add(Account e) {

        e.setResetToken(UUID.randomUUID().toString());


        this.accountRepository.save(e);
    }

    @Override
    public Account update(Long id, Account e) {
        return this.accountRepository.findById(id)
                .map(p -> {
                    if(p.getName() != null)
                        p.setName(e.getName());
                    if (p.getFirstName() != null)
                        p.setFirstName(e.getFirstName());
                    if (p.getPassword() != null)
                        p.setPassword(e.getPassword());
                    if (p.getEmail() != null)
                        p.setEmail(e.getEmail());
                    p.setResetToken(UUID.randomUUID().toString());
                    if (p.getRole() != null)
                        p.setRole(e.getRole());
                    return this.accountRepository.save(p);
                }).orElseThrow(()-> new RuntimeException("Attention une exeption detecter sur cet user id"));
    }

    @Override
    public void remove(Long id) {

        Account utilisateur = this.accountRepository.findById(id).get();

        if (utilisateur != null)
            this.accountRepository.delete(utilisateur);
    }

    @Override
    public Account getById(Long id) {
        return this.accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found sorry"));
    }
}

