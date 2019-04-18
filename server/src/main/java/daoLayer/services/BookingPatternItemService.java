package daoLayer.services;

import daoLayer.services.daoServices.BookingPatternItemDaoService;
import models.patternBooking.interfaces.BookingPatternItem;
import models.patternBooking.metaData.BookingPatternItemMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingPatternItemService {

    @Autowired
    private BookingPatternItemDaoService patternItemDaoService;


    public int saveBookingPatternItem(BookingPatternItem patternItem) {
        return this.patternItemDaoService.savePatternItem(patternItem);
    }
}
