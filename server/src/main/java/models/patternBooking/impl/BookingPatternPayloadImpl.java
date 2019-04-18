package models.patternBooking.impl;

import models.patternBooking.interfaces.BookingPatternPayload;
import java.util.Map;

public class BookingPatternPayloadImpl implements BookingPatternPayload {

    private int id;
    private Map<String, String> bookingPatternPayload;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Map<String, String> getBookingPatternPayload() {
        return this.bookingPatternPayload;
    }

    @Override
    public void setBookingPatternPayload(Map<String, String> patternPayload) {
        this.bookingPatternPayload = patternPayload;
    }
}
