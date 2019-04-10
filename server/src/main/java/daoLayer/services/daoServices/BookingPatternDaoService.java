package daoLayer.services.daoServices;

import daoLayer.sqlDao.BookingPatternDao;
import factory.DaoFactory;
import models.patternBooking.interfaces.BookingPattern;

public class BookingPatternDaoService {

    private BookingPatternDao patternDao;

    public BookingPatternDaoService() {
        this.patternDao = DaoFactory.getBookingPatternDao();
    }

    public int saveBookingPattern(BookingPattern bookingPattern) {
        return this.patternDao.write(bookingPattern);
    }

    public void deleteBookingPattern(BookingPattern bookingPattern) {
        this.patternDao.delete(bookingPattern);
    }

    public BookingPattern findBookingPatternById(int id) {
        return this.patternDao.read(id);
    }
}
