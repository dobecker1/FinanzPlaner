package daoLayer.services;

import booking.BookingHelper;
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
    private BookingHelper bookingHelper;

    public int saveBooking(Booking booking) {
        return this.bookingDaoService.saveBooking(booking);
    }

    public boolean updateBooking(Booking booking) {

        if(bookingHelper.undoOldBooking(booking)) {
            //TODO update Booking in Dao
            return true;
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
