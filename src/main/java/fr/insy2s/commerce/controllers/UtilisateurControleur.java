package fr.insy2s.commerce.controllers;

import fr.insy2s.commerce.models.Utilisateur;
import fr.insy2s.commerce.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

public class UtilisateurControleur {


    @RestController
//    @CrossOrigin(originPatterns = "http://localhost:3000/%22)
            @RequestMapping("/api/public/enr")
            public static class UserController {

            @Autowired
            private UtilisateurRepository utilisateurRepository;

            @PostMapping
            public ResponseEntity<Utilisateur> create(@RequestBody Utilisateur user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        Utilisateur savedProduct = utilisateurRepository.save(user);
        URI productURI = URI.create("/users/" + savedProduct.getId());
        return ResponseEntity.created(productURI).body(savedProduct);
    }

    @GetMapping("/users")
    public List<Utilisateur> list() {
        return utilisateurRepository.findAll();
    }
}

}
