package fr.insy2s.commerce.services;

import fr.insy2s.commerce.models.Produit;
import fr.insy2s.commerce.models.Utilisateur;
import fr.insy2s.commerce.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository userRepo;

    /**
     * Il renvoie une liste de tous les utilisateur de la base de données
     *
     * @return Une liste de tous les utilisateur dans la base de données.
     */
    public List<Utilisateur> findAll(){
        return this.userRepo.findAll();
    }

    /**
     * Si l'Utilisateur est présent, retournez-le, sinon lancez une erreur 404
     *
     * @param id L'identifiant del'utilisateur à supprimer.
     * @return L'utilisateur avec l'identifiant qui a été transmis.
     */
    public Utilisateur findById(Long id){

        Optional<Utilisateur> optproduit = this.userRepo.findById(id);
        if(optproduit.isPresent()){
            return  optproduit.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Il prend un objet Utilisateur, l'enregistre dans la base de données et renvoie l'objet Utilisateur
     * enregistré.
     *
     * @param newProduct Il s'agit de l'objet transmis par le contrôleur.
     * @return L'objet newProduct est renvoyé.
     */
    public Utilisateur create(Utilisateur newProduct){
        return this.userRepo.save(newProduct);
    }


    /**
     * Si l'utilisateur existe, mettez-le à jour, sinon lancez une exception
     *
     * @param newProduct le nouveau produit à mettre à jour
     */
    public Utilisateur update(Utilisateur newProduct){
        if(!this.userRepo.existsById(newProduct.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"impossible de trouver la Product a mettere a jour ");
        }else{
            return this.userRepo.save(newProduct);
        }
    }

    /**
     * Cette fonction supprime un utilisateur par son identifiant
     *
     * @param id L'identifiant de l'utilisateur à supprimer.
     */

    public void delete(Long id){
        this.userRepo.deleteById(id);
    }



}
