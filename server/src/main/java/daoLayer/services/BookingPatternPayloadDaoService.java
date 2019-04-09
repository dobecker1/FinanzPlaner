package daoLayer.services;

import daoLayer.sqlDao.BookingPatternPayloadDao;
import factory.DaoFactory;
import models.patternBooking.interfaces.BookingPatternPayload;

public class BookingPatternPayloadDaoService {

    private BookingPatternPayloadDao patternPayloadDao;

    public BookingPatternPayloadDaoService() {
        this.patternPayloadDao = DaoFactory.getBookingPatternPayloadDao();
    }

    public BookingPatternPayload findPatternPayloadById(int id) {
        return this.patternPayloadDao.read(id);
    }

    public int saveBookingPatternPayload(BookingPatternPayload payload) {
        return this.patternPayloadDao.write(payload);
    }

    public void deleteBookingPayload(BookingPatternPayload payload) {
        this.patternPayloadDao.delete(payload);
    }
}
