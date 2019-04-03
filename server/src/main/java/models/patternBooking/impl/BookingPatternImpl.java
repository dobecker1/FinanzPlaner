package models.patternBooking.impl;

import models.category.Category;
import models.category.CategoryImpl;
import models.patternBooking.interfaces.BookingInformation;
import models.patternBooking.interfaces.BookingPattern;
import models.patternBooking.interfaces.BookingPatternItem;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "PATTERN_BOOKING")
public class BookingPatternImpl implements BookingPattern {

    private int id;

    private String name;
    private Date executionDate;
    private String executionDatePattern;
    private BookingInformation bookingInformation;
    private Category category;
    private boolean pattern;
    private List<BookingPatternItem> patternItems;

    @Override
    @Id @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    @Column(name = "executionDate")
    public Date getExecutionDate() {
        return this.executionDate;
    }

    @Override
    public void setExecutionDate(Date date) {
        this.executionDate = date;
    }

    @Override
    @Column(name = "executionDatePattern")
    public String getExecutionDatePattern() {
        return this.executionDatePattern;
    }

    @Override
    public void setExecutionDatePattern(String pattern) {
        this.executionDatePattern = pattern;
    }

    @Override
    @OneToOne(fetch = FetchType.EAGER, targetEntity = BookingInformationImpl.class)
    @JoinColumn(name = "bookingInformation", nullable = false)
    public BookingInformation getBookingInformation() {
        return this.bookingInformation;
    }

    @Override
    public void setBookingInformation(BookingInformation bookingInformation) {
        this.bookingInformation = bookingInformation;
    }

    @Override
    @OneToOne(fetch = FetchType.EAGER, targetEntity = CategoryImpl.class)
    @JoinColumn(name = "category", nullable = false)
    public Category getCategory() {
        return this.category;
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    @Column(name = "pattern", nullable = false)
    public boolean isPattern() {
        return this.pattern;
    }

    @Override
    public void setPattern(boolean pattern) {
        this.pattern = pattern;
    }

    @Override
    @OneToMany(fetch = FetchType.LAZY, targetEntity = BookingPatternItemImpl.class)
    @Column(name = "patternItems", nullable = false)
    public List<BookingPatternItem> getBookingPatternItems() {
        return this.patternItems;
    }

    @Override
    public void setBookingPatternItems(List<BookingPatternItem> patternItems) {
        this.patternItems = patternItems;
    }

}
