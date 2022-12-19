package fr.insy2s.commerce.services;

import fr.insy2s.commerce.models.Adresse;
import fr.insy2s.commerce.repositories.AdresseRepository;
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


    public void delete(Long id) {
        this.adresseRepo.deleteById(id);
    }
}
