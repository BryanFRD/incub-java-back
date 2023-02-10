package fr.insy2s.commerce.shoponlineback.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.insy2s.commerce.shoponlineback.beans.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private Boolean present;

    private CategoryDTO category;

    private List<PromotionDTO> promotions;

}
