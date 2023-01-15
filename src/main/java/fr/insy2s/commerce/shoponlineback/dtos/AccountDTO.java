package fr.insy2s.commerce.shoponlineback.dtos;

import fr.insy2s.commerce.shoponlineback.beans.Role;
import lombok.Data;

@Data
public class AccountDTO {

    private Long id;

    private String name;

    private String firstName;

    private String password;

    private String email;

    private String resetToken;

    private Role role;
}
