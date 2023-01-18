package fr.insy2s.commerce.shoponlineback.dtos;

import fr.insy2s.commerce.shoponlineback.beans.Category;
import lombok.Data;

@Data
public class ProductDTO {

    private String refProduct;

    private String name;

    private Double priceTTC;

    private Long productInventory;

    private CategoryDTO category;

}
