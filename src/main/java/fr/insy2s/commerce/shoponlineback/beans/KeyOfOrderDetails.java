package fr.insy2s.commerce.shoponlineback.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class KeyOfOrderDetails implements Serializable {

    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "id_ordered")
    private Long idOrdered;
}
