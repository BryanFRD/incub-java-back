package fr.insy2s.commerce.shoponlineback.mappers;

import fr.insy2s.commerce.shoponlineback.beans.Product;
import fr.insy2s.commerce.shoponlineback.dtos.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "default", uses = {CategoryMapper.class, PromotionMapper.class})
public interface ProductMapper {

    @Mapping(target = "promotions.product", ignore = true)

    ProductDTO fromProduct(Product product);

    @Mapping(target = "promotions.product", ignore = true)
    Product fromProductDTO(ProductDTO productDTO);

}
