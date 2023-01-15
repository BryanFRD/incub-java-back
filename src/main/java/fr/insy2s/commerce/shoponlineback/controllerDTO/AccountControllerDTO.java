package fr.insy2s.commerce.shoponlineback.controllerDTO;

import fr.insy2s.commerce.shoponlineback.dtos.AccountDTO;
import fr.insy2s.commerce.shoponlineback.servicesDTO.AccountServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public String addAccountDTO(@Validated @RequestBody AccountDTO accountDTO)
    {
        this.accountServiceDTO.add(accountDTO);

        return "AccountDTO successfully add";
    }
}
