package fr.insy2s.commerce.shoponlineback.servicesSansDTO;

import fr.insy2s.commerce.shoponlineback.beans.Ordered;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.repositories.OrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderedService_serv {

    @Autowired
    private final OrderedRepository orderedRepository;

    public OrderedService_serv(OrderedRepository commandeRepository) {
        this.orderedRepository = commandeRepository;
    }

    public List<Ordered> all() {
        return this.orderedRepository.findAll();
    }

    public void add(Ordered e) {

        e.setRefOrdered(UUID.randomUUID().toString());
        this.orderedRepository.save(e);
    }

    public Ordered update(Long id, Ordered e) {
        return this.orderedRepository.findById(id)
                .map(p -> {
                    p.setRefOrdered(UUID.randomUUID().toString());
                    if (p.getOrderedDate() != null)
                        p.setOrderedDate(e.getOrderedDate());
                    if (p.getStatut() != null)
                        p.setStatut(e.getStatut());
                    if (p.getDeliveryDate() != null)
                        p.setDeliveryDate(e.getDeliveryDate());
                    return this.orderedRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("this id is not found sorrry"));
    }

    public void remove(Long id) {

        Ordered commande = this.orderedRepository.findById(id).get();

        if (commande != null)
            this.orderedRepository.delete(commande);
    }

    public Ordered getById(Long id) {
        return this.orderedRepository.findById(id).orElseThrow(() -> new RuntimeException("not found sorry"));
    }
}
