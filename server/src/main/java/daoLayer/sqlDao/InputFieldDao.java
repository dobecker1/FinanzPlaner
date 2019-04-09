package daoLayer.sqlDao;

import factory.ModelFactory;
import models.patternBooking.interfaces.InputField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InputFieldDao extends BasicDao {

    private static  final String INPUT_FIELD_TABLE = "INPUT_FIELD";


    public int write(InputField inputField) {
        int inputFieldId = -1;

        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("INSERT INTO INPUT_FIELD(NAME, TYPE) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, inputField.getName());
            statement.setString(2, inputField.getInputFieldType().toString());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                inputFieldId = generatedKeys.getInt(1);
            }
            generatedKeys.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inputFieldId;
    }

    public InputField read(int id) {
        try {
            PreparedStatement statement = super.controller.connection
                    .prepareStatement("SELECT * FROM " + INPUT_FIELD_TABLE +" where id = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                InputField inputField = ModelFactory.getInputField();
                inputField.setId(result.getInt("id"));
                inputField.setName(result.getString("name"));
                inputField.setInputFieldType(InputField.InputFieldType.valueOf(result.getString("type")));
                result.close();
                return inputField;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler");
        }
    }

    public void delete(InputField inputField) {
        super.delete(inputField, INPUT_FIELD_TABLE);
    }

    public List<InputField> findAllInputFields() {
        List<InputField> inputFields = new ArrayList<>();
        try {
            PreparedStatement statement = super.controller.connection
                    .prepareStatement("SELECT * FROM " + INPUT_FIELD_TABLE);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                InputField inputField = ModelFactory.getInputField();
                inputField.setId(result.getInt("id"));
                inputField.setName(result.getString("name"));
                inputField.setInputFieldType(InputField.InputFieldType.valueOf(result.getString("type")));
                inputFields.add(inputField);
            }
            return inputFields;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler");
        }
    }
}
