package daoLayer.services;

import booking.BookingHelper;
import daoLayer.services.daoServices.BookingDaoService;
import factory.ServiceFactory;
import models.booking.Booking;

import java.time.LocalDate;
import java.util.List;

public class BookingService {

    private BookingDaoService bookingDaoService;
    private BookingHelper bookingHelper;

    public BookingService() {
        this.bookingDaoService = ServiceFactory.getBookingDaoService();
        this.bookingHelper = new BookingHelper(this);
    }

    public int saveBooking(Booking booking) {
        return this.bookingDaoService.saveBooking(booking);
    }

    public boolean updateBooking(Booking booking) {

        if(bookingHelper.updateLedgers(booking)) {
            return this.bookingDaoService.updateBooking(booking);
        } else {
            return false;
        }
    }

    public int book(Booking booking) {
        return this.bookingHelper.book(booking);
    }

    public void deleteBooking(Booking booking) {
        this.bookingDaoService.deleteBooking(booking);
    }

    public boolean deleteBooking(int id) {
        return this.bookingDaoService.deleteBooking(id);
    }

    public Booking getBookingById(int id) {
        return this.bookingDaoService.findBookingById(id);
    }

    public List<Booking> findBookingsByStartEndDate(LocalDate start, LocalDate end) {
        return this.bookingDaoService.findBookingsByStartEndDate(start, end);
    }


}
