package daoLayer.services;

import daoLayer.services.daoServices.BookingPatternPayloadDaoService;
import models.patternBooking.interfaces.BookingPatternPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingPatternPayloadService {

    @Autowired
    private BookingPatternPayloadDaoService patternPayloadDaoService;

    public int savePatternPayload(BookingPatternPayload payload) {
       return this.patternPayloadDaoService.saveBookingPatternPayload(payload);
    }
}
