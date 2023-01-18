package fr.insy2s.commerce.shoponlineback.services;

import fr.insy2s.commerce.shoponlineback.beans.Ordered;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.repositories.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderedService implements Webservices<Ordered> {

    @Autowired
    private final CommandRepository commandRepository;

    public OrderedService(CommandRepository commandeRepository) {
        this.commandRepository = commandeRepository;
    }

    @Override
    public List<Ordered> all() {
        return this.commandRepository.findAll();
    }

    @Override
    public void add(Ordered e) {

        e.setRefOrdered(UUID.randomUUID().toString());
        this.commandRepository.save(e);
    }

    @Override
    public Ordered update(Long id, Ordered e) {
        return this.commandRepository.findById(id)
                .map(p -> {
                    p.setRefOrdered(UUID.randomUUID().toString());
                    if (p.getOrderedDate() != null)
                        p.setOrderedDate(e.getOrderedDate());
                    if (p.getStatut() != null)
                        p.setStatut(e.getStatut());
                    if (p.getDeliveryDate() != null)
                        p.setDeliveryDate(e.getDeliveryDate());
                    return this.commandRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("this id is not found sorrry"));
    }

    @Override
    public void remove(Long id) {

        Ordered commande = this.commandRepository.findById(id).get();

        if (commande != null)
            this.commandRepository.delete(commande);
    }

    @Override
    public Ordered getById(Long id) {
        return this.commandRepository.findById(id).orElseThrow(() -> new RuntimeException("not found sorry"));
    }
}
