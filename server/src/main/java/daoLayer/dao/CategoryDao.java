package daoLayer.dao;

import models.category.Category;
import models.category.CategoryImpl;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("categoryDao")
public class CategoryDao extends BasicDao {

    public void write(Category category) {
        super.write(category);
    }

    public Category read(int id) {
        return super.read(id, CategoryImpl.class);
    }

    public void deleteCategory(int id) {
        super.delete(this.read(id));
    }

    public void deleteCategory(Category category) {
        super.delete(category);
    }

    public List<Category> findAllCategories() {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from CategoryImpl");
        List<Category> categories = query.list();
        session.getTransaction().commit();
        return categories;
    }
}
