package daoLayer.services;

import daoLayer.services.daoServices.BookingPatternDaoService;
import models.patternBooking.interfaces.BookingPattern;
import models.patternBooking.metadata.BookingPatternMetadata;
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

    public List<BookingPatternMetadata> findAllBookingMeta() {
        return this.bookingPatternDaoService.findAllBookingMeta();
    }

    public List<BookingPatternMetadata> findBookingMetaByCategory(int categoryId) {
        return this.bookingPatternDaoService.findBookingMetaByCategory(categoryId);
    }

    public BookingPatternMetadata findBookingMetadataById(int id) {
        return this.bookingPatternDaoService.findBookingMetadataById(id);
    }

    public void connectPatternItemToPattern(int patternId, int patternItemId) {
        this.bookingPatternDaoService.connectPatternItemToPattern(patternId, patternItemId);
    }

}
