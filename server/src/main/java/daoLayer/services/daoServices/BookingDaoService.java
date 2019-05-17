package daoLayer.services.daoServices;

import daoLayer.sqlDao.BookingDao;
import factory.DaoFactory;
import models.booking.Booking;
import models.booking.metadata.BookingMetadata;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public class BookingDaoService {

    private BookingDao bookingDao;

    public BookingDaoService() {
        this.bookingDao = DaoFactory.getBookingDao();
    }

    public int saveBooking(Booking booking) {
        return this.bookingDao.write(booking);
    }

    public boolean updateBooking(Booking booking) {
        return this.bookingDao.updateBooking(booking);
    }

    public void deleteBooking(Booking booking) {
        this.bookingDao.delete(booking);
    }

    public boolean deleteBooking(int id) {
        return this.bookingDao.delete(id);
    }

    public Booking findBookingById(int id) {
        return this.bookingDao.read(id);
    }

    public List<Booking> findBookingsByStartEndDate(LocalDate start, LocalDate end) {
        return  this.bookingDao.findBookingsByStartEndDate(start, end);
    }

    public List<Booking> findAllBookings() {
        return this.bookingDao.findAllBookings();
    }

    public List<BookingMetadata> findAllBookingMetadata() {
        return this.bookingDao.findAllBookingMetadata();
    }
}
