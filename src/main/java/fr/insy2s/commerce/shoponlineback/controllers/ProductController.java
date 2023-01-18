package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.beans.Product;
import fr.insy2s.commerce.shoponlineback.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all-product")
    public List<Product> allProduct()
    {
        return this.productService.all();
    }

    @PostMapping("/add-product")
    public String addProduct(@Validated @RequestBody Product product)
    {
        this.productService.add(product);

        return "Product successfully add";
    }

    @PutMapping("/update-product/{idProduct}")
    public String updateProduct(@Validated @PathVariable Long idProduct, @RequestBody Product product)
    {
        this.productService.update(idProduct, product);

        return "Product update complete successfully";
    }

    @DeleteMapping("/remove-product/{idProduct}")
    public String removeProduct(@Validated @PathVariable Long idProduct)
    {
        this.productService.remove(idProduct);

        return "Product successfully delete";
    }

    @GetMapping("/get-by-id-product/{idProduct}")
    public Product getByIdProduct(@Validated @PathVariable Long idProduct)
    {
        return this.productService.getById(idProduct);
    }
}
