package daoLayer.services.daoServices;

import daoLayer.sqlDao.BookingInformationDao;
import factory.DaoFactory;
import models.patternBooking.interfaces.BookingInformation;

public class BookingInformationDaoService {

    private BookingInformationDao bookingInformationDao;

    public BookingInformationDaoService() {
        this.bookingInformationDao = DaoFactory.getBookingInformationDao();
    }

    public int saveBookingInformation(BookingInformation bookingInformation) {
        return this.bookingInformationDao.write(bookingInformation);
    }

    public void deleteBookingInfo(BookingInformation bookingInformation) {
        this.bookingInformationDao.delete(bookingInformation);
    }

    public BookingInformation findBookingInfoById(int id) {
        return this.bookingInformationDao.read(id);
    }
}
