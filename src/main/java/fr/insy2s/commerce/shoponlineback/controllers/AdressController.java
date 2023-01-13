package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.beans.Adress;
import fr.insy2s.commerce.shoponlineback.services.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adresse")
public class AdressController {

    @Autowired
    private final AdressService adressService;

    public AdressController(AdressService adresseService) {
        this.adressService = adresseService;
    }

    @GetMapping("/listesAdresse")
    public List<Adress> listes_adresse()
    {
        return this.adressService.listes();
    }

    @PostMapping("/addAdresse")
    public String add_adresse(@Validated @RequestBody Adress adresse)
    {
        this.adressService.add(adresse);

        return "adresse ajouter avec succès";
    }

    @PutMapping("/updateAdresse/{ref_adresse}")
    public String update_adresse(@Validated @PathVariable Long ref_adresse, @RequestBody Adress adresse)
    {
        this.adressService.update(ref_adresse, adresse);

        return "adresse modifier avec succèss";
    }

    @DeleteMapping("/removeAdresse/{ref_adresse}")
    public String remove_adresse(@Validated @PathVariable Long ref_adresse)
    {
        this.adressService.remove(ref_adresse);

        return "adresse supprimer avec succès";
    }

    @GetMapping("/getByIdAdresse/{ref_adresse}")
    public Adress getById_adresse(@Validated @PathVariable Long ref_adresse)
    {
        return this.adressService.getById(ref_adresse);
    }
}
