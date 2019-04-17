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
    public String saveCategory(@RequestBody Category category) {
        this.categoryService.saveCategory(category);
        return "OK";
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable int id) {
        this.categoryService.deleteCategory(id);
        return "OK";
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return this.categoryService.findAllCategories();
    }
}
