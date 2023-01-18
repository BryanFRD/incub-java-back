package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.beans.Category;
import fr.insy2s.commerce.shoponlineback.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/all-category")
    public List<Category> allCategory()
    {
        return this.categoryService.all();
    }

    @PostMapping("/add-category")
    public String addCategory(@Validated @RequestBody Category category)
    {
        this.categoryService.add(category);

        return "Category successfully add";
    }

    @PutMapping("/update-category/{idCategory}")
    public String updateCategory(@Validated @PathVariable Long idCategory, @RequestBody Category category)
    {
        this.categoryService.update(idCategory, category);

        return "Category update complete successfully";
    }

    @DeleteMapping("/remove-category/{idCategory}")
    public String removeCategory(@Validated @PathVariable Long idCategory)
    {
        this.categoryService.remove(idCategory);

        return "Category successfully delete";
    }

    @GetMapping("/get-by-id-category/{idCategory}")
    public Category getByIdCategory(@Validated @PathVariable Long idCategory)
    {
        return this.categoryService.getById(idCategory);
    }
}
