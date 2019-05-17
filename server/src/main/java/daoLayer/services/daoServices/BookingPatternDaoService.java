package daoLayer.services.daoServices;

import daoLayer.sqlDao.BookingPatternDao;
import factory.DaoFactory;
import models.patternBooking.interfaces.BookingPattern;
import models.patternBooking.metadata.BookingPatternMetadata;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookingPatternDaoService")
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

    public void deleteBookingPattern(int id) {
        this.patternDao.delete(id);
    }

    public BookingPattern findBookingPatternById(int id) {
        return this.patternDao.read(id);
    }

    public List<BookingPatternMetadata> findAllBookingMeta() {
        return this.patternDao.findAllBookingPatternMetadata();
    }

    public List<BookingPatternMetadata> findBookingMetaByCategory(int categoryId) {
        return this.patternDao.findBookingPatternMetaByCategory(categoryId);
    }

    public BookingPatternMetadata findBookingMetadataById(int patternId) {
        return this.patternDao.readMetadata(patternId);
    }

    public void connectPatternItemToPattern(int patternId, int patternItemId) {
        this.patternDao.connectBookingPatternItemToPattern(patternId, patternItemId);
    }
}
