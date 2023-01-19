package fr.insy2s.commerce.shoponlineback.dtos;

import lombok.Data;

@Data
public class AddressDTO {

    private Long id;

    private String street;

    private String city;

    private int codePostal;

    private String statut;
}
