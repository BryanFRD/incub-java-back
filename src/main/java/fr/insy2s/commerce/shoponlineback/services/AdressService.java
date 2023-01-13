package fr.insy2s.commerce.shoponlineback.services;

import fr.insy2s.commerce.shoponlineback.beans.Adress;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.repositories.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdressService implements Webservices<Adress> {

    @Autowired
    private final AdressRepository adressRepository;

    public AdressService(AdressRepository adresseRepository) {
        this.adressRepository = adresseRepository;
    }

    @Override
    public List<Adress> listes() {
        return this.adressRepository.findAll();
    }

    @Override
    public void add(Adress e) {
        this.adressRepository.save(e);
    }

    @Override
    public Adress update(Long id, Adress e) {
        return this.adressRepository.findById(id)
                .map(p -> {
                    if (p.getStreet() != null)
                        p.setStreet(e.getStreet());
                    if (p.getCity() != null)
                        p.setCity(e.getCity());
                    if (p.getCodePostal() != 0)
                        p.setCodePostal(e.getCodePostal());
                    if (p.getStatut() != null)
                        p.setStatut(e.getStatut());
                    return this.adressRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("this id adress not found"));
    }

    @Override
    public void remove(Long id) {

        Adress adresse = this.adressRepository.findById(id).get();

        if (adresse != null)
            this.adressRepository.delete(adresse);
    }

    @Override
    public Adress getById(Long id) {
        return this.adressRepository.findById(id).orElseThrow(() -> new RuntimeException("sorry Adresse not found"));
    }
}
