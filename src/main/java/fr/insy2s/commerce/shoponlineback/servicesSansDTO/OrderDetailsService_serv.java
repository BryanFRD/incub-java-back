package fr.insy2s.commerce.shoponlineback.servicesSansDTO;

import fr.insy2s.commerce.shoponlineback.beans.KeyOfOrderDetails;
import fr.insy2s.commerce.shoponlineback.beans.OrderDetails;
import fr.insy2s.commerce.shoponlineback.repositories.OrderDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsService_serv {

    private final OrderDetailsRepository orderDetailsRepository;

    public List<OrderDetails> all()
    {
        return this.orderDetailsRepository.findAll();
    }

    public void add(OrderDetails orderDetails)
    {
        this.orderDetailsRepository.save(orderDetails);
    }

    public OrderDetails update(Long idProduct, Long idOrdered, OrderDetails orderDetails)
    {
        KeyOfOrderDetails keyOfOrderDetails = new KeyOfOrderDetails(idProduct, idOrdered);

        return this.orderDetailsRepository.findById(keyOfOrderDetails)
                .map(p -> {
                    if (p.getAmount() != null)
                        p.setAmount(orderDetails.getAmount());
                    if (p.getPrice() != null)
                        p.setPrice(orderDetails.getPrice());
                    return this.orderDetailsRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("not found this key for order details sorry"));
    }

    public void remove(Long idProduct, Long idOrdered)
    {
        KeyOfOrderDetails keyOfOrderDetails = new KeyOfOrderDetails(idProduct, idOrdered);

        OrderDetails orderDetails = this.orderDetailsRepository.findById(keyOfOrderDetails).get();

        if (orderDetails != null)
            this.orderDetailsRepository.delete(orderDetails);
    }

    public OrderDetails getById(Long idProduct, Long idOrdered)
    {
        KeyOfOrderDetails keyOfOrderDetails = new KeyOfOrderDetails(idProduct, idOrdered);

        return this.orderDetailsRepository.findById(keyOfOrderDetails).orElseThrow(() -> new RuntimeException("not found"));
    }
}
