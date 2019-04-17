package daoLayer.services;

import daoLayer.services.daoServices.BookingPatternDaoService;
import models.patternBooking.interfaces.BookingPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookingPatternService")
public class BookingPatternService {

    @Autowired
    private BookingPatternDaoService bookingPatternDaoService;

    public int saveBookingPattern(BookingPattern bookingPattern) {
        return this.bookingPatternDaoService.saveBookingPattern(bookingPattern);
    }

    public void deleteBookingPattern(BookingPattern bookingPattern) {
        this.bookingPatternDaoService.deleteBookingPattern(bookingPattern);
    }

    public void deleteBookingPattern(int id) {
        this.bookingPatternDaoService.deleteBookingPattern(id);
    }


}
