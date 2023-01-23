package fr.insy2s.commerce.shoponlineback.services;

import fr.insy2s.commerce.shoponlineback.beans.KeyOfOrderDetails;
import fr.insy2s.commerce.shoponlineback.beans.OrderDetails;
import fr.insy2s.commerce.shoponlineback.beans.Ordered;
import fr.insy2s.commerce.shoponlineback.beans.Product;
import fr.insy2s.commerce.shoponlineback.dtos.OrderDetailsDTO;
import fr.insy2s.commerce.shoponlineback.exceptions.beansexptions.OrderDetailsNotFoundException;
import fr.insy2s.commerce.shoponlineback.exceptions.beansexptions.OrderedNotFoundException;
import fr.insy2s.commerce.shoponlineback.exceptions.generic_exception.WebservicesGenericServiceException;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.mappers.*;
import fr.insy2s.commerce.shoponlineback.repositories.OrderDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;

    private final OrderDetailsMapper orderDetailsMapper = new OrderDetailsMapperImpl();

    private final ProductMapper productMapper = new ProductMapperImpl();

    private final OrderedMapper orderedMapper = new OrderedMapperImpl();

//    @Override
    public Page<OrderDetailsDTO> all(Pageable pageable) {
        return this.orderDetailsRepository.findAll(pageable)
                .map( this.orderDetailsMapper::fromOrderDetails);
    }

//    @Override
    public void add(OrderDetailsDTO e) throws WebservicesGenericServiceException {
        OrderDetails orderDetails = this.orderDetailsMapper.fromOrderDetailsDTO(e);
        this.orderDetailsRepository.save(orderDetails);

    }

//    @Override
    public OrderDetailsDTO update(Long idOrdered, Long idProduct,  OrderDetailsDTO e) throws WebservicesGenericServiceException {

        KeyOfOrderDetails keyOfOrderDetails = new KeyOfOrderDetails(idProduct, idOrdered);

        return this.orderDetailsMapper.fromOrderDetails(this.orderDetailsRepository.findById(keyOfOrderDetails)
                .map(p -> {
                    if(p.getAmount() != null)
                        p.setAmount(e.getAmount());
                    if(p.getPrice() != null)
                        p.setPrice(e.getPrice());
                    if(p.getProduct() != null)
                    {
                        Product product = this.productMapper.fromProductDTO(e.getProduct());
                        p.setProduct(product);
                    }
                    if(p.getOrdered() != null)
                    {
                        Ordered ordered = this.orderedMapper.fromOrderedDTO(e.getOrdered());
                        p.setOrdered(ordered);
                    }
                    return this.orderDetailsRepository.save(p);
                }
                ).orElseThrow(()-> new OrderedNotFoundException("OrderDetails witch" + idProduct +","+ idOrdered + "was not found" ))
        );
    }

//    @Override
    public void remove(Long idProduct, Long idOrdered) throws WebservicesGenericServiceException {

        KeyOfOrderDetails keyOfOrderDetails = new KeyOfOrderDetails(idProduct, idOrdered);
        Optional<OrderDetails> orderDetails = this.orderDetailsRepository.findById(keyOfOrderDetails);
        if(orderDetails.isEmpty())
            throw new OrderDetailsNotFoundException("OrderDetails witch" + idProduct +","+ idOrdered + "was not found");
        this.orderDetailsRepository.deleteById(keyOfOrderDetails);

    }



//    @Override
    public Optional<OrderDetailsDTO> getById(Long idProduct, Long idOrdered) throws WebservicesGenericServiceException {
        KeyOfOrderDetails keyOfOrderDetails = new KeyOfOrderDetails(idProduct, idOrdered);
        return this.orderDetailsRepository.findById(keyOfOrderDetails)
                .map(this.orderDetailsMapper::fromOrderDetails)
                .map(Optional::of)
                .orElseThrow(()-> new OrderedNotFoundException("OrderDetails witch" + idProduct +","+ idOrdered + "was not found" ));
    }
}
