package daoLayer.sqlDao;

import factory.ModelFactory;
import models.category.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends BasicDao {

    private static final String CATEGORY_TABLE = "CATEGORY";

    public int write(Category category) {
        int categoryId = -1;

        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("INSERT INTO CATEGORY(NAME) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                categoryId = generatedKeys.getInt(1);
            }
            generatedKeys.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryId;
    }

    public Category read(int id) {
        try {
            PreparedStatement statement = super.controller.connection
                    .prepareStatement("SELECT * FROM CATEGORY where id = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                Category category = ModelFactory.getCategory();
                category.setId(result.getInt("id"));
                category.setName(result.getString("name"));
                result.close();
                return category;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler");
        }
    }

    public void delete(Category category) {
        super.delete(category, CATEGORY_TABLE);
    }

    public boolean delete(int id) {
        return super.delete(id, CATEGORY_TABLE);
    }

    public List<Category> findAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement statement = super.controller.connection
                    .prepareStatement("SELECT * FROM " + CATEGORY_TABLE);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                Category category = ModelFactory.getCategory();
                category.setId(result.getInt("id"));
                category.setName(result.getString("name"));
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler");
        }
    }

    public boolean updateCategory(Category category) {
        try {
            PreparedStatement statement = super.controller.connection
                    .prepareStatement("UPDATE CATEGORY SET NAME = ? WHERE ID = ?");
            statement.setString(1, category.getName());
            statement.setInt(2, category.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
