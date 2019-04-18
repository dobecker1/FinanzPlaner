package models.patternBooking.impl;

import models.category.Category;
import models.patternBooking.interfaces.BookingInformation;
import models.patternBooking.interfaces.BookingPattern;
import models.patternBooking.interfaces.BookingPatternItem;
import models.patternBooking.interfaces.InputField;

import java.util.Date;
import java.util.List;

public class BookingPatternImpl implements BookingPattern {

    private int id;

    private String name;
    private Date executionDate;
    private String executionDatePattern;
    private BookingInformation bookingInformation;
    private Category category;
    private boolean pattern;
    private List<InputField> inputFields;
    private List<BookingPatternItem> patternItems;

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Date getExecutionDate() {
        return this.executionDate;
    }

    @Override
    public void setExecutionDate(Date date) {
        this.executionDate = date;
    }

    @Override
    public String getExecutionDatePattern() {
        return this.executionDatePattern;
    }

    @Override
    public void setExecutionDatePattern(String pattern) {
        this.executionDatePattern = pattern;
    }

    @Override
    public BookingInformation getBookingInformation() {
        return this.bookingInformation;
    }

    @Override
    public void setBookingInformation(BookingInformation bookingInformation) {
        this.bookingInformation = bookingInformation;
    }

    @Override
    public Category getCategory() {
        return this.category;
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean isPattern() {
        return this.pattern;
    }

    @Override
    public void setPattern(boolean pattern) {
        this.pattern = pattern;
    }

    @Override
    public List<InputField> getInputFields() {
        return this.inputFields;
    }

    @Override
    public void setInputFields(List<InputField> inputFields) {
        this.inputFields = inputFields;
    }

    @Override
    public List<BookingPatternItem> getBookingPatternItems() {
        return this.patternItems;
    }

    @Override
    public void setBookingPatternItems(List<BookingPatternItem> patternItems) {
        this.patternItems = patternItems;
    }

}
