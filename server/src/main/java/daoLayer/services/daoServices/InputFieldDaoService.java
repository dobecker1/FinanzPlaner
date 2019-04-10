package daoLayer.services.daoServices;

import daoLayer.sqlDao.InputFieldDao;
import factory.DaoFactory;
import models.patternBooking.interfaces.InputField;

public class InputFieldDaoService {

    private InputFieldDao inputFieldDao;

    public InputFieldDaoService() {
        this.inputFieldDao = DaoFactory.getInputFieldDao();
    }

    public int saveInputField(InputField inputField) {
        return this.inputFieldDao.write(inputField);
    }

    public void deleteInputField(InputField inputField) {
        this.inputFieldDao.delete(inputField);
    }

    public InputField findInputFieldById(int id) {
        return this.inputFieldDao.read(id);
    }
}
