package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.beans.Ordered;
import fr.insy2s.commerce.shoponlineback.services.OrderedService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordered")
@RequiredArgsConstructor
public class OrderedController {

    private final OrderedService orderedService;

    @GetMapping("/all-ordered")
    public List<Ordered> allOrdered()
    {
        return this.orderedService.all();
    }

    @PostMapping("/add-ordered")
    public String addOrdered(@Validated @RequestBody Ordered ordered)
    {
        this.orderedService.add(ordered);

        return "Ordered successfully add";
    }

    @PutMapping("/update-ordered/{idOrdered}")
    public String updateOrdered(@Validated @PathVariable Long idOrdered, @RequestBody Ordered ordered)
    {
        this.orderedService.update(idOrdered, ordered);

        return "ordered update complete successfully";
    }

    @DeleteMapping("/remove-ordered/{idOrdered}")
    public String removeOrdered(@Validated @PathVariable Long idOrdered)
    {
        this.orderedService.remove(idOrdered);

        return "ordered delete successfully";
    }

    @GetMapping("/get-by-id-ordered/{idOrdered}")
    public Ordered getByIdOrdered(@Validated @PathVariable Long idOrdered)
    {
        return this.orderedService.getById(idOrdered);
    }


}
