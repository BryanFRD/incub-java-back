package fr.insy2s.commerce.controllers;

import fr.insy2s.commerce.models.Panier;
import fr.insy2s.commerce.repositories.PanierRepository;
import fr.insy2s.commerce.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;

public class PanierController {

    @Autowired
    private PanierRepository repo;


    @PostMapping
    @RolesAllowed({"ROLE_ADMIN", "ROLE_CLIENT"})
    public ResponseEntity<String> create(@RequestBody String panier) {
//        Panier savedPanier = repo.save(panier);
     return ResponseEntity.ok("Vous avez créer votre panier");
//        URI panierURI = URI.create("/panier/" + savedPanier.getId());
//        return ResponseEntity.created(panierURI).body(savedPanier);
    }

    @GetMapping("/public/panier/list")
    public ResponseEntity<String> list(@RequestBody String panier) {
        return ResponseEntity.ok("Vous afficher ici la liste des produits du panier");
//        return repo.findAll();
//        return ("Voici ce que vous avez commandé");
    }
}
