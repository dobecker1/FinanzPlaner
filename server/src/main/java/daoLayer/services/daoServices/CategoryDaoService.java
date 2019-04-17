package daoLayer.services.daoServices;

import daoLayer.sqlDao.CategoryDao;
import factory.DaoFactory;
import models.category.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryDaoService {

    private CategoryDao categoryDao;

    public CategoryDaoService() {
        this.categoryDao = DaoFactory.getCategoryDao();
    }

    public int saveCategory(Category category) {
        return this.categoryDao.write(category);
    }

    public void deleteCategory(Category category) {
        this.categoryDao.delete(category);
    }

    public void deleteCategory(int id) {
        this.categoryDao.delete(id);
    }

    public Category findCategoryById(int id) {
        return this.categoryDao.read(id);
    }

    public List<Category> findAllCategories() {
        return this.categoryDao.findAllCategories();
    }
}
