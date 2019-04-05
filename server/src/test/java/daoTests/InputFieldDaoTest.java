package daoTests;

import daoLayer.sqlDao.InputFieldDao;
import factory.ModelFactory;
import models.patternBooking.interfaces.InputField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputFieldDaoTest {

    private final InputFieldDao inputFieldDao = new InputFieldDao();

    private InputField inputField;

    @BeforeEach
    void createInputField() {
        this.inputField = ModelFactory.getInputField();
        this.inputField.setName("TestInput");
        this.inputField.setInputFieldType(InputField.InputFieldType.TEXT);
    }

    @Test
    void writeInputFieldTest() {
        this.inputFieldDao.write(this.inputField);
        InputField savedInputField = this.inputFieldDao.findAllInputFields().get(0);
        savedInputField = this.inputFieldDao.read(savedInputField.getId());
        assertEquals(this.inputField.getName(), savedInputField.getName());
        assertEquals(this.inputField.getInputFieldType(), savedInputField.getInputFieldType());
        this.inputFieldDao.delete(savedInputField);
    }

    @Test
    void findAllInputFields() {
        this.inputFieldDao.write(this.inputField);
        InputField secondInputField = ModelFactory.getInputField();
        secondInputField.setName("second field");
        secondInputField.setInputFieldType(InputField.InputFieldType.INTEGER);
        this.inputFieldDao.write(secondInputField);
        List<InputField> inputFields = this.inputFieldDao.findAllInputFields();
        assertEquals(2, inputFields.size());
        this.inputFieldDao.delete(inputFields.get(0));
        this.inputFieldDao.delete(inputFields.get(1));
    }
}
