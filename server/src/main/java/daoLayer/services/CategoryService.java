package daoLayer.services;

import daoLayer.services.daoServices.CategoryDaoService;
import models.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryService {

    @Autowired
    private CategoryDaoService categoryDaoService;

    public int saveCategory(Category category) {
        return this.categoryDaoService.saveCategory(category);
    }

    public void deleteCategory(Category category) {
        this.categoryDaoService.deleteCategory(category);
    }

    public void deleteCategory(int id) {
        this.categoryDaoService.deleteCategory(id);
    }

    public List<Category> findAllCategories() {
        return this.categoryDaoService.findAllCategories();
    }
}
