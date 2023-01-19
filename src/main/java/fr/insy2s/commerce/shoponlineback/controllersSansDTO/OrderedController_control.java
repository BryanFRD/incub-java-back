package fr.insy2s.commerce.shoponlineback.controllersSansDTO;

import fr.insy2s.commerce.shoponlineback.beans.Ordered;
import fr.insy2s.commerce.shoponlineback.servicesSansDTO.OrderedService_serv;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordered")
@RequiredArgsConstructor
public class OrderedController_control {

    private final OrderedService_serv orderedServiceServ;

    @GetMapping("/all-ordered")
    public List<Ordered> allOrdered()
    {
        return this.orderedServiceServ.all();
    }

    @PostMapping("/add-ordered")
    public String addOrdered(@Validated @RequestBody Ordered ordered)
    {
        this.orderedServiceServ.add(ordered);

        return "Ordered successfully add";
    }

    @PutMapping("/update-ordered/{idOrdered}")
    public String updateOrdered(@Validated @PathVariable Long idOrdered, @RequestBody Ordered ordered)
    {
        this.orderedServiceServ.update(idOrdered, ordered);

        return "ordered update complete successfully";
    }

    @DeleteMapping("/remove-ordered/{idOrdered}")
    public String removeOrdered(@Validated @PathVariable Long idOrdered)
    {
        this.orderedServiceServ.remove(idOrdered);

        return "ordered delete successfully";
    }

    @GetMapping("/get-by-id-ordered/{idOrdered}")
    public Ordered getByIdOrdered(@Validated @PathVariable Long idOrdered)
    {
        return this.orderedServiceServ.getById(idOrdered);
    }


}
