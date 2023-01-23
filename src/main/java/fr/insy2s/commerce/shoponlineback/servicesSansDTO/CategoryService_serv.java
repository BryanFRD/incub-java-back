package fr.insy2s.commerce.shoponlineback.servicesSansDTO;

import fr.insy2s.commerce.shoponlineback.beans.Category;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService_serv {

    private final CategoryRepository categoryRepository;

    public List<Category> all() {
        return this.categoryRepository.findAll();
    }

    public void add(Category e) {

        this.categoryRepository.save(e);
    }

    public Category update(Long id, Category e) {
        return this.categoryRepository.findById(id)
                .map(p -> {
                    if (p.getName() != null)
                        p.setName(e.getName());
                    if (p.getUrl() != null)
                        p.setUrl(e.getUrl());
                    return this.categoryRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("this id is not found sorry"));
    }

    public void remove(Long id) {

        Category category = this.categoryRepository.findById(id).get();

        if (category != null)
            this.categoryRepository.delete(category);
    }

    public Category getById(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found sorry"));
    }
}
