package daoTests;

import daoLayer.sqlDao.CategoryDao;
import factory.ModelFactory;
import models.category.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryDaoTest {

    private  final CategoryDao categoryDao = new CategoryDao();
    private Category category;

    @BeforeEach
    void createCategory() {
        this.category = ModelFactory.getCategory();
        this.category.setName("Test Kategorie");
    }

    @Test
    void writeCategoryTest() {
        this.categoryDao.write(this.category);
        Category savedCategory = this.categoryDao.findAllCategories().get(0);
        assertEquals(this.category.getName(), savedCategory.getName());
        this.categoryDao.delete(savedCategory);
    }

    @Test
    void findAllCategoriesTest() {
        this.categoryDao.write(this.category);
        Category secondCategory = ModelFactory.getCategory();
        secondCategory.setName("Second Category");
        this.categoryDao.write(secondCategory);
        List<Category> categories = this.categoryDao.findAllCategories();
        assertEquals(2, categories.size());
        for(Category category : categories) {
            this.categoryDao.delete(category);
        }
    }
}
