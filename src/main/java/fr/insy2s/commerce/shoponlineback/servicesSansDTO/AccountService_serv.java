package fr.insy2s.commerce.shoponlineback.servicesSansDTO;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService_serv{

    private final AccountRepository accountRepository;

    public List<Account> all() {
        return this.accountRepository.findAll();
    }


    public void add(Account e) {
        e.setRefAccount(UUID.randomUUID().toString());
        this.accountRepository.save(e);
    }

    public Account update(Long id, Account e) {
        return this.accountRepository.findById(id)
                .map(p -> {
                    p.setRefAccount(UUID.randomUUID().toString());
                    if(p.getName() != null)
                        p.setName(e.getName());
                    if (p.getFirstName() != null)
                        p.setFirstName(e.getFirstName());
                    if (p.getPassword() != null)
                        p.setPassword(e.getPassword());
                    if (p.getEmail() != null)
                        p.setEmail(e.getEmail());
                    if (p.getRoles() != null)
                        p.setRoles(e.getRoles());
                    return this.accountRepository.save(p);
                }).orElseThrow(()-> new RuntimeException("Attention une exeption detecter sur cet user id"));
    }

    public void remove(Long id) {

        Account utilisateur = this.accountRepository.findById(id).get();

        if (utilisateur != null)
            this.accountRepository.delete(utilisateur);
    }

    public Account getById(Long id) {
        return this.accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found sorry"));
    }
}

