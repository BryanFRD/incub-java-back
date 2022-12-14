package fr.insy2s.commerce.controllers;

import fr.insy2s.commerce.models.Contact;
import fr.insy2s.commerce.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;

public class ContactController {

    @Autowired
    private ContactRepository repo;


    @PostMapping("/public/contact")
    public ResponseEntity<Contact> create(@RequestBody Contact contact) {
        Contact savedContact = repo.save(contact);
        URI contactURI = URI.create("/contact/" + savedContact.getId());
        return ResponseEntity.created(contactURI).body(savedContact);
    }

    @GetMapping("/contacts/list")
    @RolesAllowed({"ROLE_ADMIN"})
    public List<Contact> list(@RequestBody Contact contact) {
        return repo.findAll();

    }
}
