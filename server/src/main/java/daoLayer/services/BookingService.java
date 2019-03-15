package daoLayer.services;

import daoLayer.dao.BookingDao;
import models.booking.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookingService")
public class BookingService {

    @Autowired
    private BookingDao bookingDao;

    public void saveBooking(Booking booking) {
        this.bookingDao.write(booking);
    }

    public List<Booking> getAllBookings() {
        return this.bookingDao.findAllBookings();
    }

}
