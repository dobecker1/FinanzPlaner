package web.persistence;

import daoLayer.services.CategoryService;
import models.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryWebController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/categories")
    public int saveCategory(@RequestBody Category category) {
        return this.categoryService.saveCategory(category);
    }

    @PutMapping("/categories")
    public boolean updateCategory(@RequestBody Category category) {
        return this.categoryService.updateCategory(category);
    }

    @DeleteMapping("/categories/{id}")
    public boolean deleteCategory(@PathVariable int id) {
        return this.categoryService.deleteCategory(id);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return this.categoryService.findAllCategories();
    }
}
