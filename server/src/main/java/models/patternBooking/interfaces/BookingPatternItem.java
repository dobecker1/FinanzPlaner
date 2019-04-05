package models.patternBooking.interfaces;

import models.basic.BasicModel;
import models.booking.Booking;

public interface BookingPatternItem extends BasicModel {

    public int getId();
    public void setId(int id);

    public Booking getBooking();
    public void setBooking(Booking booking);

    public BookingPatternPayload getPayload();
    public void setPayload(BookingPatternPayload payload);
}
