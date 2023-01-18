package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.beans.Address;
import fr.insy2s.commerce.shoponlineback.services.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adress")
public class AdressController {

    @Autowired
    private final AdressService adressService;

    public AdressController(AdressService adresseService) {
        this.adressService = adresseService;
    }

    @GetMapping("/all-adress")
    public List<Address> allAdress()
    {
        return this.adressService.all();
    }

    @PostMapping("/add-adress")
    public String addAdress(@Validated @RequestBody Address adress)
    {
        this.adressService.add(adress);

        return "adress successfully add";
    }

    @PutMapping("/update-adress/{idAdress}")
    public String updateAdress(@Validated @PathVariable Long idAdress, @RequestBody Address adresse)
    {
        this.adressService.update(idAdress, adresse);

        return "adress update complete successfully";
    }

    @DeleteMapping("/remove-adresse/{idAdress}")
    public String removeAdress(@Validated @PathVariable Long idAdress)
    {
        this.adressService.remove(idAdress);

        return "adress delete successfully";
    }

    @GetMapping("/get-by-id-adress/{idAdress}")
    public Address getByIdAdresse(@Validated @PathVariable Long idAdress)
    {
        return this.adressService.getById(idAdress);
    }
}
