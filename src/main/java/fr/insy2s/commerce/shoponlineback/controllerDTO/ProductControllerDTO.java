package fr.insy2s.commerce.shoponlineback.controllerDTO;

import fr.insy2s.commerce.shoponlineback.dtos.ProductDTO;
import fr.insy2s.commerce.shoponlineback.servicesDTO.ProductServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-dto")
@RequiredArgsConstructor
public class ProductControllerDTO {

    private final ProductServiceDTO productServiceDTO;

    @GetMapping("/all-product-dto")
    public List<ProductDTO> allProductDTO(){
        return this.productServiceDTO.all();
    }

    @PostMapping("/add-product-dto")
    public String addProductDTO(@Validated @RequestBody ProductDTO productDTO) {

        this.productServiceDTO.add(productDTO);
        return "Product dto successfully add";
    }

    @PutMapping("/update-account-dto/{idProduct}")
    public String updateProductDTO(@Validated @PathVariable Long idProduct, @RequestBody ProductDTO productDTO) {
        this.productServiceDTO.update(idProduct, productDTO);
        return "Product dto update complete successfully";
    }

    @DeleteMapping("/remove-product-dto/{idProduct}")
    public String removeProductDTO(@Validated @PathVariable Long idProduct){
        this.productServiceDTO.remove(idProduct);
        return "Produit dto successfully delete";
    }

    @GetMapping("/get-by-id-product/{idProduct}")
    public ProductDTO getByIdProductDTO(@Validated @PathVariable Long idProduct) {
        return this.productServiceDTO.getById(idProduct);
    }
}
