package fr.insy2s.commerce.shoponlineback.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.insy2s.commerce.shoponlineback.beans.Ordered;
import lombok.Data;

import java.util.List;

@Data
public class AddressDTO {

    private Long id;

    private String street;

    private String city;

    private int codePostal;

    private String statut;

    @JsonIgnore
    private List<Ordered> ordersDelivered;

    @JsonIgnore
    private List<Ordered> ordersInvoiced;
}
