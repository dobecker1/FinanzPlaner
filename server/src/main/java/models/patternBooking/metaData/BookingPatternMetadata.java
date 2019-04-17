package models.patternBooking.metaData;

import java.util.Date;

public class BookingPatternMetadata {

    private int id;
    private String name;
    private Date executionDate;
    private String executionDatePattern;
    private int bookingInformation;
    private int category;
    private boolean pattern;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExecutionDate() {
        return this.executionDate;
    }

    public void setExecutionDate(Date date) {
        this.executionDate = date;
    }

    public String getExecutionDatePattern() {
        return this.executionDatePattern;
    }

    public void setExecutionDatePattern(String pattern) {
        this.executionDatePattern = pattern;
    }

    public int getBookingInformation() {
        return this.bookingInformation;
    }

    public void setBookingInformation(int bookingInformation) {
        this.bookingInformation = bookingInformation;
    }

    public int getCategory() {
        return this.category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isPattern() {
        return this.pattern;
    }

    public void setPattern(boolean pattern) {
        this.pattern = pattern;
    }

}
