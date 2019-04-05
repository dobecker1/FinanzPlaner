package models.patternBooking.impl;

import models.patternBooking.interfaces.BookingPatternPayload;
import models.patternBooking.interfaces.InputField;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "BOOKING_PATTERN_PAYLOAD")
public class BookingPatternPayloadImpl implements BookingPatternPayload {

    private int id;
    private Map<String, String> bookingPatternPayload;

    @Override
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    @ElementCollection
    @CollectionTable(name = "payloads", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "payload")
    public Map<String, String> getBookingPatternPayload() {
        return this.bookingPatternPayload;
    }

    @Override
    public void setBookingPatternPayload(Map<String, String> patternPayload) {
        this.bookingPatternPayload = patternPayload;
    }
}
