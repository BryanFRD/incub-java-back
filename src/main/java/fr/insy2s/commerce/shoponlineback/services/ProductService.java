package fr.insy2s.commerce.shoponlineback.services;


import fr.insy2s.commerce.shoponlineback.beans.Product;
import fr.insy2s.commerce.shoponlineback.dtos.ProductDTO;
import fr.insy2s.commerce.shoponlineback.exceptions.generic_exception.WebservicesGenericServiceException;
import fr.insy2s.commerce.shoponlineback.exceptions.beansexptions.ProductNotFoundException;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.mappers.ProductMapper;
import fr.insy2s.commerce.shoponlineback.mappers.ProductMapperImpl;
import fr.insy2s.commerce.shoponlineback.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements Webservices<ProductDTO, WebservicesGenericServiceException> {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper = new ProductMapperImpl();

    private final UuidService uuidService;

    @Override
    public Page<ProductDTO> all(Pageable pageable) {

        return this.productRepository.findAll(pageable).map(this.productMapper::fromProduct);
    }



    @Override
    public void add(ProductDTO e) {
        e.setRefProduct(this.uuidService.generateUuid());

        this.productRepository.save(this.productMapper.fromProductDTO(e));
    }

    @Override
    public ProductDTO update(Long id, ProductDTO e) {
        return this.productMapper.fromProduct(this.productRepository.findById(id)
                .map(p-> {
//                    p.setRefProduct(this.uuidService.generateUuid()); // TODO  supprimer l'update de l'uuid ???
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

        Optional<Product> productDTO = this.productRepository.findById(id);

        if (productDTO.isEmpty())
            throw new ProductNotFoundException("Ordered with id " +id+ " was not found");
        this.productRepository.deleteById(id);

    }

    @Override
    public Optional<ProductDTO> getById(Long id) {
        return this.productRepository.findById(id)
                .map(this.productMapper::fromProduct)
                .map(Optional::of)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " +id+ " was not found"));
    }


}
