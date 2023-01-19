package fr.insy2s.commerce.shoponlineback.controllerDTO;


import fr.insy2s.commerce.shoponlineback.dtos.OrderedDTO;
import fr.insy2s.commerce.shoponlineback.services.OrderedService;
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
    private final OrderedService orderedService;

    @GetMapping("/all-ordered-dto")
    public List<OrderedDTO> allOrderedDTO() {
        return this.orderedService.all();
    }

    @PostMapping("/add-ordered-dto")
    public String addOrderedDTO(@Validated @RequestBody OrderedDTO orderedDTO) {
        this.orderedService.add(orderedDTO);
        return "Ordered dto successfully add";
    }

    @PutMapping("/update-ordered-dto/{idOrdered}")
    public String updateOrderedDTO(@Validated @PathVariable Long idOrdered, @RequestBody OrderedDTO orderedDTO){
        this.orderedService.update(idOrdered, orderedDTO);
        return "Ordered dto update complete successfully";
    }

    @DeleteMapping("/remove-ordered-dto/{idOrdered}")
    public String removeOrderedDTO(@Validated @PathVariable Long idOrdered) {
        this.orderedService.remove(idOrdered);
        return "Ordered dto successfully delete";
    }

    @GetMapping("/get-by-id-ordered/{idOrdered}")
    public OrderedDTO getByIdOrderedDTO (@Validated @PathVariable Long idOrdered) {
        return this.orderedService.getById(idOrdered);
    }







}
