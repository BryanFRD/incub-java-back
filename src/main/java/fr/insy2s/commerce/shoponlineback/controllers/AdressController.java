package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.beans.Address;
import fr.insy2s.commerce.shoponlineback.servicesSansDTO.AdressService_serv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adress")
public class AdressController {

    @Autowired
    private final AdressService_serv adressServiceServ;

    public AdressController(AdressService_serv adresseService) {
        this.adressServiceServ = adresseService;
    }

    @GetMapping("/all-adress")
    public List<Address> allAdress()
    {
        return this.adressServiceServ.all();
    }

    @PostMapping("/add-adress")
    public String addAdress(@Validated @RequestBody Address adress)
    {
        this.adressServiceServ.add(adress);

        return "adress successfully add";
    }

    @PutMapping("/update-adress/{idAdress}")
    public String updateAdress(@Validated @PathVariable Long idAdress, @RequestBody Address adresse)
    {
        this.adressServiceServ.update(idAdress, adresse);

        return "adress update complete successfully";
    }

    @DeleteMapping("/remove-adresse/{idAdress}")
    public String removeAdress(@Validated @PathVariable Long idAdress)
    {
        this.adressServiceServ.remove(idAdress);

        return "adress delete successfully";
    }

    @GetMapping("/get-by-id-adress/{idAdress}")
    public Address getByIdAdresse(@Validated @PathVariable Long idAdress)
    {
        return this.adressServiceServ.getById(idAdress);
    }
}
