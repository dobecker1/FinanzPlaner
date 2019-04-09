package daoLayer.services;

import daoLayer.sqlDao.CategoryDao;
import factory.DaoFactory;
import models.category.Category;

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

    public Category findCategoryById(int id) {
        return this.categoryDao.read(id);
    }
}
