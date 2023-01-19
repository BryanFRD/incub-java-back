package fr.insy2s.commerce.shoponlineback.controllersSansDTO;

import fr.insy2s.commerce.shoponlineback.beans.Category;
import fr.insy2s.commerce.shoponlineback.servicesSansDTO.CategoryService_serv;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController_control {

    private final CategoryService_serv categoryServiceServ;

    @GetMapping("/all-category")
    public List<Category> allCategory()
    {
        return this.categoryServiceServ.all();
    }

    @PostMapping("/add-category")
    public String addCategory(@Validated @RequestBody Category category)
    {
        this.categoryServiceServ.add(category);

        return "Category successfully add";
    }

    @PutMapping("/update-category/{idCategory}")
    public String updateCategory(@Validated @PathVariable Long idCategory, @RequestBody Category category)
    {
        this.categoryServiceServ.update(idCategory, category);

        return "Category update complete successfully";
    }

    @DeleteMapping("/remove-category/{idCategory}")
    public String removeCategory(@Validated @PathVariable Long idCategory)
    {
        this.categoryServiceServ.remove(idCategory);

        return "Category successfully delete";
    }

    @GetMapping("/get-by-id-category/{idCategory}")
    public Category getByIdCategory(@Validated @PathVariable Long idCategory)
    {
        return this.categoryServiceServ.getById(idCategory);
    }
}
