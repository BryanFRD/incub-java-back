package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.beans.Product;
import fr.insy2s.commerce.shoponlineback.servicesSansDTO.ProductService_serv;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService_serv productServiceServ;

    @GetMapping("/all-product")
    public List<Product> allProduct()
    {
        return this.productServiceServ.all();
    }

    @PostMapping("/add-product")
    public String addProduct(@Validated @RequestBody Product product)
    {
        this.productServiceServ.add(product);

        return "Product successfully add";
    }

    @PutMapping("/update-product/{idProduct}")
    public String updateProduct(@Validated @PathVariable Long idProduct, @RequestBody Product product)
    {
        this.productServiceServ.update(idProduct, product);

        return "Product update complete successfully";
    }

    @DeleteMapping("/remove-product/{idProduct}")
    public String removeProduct(@Validated @PathVariable Long idProduct)
    {
        this.productServiceServ.remove(idProduct);

        return "Product successfully delete";
    }

    @GetMapping("/get-by-id-product/{idProduct}")
    public Product getByIdProduct(@Validated @PathVariable Long idProduct)
    {
        return this.productServiceServ.getById(idProduct);
    }
}
