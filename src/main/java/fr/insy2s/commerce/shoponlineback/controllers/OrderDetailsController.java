package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.beans.OrderDetails;
import fr.insy2s.commerce.shoponlineback.services.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@RequiredArgsConstructor
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @GetMapping("/all-order-details")
    public List<OrderDetails> allOrderDetails()
    {
        return this.orderDetailsService.all();
    }

    @PostMapping("/add-order-details")
    public String addOrderDetails(@Validated @RequestBody OrderDetails orderDetails)
    {
        this.orderDetailsService.add(orderDetails);

        return "Order details successfully add";
    }

    @PutMapping("/update-order-details/{idProduct}/{idOrdered}")
    public String updateOrderDetails(@Validated @PathVariable Long idProduct, @PathVariable Long idOrdered, @RequestBody OrderDetails orderDetails)
    {
        this.orderDetailsService.update(idProduct, idOrdered, orderDetails);

        return "Order details updater complete successfully";
    }

    @DeleteMapping("/remove-order-details/{idProduct}/{idOrdered}")
    public String removeOrderDetails(@Validated @PathVariable Long idProduct, @PathVariable Long idOrdered)
    {
        this.orderDetailsService.remove(idProduct, idOrdered);

        return "Order detail successfully delete";
    }

    @GetMapping("/get-by-id-order-details/{idProduct}/{idOrdered}")
    public OrderDetails getByIdOrderDetails(@Validated @PathVariable Long idProduct, @PathVariable Long idOrdered)
    {
        return this.orderDetailsService.getById(idProduct, idOrdered);
    }
}
