package fr.insy2s.commerce.shoponlineback.controllerDTO;

import fr.insy2s.commerce.shoponlineback.dtos.CategoryDTO;
import fr.insy2s.commerce.shoponlineback.servicesDTO.CategoryServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category-dto")
@RequiredArgsConstructor
public class CategoryControllerDTO {

    private final CategoryServiceDTO categoryServiceDTO;

    @GetMapping("/all-category-dto")
    public List<CategoryDTO> allCategoryDTO(){ return this.categoryServiceDTO.all();}

    @PostMapping("/add-category-dto")
    public String addCategoryDTO(@Validated @RequestBody CategoryDTO categoryDTO) {
        this.categoryServiceDTO.add(categoryDTO);
        return "Category dto successfully add";
    }

    @PutMapping("/update-category-dto/{idCategory}")
    public String updateCategoryDTO(@Validated @PathVariable Long idCategory, @RequestBody CategoryDTO categoryDTO) {
        this.categoryServiceDTO.update(idCategory, categoryDTO);
        return "Category dto update complete successfully";
    }

    @DeleteMapping("/remove-category-dto/{idCategory}")
    public String removeCategoryDTO(@Validated @PathVariable Long idCategory) {
        this.categoryServiceDTO.remove(idCategory);
        return  "Category dto successfully delete";
    }

    @GetMapping("/get-by-id-category-dto/{idCategory}")
    public CategoryDTO getByIdCategoryDTO(@Validated @PathVariable Long idCategory) {
        return this.categoryServiceDTO.getById(idCategory);
    }

}
