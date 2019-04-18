package daoLayer.services.daoServices;

import daoLayer.sqlDao.BookingPatternItemDao;
import factory.DaoFactory;
import models.patternBooking.interfaces.BookingPatternItem;
import org.springframework.stereotype.Service;

@Service
public class BookingPatternItemDaoService {

    private BookingPatternItemDao patternItemDao;

    public BookingPatternItemDaoService() {
        this.patternItemDao = DaoFactory.getBookingPatternItemDao();
    }

    public int savePatternItem(BookingPatternItem patternItem) {
        return this.patternItemDao.write(patternItem);
    }

    public void deletePatternItem(BookingPatternItem patternItem) {
        this.patternItemDao.delete(patternItem);
    }

    public BookingPatternItem findPatternItemById(int id) {
        return this.patternItemDao.read(id);
    }
}
