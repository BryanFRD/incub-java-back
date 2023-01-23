package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.dtos.AccountDTO;
import fr.insy2s.commerce.shoponlineback.exceptions.beansexptions.AccountNotFountException;
import fr.insy2s.commerce.shoponlineback.services.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/api-dto/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    @GetMapping("/all-account")
    public ResponseEntity<Page<AccountDTO>> findAllWithPagination(Pageable pageable){
        return ResponseEntity.ok(this.accountService.all(pageable));
    }

    @PostMapping("/add-account-dto")
    public ResponseEntity<String> addAccountDTO(@Valid @RequestBody AccountDTO accountDTO)
    {
        this.accountService.add(accountDTO);
        return ResponseEntity.status(200).body("Account dto successfully add");
    }

    @PutMapping("/update-account-dto/{idAccount}")
    public ResponseEntity<String> updateAccountDTO(@Valid @PathVariable Long idAccount, @RequestBody AccountDTO accountDTO)
    {
        this.accountService.update(idAccount, accountDTO);
        return ResponseEntity.status(202).body("Account dto update complete successfully");
    }

    @DeleteMapping("/remove-account-dto/{idAccount}")
    public ResponseEntity<String> removeAccountDTO(@Valid @PathVariable Long idAccount)
    {
        this.accountService.remove(idAccount);

        return ResponseEntity.status(202).body("Account dto successfully delete")  ;
    }


    @GetMapping("/get-by-id-account-dto/{idAccount}")
    public ResponseEntity<AccountDTO> getByIdAccountDTO(@Valid @PathVariable Long idAccount)
    {
        return this.accountService.getById(idAccount)
                .map(account -> {
                    log.info("Account with id {} was found", idAccount);
                    return new ResponseEntity<>(account, HttpStatus.OK);
                }).orElseThrow(() ->{
                    log.error("Account with id {} was not found", idAccount);
                    return new AccountNotFountException("Account with id " +idAccount+ " was not found");
                });
    }
}
