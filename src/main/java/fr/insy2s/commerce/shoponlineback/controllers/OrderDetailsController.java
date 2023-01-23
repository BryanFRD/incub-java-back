package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.dtos.OrderDetailsDTO;
import fr.insy2s.commerce.shoponlineback.exceptions.beansexptions.OrderDetailsNotFoundException;
import fr.insy2s.commerce.shoponlineback.services.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api-dto/order-details") // TODO Renomer l'api sans dto
@RequiredArgsConstructor
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @GetMapping("/all-order-details")
    public ResponseEntity<Page<OrderDetailsDTO>> findAllOrderDetailsWithPagination(Pageable pageable){
        return ResponseEntity.ok(this.orderDetailsService.all(pageable));
    }

    @GetMapping("/add-order-details")
    public ResponseEntity<String> addOrderDetailsDTO(@Valid @RequestBody OrderDetailsDTO orderDetailsDTO){
        this.orderDetailsService.add(orderDetailsDTO);
        return ResponseEntity.status(200).body("OrderDetails dto successfully add");
    }

    @GetMapping("/remove-order-details/{idProduct}/{idOrdered}")
    public ResponseEntity<String> updateOrderDetailsDTO(@Valid @PathVariable Long idProduct, @PathVariable Long idOrderDetails, @RequestBody OrderDetailsDTO orderDetailsDTO){
        this.orderDetailsService.update(idProduct, idOrderDetails, orderDetailsDTO);
        return ResponseEntity.status(200).body("OrderDetails dto update successfully");
    }

    @GetMapping("/get-by-id-order-details/{idProduct}/{idOrdered}")
    public ResponseEntity<OrderDetailsDTO> getByIdOrderDetailsDTO(@Valid @PathVariable Long idProduct, @PathVariable Long idOrdered) {
        return this.orderDetailsService.getById(idProduct, idOrdered)
                .map(orderDetail -> {
                    return new ResponseEntity<>(orderDetail, HttpStatus.OK);
                }).orElseThrow(()->new OrderDetailsNotFoundException("OrderDetails not found"));
    }

}
