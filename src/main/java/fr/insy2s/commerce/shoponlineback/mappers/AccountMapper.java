package fr.insy2s.commerce.shoponlineback.mappers;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.dtos.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    // For account user
    @Mapping(target = "roles")
    AccountDTO fromAccount(Account account);

    @Mapping(target = "roles")
    Account fromAccountDTO(AccountDTO accountDTO);

    List<AccountDTO> allDTOFromAccount(List<Account> accounts);

}
