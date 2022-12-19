package fr.insy2s.commerce.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordRequest {


    private String email;

    private  String newPassword;
}
