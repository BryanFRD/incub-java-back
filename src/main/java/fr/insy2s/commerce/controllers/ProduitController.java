package fr.insy2s.commerce.controllers;

import fr.insy2s.commerce.models.Produit;
import fr.insy2s.commerce.repositories.ProduitRepository;
import fr.insy2s.commerce.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProduitController {

    // Injecter le ProductService dans le ProductController.
// Injecter la dependance automatiquement lors du besoin
    @Autowired
    private ProduitService productService;

    @Autowired
    private ProduitRepository produitRepo;

        /**
         * Cette fonction est une requête GET qui renvoie une liste de tous les produits
         *
         * @return Une liste de produits
         */
        @GetMapping("/public/produit/liste")
        @ResponseStatus(code= HttpStatus.OK)
        public List<Produit> findAll(){
            return this.productService.findAll();
        }

        /**
         * Il renvoie un produit avec l'identifiant donné
         *
         * @param id L'identifiant du produit à récupérer.
         * @return Un objet Produit
         */
        @GetMapping("/public/produit/{id}")
        @ResponseStatus(code= HttpStatus.OK)
        public Produit findById(@PathVariable Long id){
            return  this.productService.findById(id);
        }

        /**
         * Cette fonction crée un nouveau produit et le renvoie
         *
         * @param newProduct C'est l'objet qui sera créé.
         * @return Le produit qui a été créé.
         */
        @PostMapping("/produit/create")
        @RolesAllowed("ROLE_ADMIN")
        @ResponseStatus(code= HttpStatus.CREATED)
        public Produit create (@RequestBody Produit newProduct){

            return this.productService.create(newProduct);
        }

//    @PostMapping("/produit/create")
//    @RolesAllowed("ROLE_ADMIN")
//    public ResponseEntity<Produit> create(@RequestBody Produit product) {
//        Produit savedProduit = produitRepo.save(product);
//        URI produitURI = URI.create("/produit/" + savedProduit.getId());
//        return ResponseEntity.created(produitURI).body(savedProduit);
//    }

        // Mise à jour d'un produit.
        /**
         * Cette fonction met à jour un produit dans la base de données
         * verification si  l'id renseigné egale aà l'id de l'objet recuperer
         * si oui on met à jour le produit dans la base de données si non
         * on retourne une exception
         * @param Product L'objet qui sera mis à jour.
         * @param id L'identifiant du produit à mettre à jour.
         * @return L'objet Product est renvoyé.
         */
        @PostMapping("/produit/update/{id}")
        @RolesAllowed("ROLE_ADMIN")
        @ResponseStatus(code= HttpStatus.ACCEPTED)
        public Produit update (@RequestBody Produit Product,@PathVariable Long id){
            if (!id.equals(Product.getId())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"mauvaise Product a mettre a jour");
            }
            return this.productService.update(Product);
        }

        /**
         * Cette fonction supprime un produit de la base de données
         *
         * @param id L'identifiant du produit à supprimer.
         * @return Le produit qui a été supprimé.
         */
        @DeleteMapping("/produit/delete/{id}")
        @RolesAllowed("ROLE_ADMIN")
        @ResponseStatus(code= HttpStatus.ACCEPTED)
        public Produit delete (@PathVariable Long id){
            return this.productService.delete(id);
        }

}
