package fr.insy2s.commerce.shoponlineback.controllerDTO;


import fr.insy2s.commerce.shoponlineback.dtos.OrderedDTO;
import fr.insy2s.commerce.shoponlineback.servicesDTO.OrderedServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-dto/ordered")
@RequiredArgsConstructor
public class OrderedControllerDTO {

    @Autowired
    private final OrderedServiceDTO orderedServiceDTO;

    @GetMapping("/all-ordered-dto")
    public List<OrderedDTO> allOrderedDTO() {
        return this.orderedServiceDTO.all();
    }

    @PostMapping("/add-ordered-dto")
    public String addOrderedDTO(@Validated @RequestBody OrderedDTO orderedDTO) {
        this.orderedServiceDTO.add(orderedDTO);
        return "Ordered dto successfully add";
    }

    @PutMapping("/update-ordered-dto/{idOrdered}")
    public String updateOrderedDTO(@Validated @PathVariable Long idOrdered, @RequestBody OrderedDTO orderedDTO){
        this.orderedServiceDTO.update(idOrdered, orderedDTO);
        return "Ordered dto update complete successfully";
    }

    @DeleteMapping("/remove-ordered-dto/{idOrdered}")
    public String removeOrderedDTO(@Validated @PathVariable Long idOrdered) {
        this.orderedServiceDTO.remove(idOrdered);
        return "Ordered dto successfully delete";
    }

    @GetMapping("/get-by-id-ordered/{idOrdered}")
    public OrderedDTO getByIdOrderedDTO (@Validated @PathVariable Long idOrdered) {
        return this.orderedServiceDTO.getById(idOrdered);
    }







}
