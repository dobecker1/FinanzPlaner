package daoLayer.dao;

import models.patternBooking.impl.BookingPatternPayloadImpl;
import models.patternBooking.interfaces.BookingPatternPayload;

public class BookingPatternPayloadDao extends BasicDao {

    public void write(BookingPatternPayload payload) {
        super.write(payload);
    }

    public BookingPatternPayload read(int id) {
        return super.read(id, BookingPatternPayloadImpl.class);
    }

    public void delete(BookingPatternPayload payload) {
        super.delete(payload);
    }
}
