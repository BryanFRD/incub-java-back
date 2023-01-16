package fr.insy2s.commerce.shoponlineback.services;

import fr.insy2s.commerce.shoponlineback.beans.Product;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService implements Webservices<Product> {

    private final ProductRepository productRepository;

    @Override
    public List<Product> all() {
        return this.productRepository.findAll();
    }

    @Override
    public void add(Product e) {

        e.setProductReference(UUID.randomUUID().toString());

        this.productRepository.save(e);
    }

    @Override
    public Product update(Long id, Product e) {
        return this.productRepository.findById(id)
                .map(p -> {
                    p.setProductReference(UUID.randomUUID().toString());
                    if (p.getName() != null)
                        p.setName(e.getName());
                    if (p.getPriceTTC() != null)
                        p.setPriceTTC(e.getPriceTTC());
                    if (p.getProductInventory() != null)
                        p.setProductInventory(e.getProductInventory());
                    return this.productRepository.save(p);
                }).orElseThrow(()-> new RuntimeException("not found product id soryy"));
    }

    @Override
    public void remove(Long id) {

        Product product = this.productRepository.findById(id).get();

        if (product != null)
            this.productRepository.delete(product);
    }

    @Override
    public Product getById(Long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new RuntimeException("not found sorry"));
    }
}
