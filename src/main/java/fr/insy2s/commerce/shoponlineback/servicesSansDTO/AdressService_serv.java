package fr.insy2s.commerce.shoponlineback.servicesSansDTO;

import fr.insy2s.commerce.shoponlineback.beans.Address;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.repositories.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdressService_serv implements Webservices<Address> {

    @Autowired
    private final AdressRepository adressRepository;

    public AdressService_serv(AdressRepository adresseRepository) {
        this.adressRepository = adresseRepository;
    }

    @Override
    public List<Address> all() {
        return this.adressRepository.findAll();
    }

    @Override
    public void add(Address e) {
        this.adressRepository.save(e);
    }

    @Override
    public Address update(Long id, Address e) {
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

        Address adresse = this.adressRepository.findById(id).get();

        if (adresse != null)
            this.adressRepository.delete(adresse);
    }

    @Override
    public Address getById(Long id) {
        return this.adressRepository.findById(id).orElseThrow(() -> new RuntimeException("sorry Adresse not found"));
    }
}
