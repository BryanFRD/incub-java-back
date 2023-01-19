package fr.insy2s.commerce.shoponlineback.servicesSansDTO;

import fr.insy2s.commerce.shoponlineback.beans.Category;
import fr.insy2s.commerce.shoponlineback.interfaces.Webservices;
import fr.insy2s.commerce.shoponlineback.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService_serv implements Webservices<Category> {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> all() {
        return this.categoryRepository.findAll();
    }

    @Override
    public void add(Category e) {

        this.categoryRepository.save(e);
    }

    @Override
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

    @Override
    public void remove(Long id) {

        Category category = this.categoryRepository.findById(id).get();

        if (category != null)
            this.categoryRepository.delete(category);
    }

    @Override
    public Category getById(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found sorry"));
    }
}
