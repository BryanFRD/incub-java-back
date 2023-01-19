package fr.insy2s.commerce.shoponlineback.controllerDTO;

import fr.insy2s.commerce.shoponlineback.dtos.AccountDTO;
import fr.insy2s.commerce.shoponlineback.servicesDTO.AccountServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-dto/account")
@RequiredArgsConstructor
public class AccountControllerDTO {

    private final AccountServiceDTO accountServiceDTO;

    @GetMapping("/all-account-dto")
    public List<AccountDTO> allAccountDTO()
    {
        return this.accountServiceDTO.all();
    }

    @PostMapping("/add-account-dto")
    public String addAccountDTO(@Valid @RequestBody AccountDTO accountDTO)
    {
        this.accountServiceDTO.add(accountDTO);

        return "Account dto successfully add";
    }

    @PutMapping("/update-account-dto/{idAccount}")
    public String updateAccountDTO(@Valid @PathVariable Long idAccount, @RequestBody AccountDTO accountDTO)
    {
        this.accountServiceDTO.update(idAccount, accountDTO);

        return "Account dto update complete successfully";
    }

    @DeleteMapping("/remove-account-dto/{idAccount}")
    public String removeAccountDTO(@Valid @PathVariable Long idAccount)
    {
        this.accountServiceDTO.remove(idAccount);

        return "Account dto successfully delete";
    }

    @GetMapping("/get-by-id-account-dto/{idAccount}")
    public AccountDTO getByIdAccountDTO(@Valid @PathVariable Long idAccount)
    {
        return this.accountServiceDTO.getById(idAccount);
    }
}
