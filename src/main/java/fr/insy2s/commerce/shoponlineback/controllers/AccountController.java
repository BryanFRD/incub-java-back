package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.servicesSansDTO.AccountService_serv;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService_serv accountServiceServ;


    @GetMapping("/all-account")
    public List<Account> listesUser()
    {
        return this.accountServiceServ.all();
    }

    @PostMapping("/add-account")
    public String addUser(@Validated @RequestBody Account utilisateur)
    {
        this.accountServiceServ.add(utilisateur);

        return "user successfully add";
    }

    @PutMapping("/update-account/{id_user}")
    public String updateUser(@Validated @PathVariable Long id_user, @RequestBody Account utilisateur)
    {
        this.accountServiceServ.update(id_user, utilisateur);

        return "User update complete successfully";
    }

    @DeleteMapping("/remove-account/{id_user}")
    public String removeUser(@Validated @PathVariable Long id_user)
    {
        this.accountServiceServ.remove(id_user);

        return "user delete successfully";
    }

    @GetMapping("/get-by-id-account/{id_user}")
    public Account getByIdUser(@Validated @PathVariable Long id_user)
    {
        return  this.accountServiceServ.getById(id_user);
    }
}
