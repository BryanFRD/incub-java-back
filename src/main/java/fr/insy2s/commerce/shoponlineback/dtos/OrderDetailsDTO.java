package fr.insy2s.commerce.shoponlineback.dtos;

import fr.insy2s.commerce.shoponlineback.beans.KeyOfOrderDetails;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderDetailsDTO {

    private KeyOfOrderDetails keyOfOrderDetails;

    private Long amount;

    private Double price;

    private ProductDTO product;

    private OrderedDTO ordered;
}
