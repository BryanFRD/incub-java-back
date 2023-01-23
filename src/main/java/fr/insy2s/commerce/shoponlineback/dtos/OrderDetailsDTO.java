package fr.insy2s.commerce.shoponlineback.dtos;

import fr.insy2s.commerce.shoponlineback.beans.KeyOfOrderDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDTO {

    private KeyOfOrderDetails keyOfOrderDetails;


    private Long amount;


    private Double price;


    private ProductDTO product;


    private OrderedDTO ordered;
}
