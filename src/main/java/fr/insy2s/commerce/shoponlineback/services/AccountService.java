package fr.insy2s.commerce.shoponlineback.services;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
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
    public void add(Account e) throws GeneralSecurityException {

        e.setRefAccount(UUID.randomUUID().toString());
        e.setResetToken(UUID.randomUUID().toString());
        e.setPassword(this.crypte(e.getPassword()));


        this.accountRepository.save(e);
    }

    @Override
    public Account update(Long id, Account e) {
        return this.accountRepository.findById(id)
                .map(p -> {
                    p.setRefAccount(UUID.randomUUID().toString());
                    if(p.getName() != null)
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
                    if (p.getEmail() != null)
                        p.setEmail(e.getEmail());
                    p.setResetToken(UUID.randomUUID().toString());
                    if (p.getRoles() != null)
                        p.setRoles(e.getRoles());
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

    // Chiffrer le mot password en attendant bcrypte de pring secure

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

