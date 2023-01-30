package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.dtos.ProductDTO;
import fr.insy2s.commerce.shoponlineback.exceptions.beansexptions.ProductNotFoundException;
import fr.insy2s.commerce.shoponlineback.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shopping-online")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

   @GetMapping("/no-role/all-product-dto")
    public ResponseEntity<Page> allProductDTO(Pageable pageable){

        log.debug("Finding all users");

       return ResponseEntity.ok(this.productService.all(pageable));

    }

    @PostMapping("/public/add-product-dto")
    public ResponseEntity<ProductDTO> addProductDTO(@Valid @RequestBody ProductDTO productDTO) {

        try
        {
            this.productService.add(productDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (ConstraintViolationException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-product-dto/{idProduct}")
    public ResponseEntity<ProductDTO> updateProductDTO(@Valid @PathVariable Long idProduct, @RequestBody ProductDTO productDTO) {

       log.info("Updating ordered with id : {}", idProduct);

       try {
           ProductDTO updateProductDTO = this.productService.update(idProduct, productDTO);

           log.info("Product with id : {} updated successfully " ,idProduct);

           return new ResponseEntity<>(updateProductDTO, HttpStatus.OK);
       }catch (ProductNotFoundException exception)
       {
           log.error("Error occured while updating product with id: {}. Error: {}", idProduct, exception.getMessage());
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }


    @DeleteMapping("/public/remove-product-dto/{idProduct}")
    public ResponseEntity<ProductDTO> removeProductDTO(@Validated @PathVariable Long idProduct){
        this.productService.remove(idProduct);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/public/get-by-id-product/{idProduct}")
    public ResponseEntity<ProductDTO> getByIdProductDTO(@Valid @PathVariable Long idProduct) {

        return this.productService.getById(idProduct)
                .map(productDTO -> new ResponseEntity<>(productDTO, HttpStatus.OK)).
                orElseThrow(() -> new ProductNotFoundException("Product with id " +idProduct+ " was not found"));
    }

    @GetMapping("/no-role/get-by-category-name/{categoryName}")
    public ResponseEntity<List<ProductDTO>> findProductsByCategoryName(@Valid @PathVariable String categoryName){
       return ResponseEntity.ok(this.productService.getProductByCategoryName(categoryName));
    }
}
