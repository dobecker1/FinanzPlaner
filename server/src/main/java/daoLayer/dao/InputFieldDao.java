package daoLayer.dao;

import models.patternBooking.impl.InputFieldImpl;
import models.patternBooking.interfaces.InputField;

public class InputFieldDao extends BasicDao {

    public void write(InputField inputField) {
        super.write(inputField);
    }

    public InputField read(int id) {
        return super.read(id, InputFieldImpl.class);
    }

    public void delete(InputField inputField) {
        super.delete(inputField);
    }


}
