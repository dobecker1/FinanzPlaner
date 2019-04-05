package models.patternBooking.impl;

import models.patternBooking.interfaces.InputField;

import javax.persistence.*;

@Entity
@Table(name = "INPUT_FIELDS")
public class InputFieldImpl implements InputField {

    private int id;
    private String name;
    private InputFieldType type;

    @Override
    @Column(nullable = false)
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public InputFieldType getInputFieldType() {
        return this.type;
    }

    @Override
    public void setInputFieldType(InputFieldType type) {
        this.type = type;
    }

    @Override
    @Id @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
