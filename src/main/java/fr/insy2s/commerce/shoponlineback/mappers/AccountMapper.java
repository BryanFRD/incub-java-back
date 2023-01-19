package fr.insy2s.commerce.shoponlineback.mappers;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.dtos.AccountDTO;
import org.mapstruct.Mapper;



import java.util.List;

@Mapper(componentModel = "spring"/*,  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE*/)
public interface AccountMapper {

    // For account user
//    @Mapping(target = "roles")
    AccountDTO fromAccount(Account account);

//    @Mapping(target = "roles", ignore = true)
    Account fromAccountDTO(AccountDTO accountDTO);

    List<AccountDTO> allDTOFromAccount(List<Account> accounts);

//    Page<AccountDTO> allDTOFromAccount(Page<Account> accounts);



}
