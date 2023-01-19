package fr.insy2s.commerce.shoponlineback.bricole;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.dtos.AccountDTO;
import org.springframework.beans.BeanUtils;

public class MapperBricolage {

    public AccountDTO toAccount(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        BeanUtils.copyProperties(account, accountDTO);
        return accountDTO;
    }
}
