package fr.insy2s.commerce.shoponlineback.services;


import fr.insy2s.commerce.shoponlineback.beans.Category;
import fr.insy2s.commerce.shoponlineback.beans.Product;
import fr.insy2s.commerce.shoponlineback.dtos.ProductDTO;
import fr.insy2s.commerce.shoponlineback.exceptions.beansexptions.CategoryNotFoundException;
import fr.insy2s.commerce.shoponlineback.exceptions.generic_exception.WebservicesGenericServiceException;
import fr.insy2s.commerce.shoponlineback.exceptions.beansexptions.ProductNotFoundException;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.mappers.ProductMapper;
import fr.insy2s.commerce.shoponlineback.mappers.ProductMapperImpl;
import fr.insy2s.commerce.shoponlineback.repositories.CategoryRepository;
import fr.insy2s.commerce.shoponlineback.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements Webservices<ProductDTO, WebservicesGenericServiceException> {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper = new ProductMapperImpl();

    private final UuidService uuidService;

    @Override
    public Page<ProductDTO> all(Pageable pageable) {

        return this.productRepository.findByPresentIsTrue(pageable)
                .map(this.productMapper::fromProduct);
    }



    @Override
    public void add(ProductDTO e) {
        e.setRefProduct(this.uuidService.generateUuid());

        if (e.getPresent() == null)
            e.setPresent(true);

        this.productRepository.save(this.productMapper.fromProductDTO(e));
    }

    public ProductDTO addNew(ProductDTO p) {
        p.setRefProduct(this.uuidService.generateUuid());
        Product product =  this.productRepository.save(this.productMapper.fromProductDTO(p));
        return this.productMapper.fromProduct(product);
    }

//    @Override
//    public void add(ProductDTO e) {
//        e.setRefProduct(this.uuidService.generateUuid());
//        ProductDTO productDTO = new ProductDTO();
//            productDTO.setName(e.getName());
//            productDTO.setProductDescription(e.getProductDescription());
//            productDTO.setPriceTTC(e.getPriceTTC());
//            productDTO.setProductInventory(e.getProductInventory());
//            productDTO.setCategory(e.getCategory());
//        this.productRepository.save(this.productMapper.fromProductDTO(productDTO));
//    }

    @Override
    public ProductDTO update(Long id, ProductDTO e) {
        return this.productMapper.fromProduct(this.productRepository.findById(id)
                .map(p-> {
                    p.setRefProduct(this.uuidService.generateUuid());
                    if (p.getName() != null)
                        p.setName(e.getName());
                    if(p.getPriceTTC() != null)
                        p.setPriceTTC(e.getPriceTTC());
                    if(p.getProductInventory() != null)
                        p.setProductInventory(e.getProductInventory());
                    p.setProductDescription(e.getProductDescription());
                    if (p.getPresent() == null || !p.getPresent())
                        p.setPresent(true);
                    if(p.getCategory() != null)
                    {
                        Product product = this.productMapper.fromProductDTO(e);

                        p.setCategory(product.getCategory());
                    }

                    return this.productRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Sorry not this id for product")));
    }

    @Override
    public void remove(Long id)
    {
        Optional<Product> product = this.productRepository.findById(id);

        if (product.isEmpty())
            throw new ProductNotFoundException("Product with id " +id+ "was not found");
        product.get().setPresent(false);
        this.productRepository.save(product.get());
    }

    @Override
    public Optional<ProductDTO> getById(Long id) {
        return this.productRepository.findById(id)
                .map(this.productMapper::fromProduct)
                .map(Optional::of)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " +id+ " was not found"));
    }


    public Page<ProductDTO> getProductsByCategoryId(Long id, Pageable page){
        System.out.println(id);
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " was not found"));
        List<Product> productList = this.productRepository.findByCategory(category);
        Page<Product> page1 = new PageImpl<>(productList, page, productList.size());
        return page1.map(this.productMapper::fromProduct) ;

    }

    public Page<ProductDTO> updatePresentAllProduct(Pageable pageable)
    {
        List<Product> products = this.productRepository.findAll();

        List<Product> productList = new ArrayList<>();

        for (int i = 0; i < products.size(); i++)
        {
            products.get(i).setPresent(true);
            productList.add(products.get(i));
//            this.productRepository.save(products.get(i));
        }

        Page<Product> productPage = new PageImpl<>(productList, pageable, productList.size());

        return productPage.map(this.productMapper::fromProduct);
    }


}
