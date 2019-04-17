package daoLayer.services.daoServices;

import daoLayer.sqlDao.BookingDao;
import factory.DaoFactory;
import models.booking.Booking;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("bookingDaoService")
public class BookingDaoService {

    private BookingDao bookingDao;

    public BookingDaoService() {
        this.bookingDao = DaoFactory.getBookingDao();
    }

    public int saveBooking(Booking booking) {
        return this.bookingDao.write(booking);
    }

    public void deleteBooking(Booking booking) {
        this.bookingDao.delete(booking);
    }

    public void deleteBooking(int id) {
        this.bookingDao.delete(id);
    }

    public Booking findBookingById(int id) {
        return this.bookingDao.read(id);
    }

    public List<Booking> findBookingsByStartEndDate(LocalDate start, LocalDate end) {
        return  this.bookingDao.findBookingsByStartEndDate(start, end);
    }
}
