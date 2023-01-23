package fr.insy2s.commerce.shoponlineback.services;

import fr.insy2s.commerce.shoponlineback.beans.Category;
import fr.insy2s.commerce.shoponlineback.dtos.CategoryDTO;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.mappers.CategoryMapper;
import fr.insy2s.commerce.shoponlineback.mappers.CategoryMapperImpl;
import fr.insy2s.commerce.shoponlineback.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService /*implements Webservices<CategoryDTO>*/ {

    private final CategoryRepository categoryRepository;

    private CategoryMapper categoryMapper = new CategoryMapperImpl();


   /* @Override
    public List<CategoryDTO> all() {
        return this.categoryMapper.allDTOFromCategory(this.categoryRepository.findAll());
    }

    @Override
    public void add(CategoryDTO e) {
        this.categoryRepository.save(this.categoryMapper.fromCategoryDTO(e));

    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO e) {
        return this.categoryMapper.fromCategory(this.categoryRepository.findById(id)
                .map(p-> {
                   if(p.getName() != null)
                       p.setName(e.getName());
                   if(p.getUrl() != null)
                       p.setUrl(e.getUrl());
                   return this.categoryRepository.save(p);
                }).orElseThrow(()-> new RuntimeException("Sorry not found id category"))
        );
    }

    @Override
    public void remove(Long id) {
        Category category = this.categoryRepository.findById(id).get();
        if(category != null)
            this.categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO getById(Long id) {
        return this.categoryMapper.fromCategory(this.categoryRepository.findById(id).get());
    }*/
}

