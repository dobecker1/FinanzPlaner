package daoTests;

import daoLayer.dao.BookingPatternPayloadDao;
import factory.ModelFactory;
import models.patternBooking.interfaces.BookingPatternPayload;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingPatternPayloadDaoTest {

    private final BookingPatternPayloadDao patternPayloadDao = new BookingPatternPayloadDao();
    private BookingPatternPayload patternPayload;

    @BeforeEach
    void createPatternPayload() {
        this.patternPayload = ModelFactory.getBookingPatternPayload();
        Map<String, String> payload = new HashMap<>();
        payload.put("inputName", "inputValue");
        payload.put("inputDate", new Date().toString());
        payload.put("inputNumber", "9");
        this.patternPayload.setBookingPatternPayload(payload);
    }

    @AfterEach
    void deletePatternPayload() {
        this.patternPayloadDao.delete(this.patternPayload);
    }

    @Test
    void writeBookingPatternPayloadTest() {
        this.patternPayloadDao.write(this.patternPayload);
        BookingPatternPayload savedPatternPayload = this.patternPayloadDao.read(this.patternPayload.getId());
        savedPatternPayload.getBookingPatternPayload().size();
        assertEquals(this.patternPayload.getBookingPatternPayload().get("inputName"),
                savedPatternPayload.getBookingPatternPayload().get("inputName"));
        assertEquals(this.patternPayload.getBookingPatternPayload().get("inputDate"),
                savedPatternPayload.getBookingPatternPayload().get("inputDate"));
        assertEquals(this.patternPayload.getBookingPatternPayload().get("inputNumber"),
                savedPatternPayload.getBookingPatternPayload().get("inputNumber"));
    }
}
