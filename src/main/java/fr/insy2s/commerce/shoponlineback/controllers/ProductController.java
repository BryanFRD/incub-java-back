package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.dtos.ProductDTO;
import fr.insy2s.commerce.shoponlineback.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-dto/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all-product-dto")
    public ResponseEntity<Page<ProductDTO>> allProduct(Pageable pageable){

        return ResponseEntity.ok(this.productService.all(pageable));
    }

    @PostMapping("/add-product-dto")
    public ResponseEntity<String> addProduct(@Valid @RequestBody ProductDTO productDTO) {

        this.productService.add((productDTO));

        return ResponseEntity.status(200).body("Product sucessfully add");
    }

    @PutMapping("/update-account-dto/{idProduct}")
    public ResponseEntity <String> updateProductDTO(@Valid @PathVariable Long idProduct, @RequestBody ProductDTO productDTO) {
        this.productService.update(idProduct, productDTO);
        return ResponseEntity.status(202).body("Product dto update complete successfully")  ;
    }

    @DeleteMapping("/remove-product-dto/{idProduct}")
    public ResponseEntity <String> removeProductDTO(@Valid @PathVariable Long idProduct){
        this.productService.remove(idProduct);
        return ResponseEntity.status(202).body("Produit dto successfully delete");
    }

    @GetMapping("/get-by-id-product/{idProduct}")
    public ResponseEntity<ProductDTO> getByIdProductDTO(@Valid @PathVariable Long idProduct) {
        ProductDTO productDto =  this.productService.getById(idProduct);
        return ResponseEntity.status(200).body(productDto);
    }
}
