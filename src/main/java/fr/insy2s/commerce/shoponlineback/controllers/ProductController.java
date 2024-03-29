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
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

   @GetMapping("/no-role/all-product-dto")
    public ResponseEntity<Page> allProductDTO(Pageable pageable){

        log.debug("Finding all users");

       return ResponseEntity.ok(this.productService.all(pageable));

    }

    @PostMapping("/no-role/add-product-dto")
    public ResponseEntity<ProductDTO> addProductDTO(@Valid @RequestBody ProductDTO productDTO) {

        try
        {
            return  ResponseEntity.status(HttpStatus.CREATED).body( this.productService.addNew(productDTO));
        }catch (ConstraintViolationException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/no-role/update-product-dto/{idProduct}")
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


    @DeleteMapping("/no-role/remove-product-dto/{idProduct}")
    public ResponseEntity<String> removeProductDTO(@Validated @PathVariable Long idProduct){
        this.productService.remove(idProduct);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/no-role/get-by-id-product/{idProduct}")
    public ResponseEntity<ProductDTO> getByIdProductDTO(@Valid @PathVariable Long idProduct) {

        return this.productService.getById(idProduct)
                .map(productDTO -> new ResponseEntity<>(productDTO, HttpStatus.OK)).
                orElseThrow(() -> new ProductNotFoundException("Product with id " +idProduct+ " was not found"));
    }

    @GetMapping("/no-role/get-by-category-id/{id}")
    public ResponseEntity<Page<ProductDTO>> findProductsByCategoryId(@Valid @PathVariable Long id, Pageable page){
       return ResponseEntity.ok(this.productService.getProductsByCategoryId(id, page));
    }

    @GetMapping("/no-role/get-all-update")
    public ResponseEntity<Page<ProductDTO>> getUpdatePresentAllProduct(Pageable pageable)
    {
        return ResponseEntity.ok(this.productService.updatePresentAllProduct(pageable));
    }
}
