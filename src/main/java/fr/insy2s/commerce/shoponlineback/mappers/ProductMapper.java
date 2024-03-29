package fr.insy2s.commerce.shoponlineback.mappers;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.beans.Product;
import fr.insy2s.commerce.shoponlineback.dtos.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "default", uses = {CategoryMapper.class})
public interface ProductMapper {


    ProductDTO fromProduct(Product product);

    Product fromProductDTO(ProductDTO productDTO);

    // List<Product> findByIsActive(Boolean status);

}
