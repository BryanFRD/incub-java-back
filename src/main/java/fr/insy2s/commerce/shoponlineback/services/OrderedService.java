package fr.insy2s.commerce.shoponlineback.services;

import fr.insy2s.commerce.shoponlineback.beans.Ordered;
import fr.insy2s.commerce.shoponlineback.dtos.OrderedDTO;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.mappers.OrderedMapper;
import fr.insy2s.commerce.shoponlineback.mappers.OrderedMapperImpl;
import fr.insy2s.commerce.shoponlineback.repositories.OrderedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderedService implements Webservices<OrderedDTO> {

    private final OrderedRepository orderedRepository;
    private final OrderedMapper orderedMapper = new OrderedMapperImpl();

    @Override
    public List<OrderedDTO> all() {
      List<Ordered> ordereds = this.orderedRepository.findAll();
      return this.orderedMapper.allDTOfromOrdered(ordereds);
    }

    @Override
    public void add(OrderedDTO e) {
        e.setRefOrdered(UUID.randomUUID().toString());
        Ordered ordered = this.orderedMapper.fromOrderedDTO(e);
        this.orderedRepository.save(ordered);
    }

    @Override
    public OrderedDTO update(Long id, OrderedDTO e) {
        return this.orderedMapper.fromOrdered(this.orderedRepository.findById(id)
                .map(p-> {
                    p.setRefOrdered(UUID.randomUUID().toString());
                    if(p.getOrderedDate() != null){
                        p.setOrderedDate(e.getOrderedDate());
                    }
                    if (p.getStatut() != null){
                        p.setStatut(e.getStatut());
                    }
                    if(p.getDeliveryDate() != null){
                        p.setDeliveryDate(e.getDeliveryDate());
                    }
                    return this.orderedRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Sorry not id for ordered")));
    }

    @Override
    public void remove(Long id) {
        Ordered ordered = this.orderedRepository.findById(id).get();
        if( ordered != null)
            this.orderedRepository.delete(ordered);

    }

    @Override
    public OrderedDTO getById(Long id) {
        return this.orderedMapper.fromOrdered(this.orderedRepository.findById(id).orElseThrow(()->new RuntimeException("Sorry ordered id not found")));
    }
}
