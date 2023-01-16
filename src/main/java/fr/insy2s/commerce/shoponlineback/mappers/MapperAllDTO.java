package fr.insy2s.commerce.shoponlineback.mappers;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.beans.Role;
import fr.insy2s.commerce.shoponlineback.dtos.AccountDTO;
import fr.insy2s.commerce.shoponlineback.dtos.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MapperAllDTO {

    // For account user
    AccountDTO fromAccount(Account account);

    Account fromAccountDTO(AccountDTO accountDTO);

    List<AccountDTO> allDTOFromAccount(List<Account> accounts);

    // For Role

    RoleDTO fromRole(Role role);

    Role fromRoleDTO(RoleDTO roleDTO);

    List<RoleDTO> allDTOFromRole(List<Role> roles);
}
