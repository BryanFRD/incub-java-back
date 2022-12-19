package fr.insy2s.commerce.services;

import fr.insy2s.commerce.dtos.AdresseRequest;
import fr.insy2s.commerce.models.Adresse;
import fr.insy2s.commerce.models.Utilisateur;
import fr.insy2s.commerce.repositories.AdresseRepository;
import fr.insy2s.commerce.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AdresseService {

    @Autowired
    private AdresseRepository adresseRepo;
    @Autowired
    private UtilisateurService userService;

    public List<Adresse> findAll() {
        return this.adresseRepo.findAll();
    }

    public Adresse findById(Long id) {
        Optional<Adresse> optAdresse = this.adresseRepo.findById(id);
        if(optAdresse.isPresent()){
        return optAdresse.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    public Adresse create(Adresse newAdresse) {
        return this.adresseRepo.save(newAdresse);
    }

    public Adresse update(Adresse newAdresse) {
        if(!this.adresseRepo.existsById(newAdresse.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "impossible de trouver l'adresse à mettre à jour");
        } else {
            return this.adresseRepo.save(newAdresse);
        }
    }

//       TODO
//    public Adresse addUserToAdress(AdresseRequest addRequest){
//        if(!this.adresseRepo.existsById((long) addRequest.getAdresseId())){
//            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "impossible de mettre l'adresse à jour");
//        } else {
//            Adresse address = this.findById((long) addRequest.getAdresseId());
//            Utilisateur user = this.userService.findById((long) addRequest.getUserId());
//            address.setUtilisateur();
//            return this.adresseRepo.save(address);
//        }
//    }


    public void delete(Long id) {
        this.adresseRepo.deleteById(id);
    }

//    public Adresse addUserToAdress(AdresseRequest addRequest) {
//    }
}
