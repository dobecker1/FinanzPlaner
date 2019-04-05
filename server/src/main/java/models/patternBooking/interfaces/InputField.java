package models.patternBooking.interfaces;

import models.basic.BasicModel;

public interface InputField extends BasicModel {

    public int getId();
    public void setId(int id);

    public String getName();
    public void setName(String name);

    public InputFieldType getInputFieldType();
    public void setInputFieldType(InputFieldType type);

    public enum InputFieldType {
        TEXT, DATE, INTEGER, DOUBLE
    }
}
