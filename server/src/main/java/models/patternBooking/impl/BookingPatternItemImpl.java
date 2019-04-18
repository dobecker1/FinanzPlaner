package models.patternBooking.impl;

import models.booking.Booking;
import models.patternBooking.interfaces.BookingPatternItem;
import models.patternBooking.interfaces.BookingPatternPayload;


public class BookingPatternItemImpl implements BookingPatternItem {

    private int id;
    private Booking booking;
    private BookingPatternPayload payload;


    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Booking getBooking() {
        return this.booking;
    }

    @Override
    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public BookingPatternPayload getPayload() {
        return this.payload;
    }

    @Override
    public void setPayload(BookingPatternPayload payload) {
        this.payload = payload;
    }
}
