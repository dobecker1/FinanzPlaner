package daoLayer.sqlDao;

import factory.ModelFactory;
import models.category.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends BasicDao {

    private static final String CATEGORY_TABLE = "CATEGORY";

    public void write(Category category) {
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("INSERT INTO CATEGORY(NAME) VALUES(?)");
            statement.setString(1, category.getName());
            statement.addBatch();
            super.controller.connection.setAutoCommit(false);
            statement.executeBatch();
            super.controller.connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
}
