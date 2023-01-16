package fr.insy2s.commerce.shoponlineback.mappers;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.beans.Adress;
import fr.insy2s.commerce.shoponlineback.beans.Role;
import fr.insy2s.commerce.shoponlineback.dtos.AccountDTO;
import fr.insy2s.commerce.shoponlineback.dtos.AdressDTO;
import fr.insy2s.commerce.shoponlineback.dtos.RoleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MapperForAll {

    public AccountDTO fromAccount(Account account)
    {
        AccountDTO accountDTO = new AccountDTO();

        BeanUtils.copyProperties(account, accountDTO);
        return accountDTO;
    }

    public Account fromAccountDTO(AccountDTO accountDTO)
    {
        Account account = new Account();

        BeanUtils.copyProperties(accountDTO, account);
        return account;
    }

    public RoleDTO fromRole(Role role)
    {
        RoleDTO roleDTO = new RoleDTO();

        BeanUtils.copyProperties(role, roleDTO);

        return roleDTO;
    }

    public Role fromRoleDTO(RoleDTO roleDTO)
    {
        Role role = new Role();

        BeanUtils.copyProperties(roleDTO, role);
        return role;
    }

    public AdressDTO fromAdress(Adress adress)
    {
        AdressDTO adressDTO = new AdressDTO();
        BeanUtils.copyProperties(adress, adressDTO);
        return adressDTO;
    }

    public Adress fromAdressDTO(AdressDTO adressDTO)
    {
        Adress adress = new Adress();
        BeanUtils.copyProperties(adressDTO, adress);
        return adress;
    }
}
