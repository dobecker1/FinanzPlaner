package models.patternBooking.interfaces;

import models.basic.BasicModel;

import java.util.Map;

public interface BookingPatternPayload extends BasicModel {

    public int getId();
    public void setId(int id);

    public Map<String, String> getBookingPatternPayload();
    public void setBookingPatternPayload(Map<String, String> patternPayload);
}
