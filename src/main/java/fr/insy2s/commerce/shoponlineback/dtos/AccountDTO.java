package fr.insy2s.commerce.shoponlineback.dtos;

import lombok.Data;

import java.util.List;

@Data
public class AccountDTO {

    private Long id;

    private String refAccount;

    private String name;

    private String firstName;

    private String password;

    private String email;

    private String resetToken;

    List<RoleDTO> roles;
}
