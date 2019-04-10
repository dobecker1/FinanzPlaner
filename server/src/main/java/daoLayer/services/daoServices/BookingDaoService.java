package daoLayer.services.daoServices;

import daoLayer.sqlDao.BookingDao;
import factory.DaoFactory;
import models.booking.Booking;

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

    public Booking findBookingById(int id) {
        return this.bookingDao.read(id);
    }
}
