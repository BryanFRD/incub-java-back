package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.beans.Contact;
import fr.insy2s.commerce.shoponlineback.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/all-contact")
    public List<Contact> allContact()
    {
        return this.contactService.all();
    }

    @PostMapping("/add-contact")
    public String addContact(@Validated @RequestBody Contact contact)
    {
        this.contactService.add(contact);

        return "Contact successfully add";
    }

    @PutMapping("/update-contact/{idContact}")
    public String updateContact(@Validated @PathVariable Long idContact, @RequestBody Contact contact)
    {
        this.contactService.update(idContact, contact);

        return "Contact update complete successfully";
    }

    @DeleteMapping("/remove-contact/{idContact}")
    public String removeContact(@Validated @PathVariable Long idContact)
    {
        this.contactService.remove(idContact);

        return "Contact successfully delete";
    }

    @GetMapping("/get-by-id-contact/{idContact}")
    public Contact getByIdContact(@Validated @PathVariable Long idContact)
    {
        return this.contactService.getById(idContact);
    }
}
