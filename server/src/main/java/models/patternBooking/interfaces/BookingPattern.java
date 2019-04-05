package models.patternBooking.interfaces;

import models.basic.BasicModel;
import models.booking.Booking;
import models.category.Category;
import models.patternBooking.impl.BookingPatternImpl;
import models.patternBooking.interfaces.BookingInformation;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BookingPattern extends BasicModel {

    public int getId();

    public void setId(int id);

    public String getName();

    public void setName(String name);

    public Date getExecutionDate();

    public void setExecutionDate(Date date);

    public String getExecutionDatePattern();

    public void setExecutionDatePattern(String pattern);

    public BookingInformation getBookingInformation();

    public void setBookingInformation(BookingInformation bookingInformation);

    public Category getCategory();

    public void setCategory(Category category);

    public boolean isPattern();
    public void setPattern(boolean pattern);

    public List<InputField> getInputFields();
    public void setInputFields(List<InputField> inputFields);

    public List<BookingPatternItem> getBookingPatternItems();
    public void setBookingPatternItems(List<BookingPatternItem> patternItems);
}
