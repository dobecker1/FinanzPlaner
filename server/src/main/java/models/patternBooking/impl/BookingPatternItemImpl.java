package models.patternBooking.impl;

import models.booking.Booking;
import models.booking.BookingImpl;
import models.patternBooking.interfaces.BookingPatternItem;

import javax.persistence.*;

@Entity
@Table(name = "BOOKING_PATTERN_ITEM")
public class BookingPatternItemImpl implements BookingPatternItem {

    private int id;
    private Booking booking;
    private String payload;


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
    @OneToOne(fetch = FetchType.EAGER, targetEntity = BookingImpl.class)
    @JoinColumn(name = "booking", nullable = false)
    public Booking getBooking() {
        return this.booking;
    }

    @Override
    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    @Column(name = "payload")
    public String getPayload() {
        return this.payload;
    }

    @Override
    public void setPayload(String payload) {
        this.payload = payload;
    }
}
