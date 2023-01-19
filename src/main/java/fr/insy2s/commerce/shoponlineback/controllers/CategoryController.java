package fr.insy2s.commerce.shoponlineback.controllers;

import fr.insy2s.commerce.shoponlineback.dtos.CategoryDTO;
import fr.insy2s.commerce.shoponlineback.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category-dto")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/all-category-dto")
    public List<CategoryDTO> allCategoryDTO(){ return this.categoryService.all();}

    @PostMapping("/add-category-dto")
    public String addCategoryDTO(@Validated @RequestBody CategoryDTO categoryDTO) {
        this.categoryService.add(categoryDTO);
        return "Category dto successfully add";
    }

    @PutMapping("/update-category-dto/{idCategory}")
    public String updateCategoryDTO(@Validated @PathVariable Long idCategory, @RequestBody CategoryDTO categoryDTO) {
        this.categoryService.update(idCategory, categoryDTO);
        return "Category dto update complete successfully";
    }

    @DeleteMapping("/remove-category-dto/{idCategory}")
    public String removeCategoryDTO(@Validated @PathVariable Long idCategory) {
        this.categoryService.remove(idCategory);
        return  "Category dto successfully delete";
    }

    @GetMapping("/get-by-id-category-dto/{idCategory}")
    public CategoryDTO getByIdCategoryDTO(@Validated @PathVariable Long idCategory) {
        return this.categoryService.getById(idCategory);
    }

}
