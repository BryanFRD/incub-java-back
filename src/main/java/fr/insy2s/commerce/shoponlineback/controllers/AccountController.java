package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;


    @GetMapping("/all-account")
    public List<Account> listesUser()
    {
        return this.accountService.all();
    }

    @PostMapping("/add-account")
    public String addUser(@Validated @RequestBody Account utilisateur)
    {
        this.accountService.add(utilisateur);

        return "user successfully add";
    }

    @PutMapping("/update-account/{id_user}")
    public String updateUser(@Validated @PathVariable Long id_user, @RequestBody Account utilisateur)
    {
        this.accountService.update(id_user, utilisateur);

        return "User update complete successfully";
    }

    @DeleteMapping("/remove-account/{id_user}")
    public String removeUser(@Validated @PathVariable Long id_user)
    {
        this.accountService.remove(id_user);

        return "user delete successfully";
    }

    @GetMapping("/get-by-id-account/{id_user}")
    public Account getByIdUser(@Validated @PathVariable Long id_user)
    {
        return  this.accountService.getById(id_user);
    }
}
