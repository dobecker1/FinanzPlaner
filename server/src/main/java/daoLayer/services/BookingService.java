package daoLayer.services;

import daoLayer.services.daoServices.BookingDaoService;
import models.booking.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("bookingService")
public class BookingService {

    @Autowired
    private BookingDaoService bookingDaoService;

    @Autowired
    private LedgerService ledgerService;

    public int saveBooking(Booking booking) {
        return this.bookingDaoService.saveBooking(booking);
    }

    public int book(Booking booking) {
        this.ledgerService.changeLedgerValue(booking.getLedgerShould(), booking.getValue());
        this.ledgerService.changeLedgerValue(booking.getLedgerHave(), -booking.getValue());
        return this.bookingDaoService.saveBooking(booking);
    }

    public void deleteBooking(Booking booking) {
        this.bookingDaoService.deleteBooking(booking);
    }

    public void deleteBooking(int id) {
        this.bookingDaoService.deleteBooking(id);
    }

    public Booking getBookingById(int id) {
        return this.bookingDaoService.findBookingById(id);
    }

    public List<Booking> findBookingsByStartEndDate(LocalDate start, LocalDate end) {
        return this.bookingDaoService.findBookingsByStartEndDate(start, end);
    }


}
