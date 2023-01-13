package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.beans.User;
import fr.insy2s.commerce.shoponlineback.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;


    @GetMapping("/listes-user")
    public List<User> listesUser()
    {
        return this.accountService.listes();
    }

    @PostMapping("/add-user")
    public String addUser(@Validated @RequestBody User utilisateur)
    {
        this.accountService.add(utilisateur);

        return "user ajouter avec succès";
    }

    @PutMapping("/update-user/{id_user}")
    public String updateUser(@Validated @PathVariable Long id_user, @RequestBody User utilisateur)
    {
        this.accountService.update(id_user, utilisateur);

        return "Mise à jour de l'utilisateur avec succès";
    }

    @DeleteMapping("/remove-user/{id_user}")
    public String removeUser(@Validated @PathVariable Long id_user)
    {
        this.accountService.remove(id_user);

        return "Utilisateur supprimer avec succès";
    }

    @GetMapping("/get-by-id-user/{id_user}")
    public User getByIdUser(@Validated @PathVariable Long id_user)
    {
        return  this.accountService.getById(id_user);
    }
}
