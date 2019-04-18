package models.patternBooking.impl;

import models.patternBooking.interfaces.InputField;

public class InputFieldImpl implements InputField {

    private int id;
    private String name;
    private InputFieldType type;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public InputFieldType getInputFieldType() {
        return this.type;
    }

    @Override
    public void setInputFieldType(InputFieldType type) {
        this.type = type;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
