package fr.insy2s.commerce.shoponlineback.dtos;

import fr.insy2s.commerce.shoponlineback.beans.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long idProduct;

    private String refProduct;

    private String name;

    private Double priceTTC;

    private Long productInventory;

    private String productDescription;

    private CategoryDTO category;

}
