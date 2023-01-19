package fr.insy2s.commerce.shoponlineback.controllersSansDTO;

import fr.insy2s.commerce.shoponlineback.beans.Contact;
import fr.insy2s.commerce.shoponlineback.servicesSansDTO.ContactService_serv;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController_control {

    private final ContactService_serv contactServiceServ;

    @GetMapping("/all-contact")
    public List<Contact> allContact()
    {
        return this.contactServiceServ.all();
    }

    @PostMapping("/add-contact")
    public String addContact(@Validated @RequestBody Contact contact)
    {
        this.contactServiceServ.add(contact);

        return "Contact successfully add";
    }

    @PutMapping("/update-contact/{idContact}")
    public String updateContact(@Validated @PathVariable Long idContact, @RequestBody Contact contact)
    {
        this.contactServiceServ.update(idContact, contact);

        return "Contact update complete successfully";
    }

    @DeleteMapping("/remove-contact/{idContact}")
    public String removeContact(@Validated @PathVariable Long idContact)
    {
        this.contactServiceServ.remove(idContact);

        return "Contact successfully delete";
    }

    @GetMapping("/get-by-id-contact/{idContact}")
    public Contact getByIdContact(@Validated @PathVariable Long idContact)
    {
        return this.contactServiceServ.getById(idContact);
    }
}
