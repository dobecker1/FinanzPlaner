package daoLayer.sqlDao;

import models.basic.BasicModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BasicDao {

    protected DaoController controller = DaoController.getInstance();

    protected void delete(BasicModel model, String table) {
        try {
            PreparedStatement statement = this.controller.connection.
                    prepareStatement("DELETE FROM " + table + " WHERE id = ?");
            statement.setInt(1, model.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void delete(int id, String table) {
        try {
            PreparedStatement statement = this.controller.connection.
                    prepareStatement("DELETE FROM " + table + " WHERE id = ?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
