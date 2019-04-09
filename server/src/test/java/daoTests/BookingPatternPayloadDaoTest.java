package daoTests;

import daoLayer.sqlDao.BookingPatternPayloadDao;
import factory.ModelFactory;
import models.patternBooking.interfaces.BookingPatternPayload;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

    }

    @Test
    void writeBookingPatternPayloadMapTest() {
        int mapId = this.patternPayloadDao.writeMapPayload(this.patternPayload.getBookingPatternPayload());
        Map<String, String> map = this.patternPayloadDao.findMapById(mapId);
        assertEquals(this.patternPayload.getBookingPatternPayload().size(), map.size());
        this.patternPayloadDao.deleteMap(mapId);
    }

    @Test
    void writeBookingPatternPayloadTest() {
        int patternPayloadId = this.patternPayloadDao.write(this.patternPayload);
        assertNotEquals(-1, patternPayloadId);
        BookingPatternPayload savedPatternPayload = this.patternPayloadDao.read(patternPayloadId);
        assertEquals(3, savedPatternPayload.getBookingPatternPayload().size());
        assertEquals("inputValue", savedPatternPayload.getBookingPatternPayload().get("inputName"));
        assertEquals("9", savedPatternPayload.getBookingPatternPayload().get("inputNumber"));
        this.patternPayloadDao.delete(savedPatternPayload);
    }
}
