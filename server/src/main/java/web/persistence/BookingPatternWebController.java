package web.persistence;

import daoLayer.services.BookingPatternItemService;
import daoLayer.services.BookingPatternPayloadService;
import daoLayer.services.BookingPatternService;
import daoLayer.services.BookingService;
import daoLayer.services.exceptions.LedgerServiceException;
import factory.ServiceFactory;
import models.patternBooking.interfaces.BookingPattern;
import models.patternBooking.interfaces.BookingPatternItem;
import models.patternBooking.metaData.BookingPatternMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingPatternWebController {

    @Autowired
    BookingPatternService bookingPatternService;

    @Autowired
    BookingPatternItemService bookingPatternItemService;

    @Autowired
    BookingPatternPayloadService patternPayloadService;

    BookingService bookingService;

    public BookingPatternWebController() {
        this.bookingService = ServiceFactory.getBookingService();
    }

    @PostMapping("/bookingPatterns")
    public String saveBookingPattern(@RequestBody BookingPattern pattern) {
        this.bookingPatternService.saveBookingPattern(pattern);
        return "OK";
    }

    @GetMapping("/bookingPatterns")
    public List<BookingPatternMetadata> getAllBookingMeta() {
        return this.bookingPatternService.findAllBookingMeta();
    }

    @GetMapping("/bookingPatterns/{categoryId}")
    public List<BookingPatternMetadata> getBookingMetaByCategory(@PathVariable int categoryId) {
        return this.bookingPatternService.findBookingMetaByCategory(categoryId);
    }

    @PostMapping("bookingPatterns/{patternId}")
    public String saveBookingPatternItem(@PathVariable int patternId, @RequestBody BookingPatternItem patternItem) throws LedgerServiceException {
        patternItem.getBooking().setId(this.bookingService.book(patternItem.getBooking()));
        if(patternItem.getPayload() != null && this.bookingPatternService.findBookingMetadataById(patternId).isPattern()) {
            patternItem.getPayload().setId(this.patternPayloadService.savePatternPayload(patternItem.getPayload()));
        }
        int patternItemId = this.bookingPatternItemService.saveBookingPatternItem(patternItem);
        this.bookingPatternService.connectPatternItemToPattern(patternId, patternItemId);
        return "OK";
    }
}
