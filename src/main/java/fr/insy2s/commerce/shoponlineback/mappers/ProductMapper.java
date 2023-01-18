package fr.insy2s.commerce.shoponlineback.mappers;

import fr.insy2s.commerce.shoponlineback.beans.Account;
import fr.insy2s.commerce.shoponlineback.beans.Product;
import fr.insy2s.commerce.shoponlineback.dtos.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "category")
    ProductDTO fromProduct(Product product);

    @Mapping(target = "category")
    Product fromProductDTO(ProductDTO productDTO);

    List<ProductDTO> allDTOFromProduct(List<Product> products);

}
