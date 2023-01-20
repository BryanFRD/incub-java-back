package fr.insy2s.commerce.shoponlineback.services;


import fr.insy2s.commerce.shoponlineback.beans.Product;
import fr.insy2s.commerce.shoponlineback.dtos.ProductDTO;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.mappers.ProductMapper;
import fr.insy2s.commerce.shoponlineback.mappers.ProductMapperImpl;
import fr.insy2s.commerce.shoponlineback.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService implements Webservices<ProductDTO> {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper = new ProductMapperImpl();


    public Page<ProductDTO> all(Pageable pageable) {
        return this.productRepository.findAll(pageable)
                .map(this.productMapper::fromProduct);
    }

    @Override
    public List<ProductDTO> all() {
        List<Product> products = this.productRepository.findAll();
        return null;
    }



    @Override
    public void add(ProductDTO e) {
        e.setRefProduct(UUID.randomUUID().toString());
        this.productRepository.save(this.productMapper.fromProductDTO(e));
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
                    if(p.getCategory() != null)
                       {
                           Product product = this.productMapper.fromProductDTO(e);
                           p.setCategory(product.getCategory());
                       }

                    return this.productRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Sorry not this id for product")));
    }

    @Override
    public void remove(Long id) {
      Optional <Product> product = this.productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("error product not found");
        }
            this.productRepository.deleteById(id);

    }

    @Override
    public ProductDTO getById(Long id) {
        return this.productMapper.fromProduct(this.productRepository.findById(id).orElseThrow(()-> new RuntimeException("Sorry product id not found")));
    }


}
