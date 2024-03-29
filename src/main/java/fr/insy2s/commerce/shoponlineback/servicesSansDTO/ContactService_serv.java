package fr.insy2s.commerce.shoponlineback.servicesSansDTO;

import fr.insy2s.commerce.shoponlineback.beans.Contact;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService_serv {

    private final ContactRepository contactRepository;

    public List<Contact> all() {
        return this.contactRepository.findAll();
    }

    public void add(Contact e) {

        this.contactRepository.save(e);
    }

    public Contact update(Long id, Contact e) {
        return this.contactRepository.findById(id)
                .map(p -> {
                    if (p.getName() != null)
                        p.setName(e.getName());
                    if (p.getEmail() != null)
                        p.setEmail(e.getEmail());
                    if (p.getMessage() != null)
                        p.setMessage(e.getMessage());
                    return this.contactRepository.save(p);
                }).orElseThrow(()-> new RuntimeException("not found this id"));
    }

    public void remove(Long id) {

        Contact contact = this.contactRepository.findById(id).get();

        if (contact != null)
            this.contactRepository.delete(contact);
    }

    public Contact getById(Long id) {
        return this.contactRepository.findById(id).orElseThrow(()-> new RuntimeException("Sorry not found"));
    }
}
