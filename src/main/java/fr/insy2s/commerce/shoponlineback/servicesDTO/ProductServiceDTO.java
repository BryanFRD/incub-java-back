package fr.insy2s.commerce.shoponlineback.servicesDTO;


import fr.insy2s.commerce.shoponlineback.beans.Product;
import fr.insy2s.commerce.shoponlineback.dtos.ProductDTO;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.mappers.ProductMapper;
import fr.insy2s.commerce.shoponlineback.mappers.ProductMapperImpl;
import fr.insy2s.commerce.shoponlineback.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceDTO implements Webservices<ProductDTO> {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper = new ProductMapperImpl();

    @Override
    public List<ProductDTO> all() {
        List<Product> products = this.productRepository.findAll();
        return this.productMapper.allDTOFromProduct(products);
    }



    @Override
    public void add(ProductDTO e) {
        e.setRefProduct(UUID.randomUUID().toString());
        e.setCategory(e.getCategory());
        Product product = this.productMapper.fromProductDTO(e);
        this.productRepository.save(product);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO e) {
        return this.productMapper.fromProduct(this.productRepository.findById(id)
                .map(p-> {
                    p.setRefProduct(UUID.randomUUID().toString());
                    if (p.getName() != null)
                        p.setName(e.getName());
                    if(p.getPriceTTC() != null)
                        p.setPriceTTC(e.getPriceTTC());
                    if(p.getProductInventory() != null)
                        p.setProductInventory(e.getProductInventory());
//                    if(p.getCategory() != null)
//                        p.setCategory(e.getCategory());

                    return this.productRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Sorry not this id for product")));
    }

    @Override
    public void remove(Long id) {
        Product product = this.productRepository.findById(id).get();
        if (product != null)
            this.productRepository.delete(product);

    }

    @Override
    public ProductDTO getById(Long id) {
        return this.productMapper.fromProduct(this.productRepository.findById(id).orElseThrow(()-> new RuntimeException("Sorry product id not found")));
    }


}
