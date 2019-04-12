package daoTests;

import daoLayer.services.daoServices.BookingDaoService;
import daoLayer.services.daoServices.BookingPatternPayloadDaoService;
import daoLayer.services.daoServices.CategoryDaoService;
import daoLayer.services.daoServices.LedgerDaoService;
import daoLayer.sqlDao.BookingPatternItemDao;
import factory.DaoFactory;
import factory.ModelFactory;
import models.booking.Booking;
import models.category.Category;
import models.ledger.Ledger;
import models.patternBooking.interfaces.BookingPatternItem;
import models.patternBooking.interfaces.BookingPatternPayload;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingPatternItemDaoTest {

    private BookingDaoService bookingDaoService = new BookingDaoService();
    private BookingPatternPayloadDaoService patternPayloadDaoService = new BookingPatternPayloadDaoService();
    private LedgerDaoService ledgerDaoService = new LedgerDaoService();
    private CategoryDaoService categoryDaoService = new CategoryDaoService();
    private BookingPatternItemDao patternItemDao = DaoFactory.getBookingPatternItemDao();

    private Ledger ledgerShould;
    private Ledger ledgerHave;
    private Category category;

    private Booking booking;
    private BookingPatternPayload patternPayload;


    @BeforeEach
    void createBookingPatternItemObjects() {
    this.category = ModelFactory.getCategory();
    this.category.setName("Test Kategorie");

    this.category.setId(this.categoryDaoService.saveCategory(category));

    this.ledgerShould = ModelFactory.getLedger();
    this.ledgerHave = ModelFactory.getLedger();

    this.ledgerShould.setName("LedgerShould");
    this.ledgerShould.setDescription("LedgerShould Description");
    this.ledgerShould.setValue(0);
    this.ledgerShould.setLedgerNumber(123);

    this.ledgerHave.setName("LedgerHave");
    this.ledgerHave.setDescription("LedgerHave Description");
    this.ledgerHave.setValue(10);
    this.ledgerHave.setLedgerNumber(456);

    this.ledgerShould.setId(this.ledgerDaoService.saveLedger(this.ledgerShould));
    this.ledgerHave.setId(this.ledgerDaoService.saveLedger(this.ledgerHave));

    this.booking = ModelFactory.getBooking();
    this.booking.setLedgerShould(this.ledgerShould);
    this.booking.setLedgerHave(this.ledgerHave);
    this.booking.setValue(200);
    this.booking.setDate(LocalDate.now());
    this.booking.setBookingDescription("Booking Description");
    this.booking.setReferenceNumber("S03");
    this.booking.setId(this.bookingDaoService.saveBooking(this.booking));

    this.patternPayload = ModelFactory.getBookingPatternPayload();
    Map<String, String> payload = new HashMap<>();
    payload.put("inputName", "inputValue");
    payload.put("inputDate", new Date().toString());
    payload.put("inputNumber", "9");
    this.patternPayload.setBookingPatternPayload(payload);
    this.patternPayload.setId(this.patternPayloadDaoService.saveBookingPatternPayload(this.patternPayload));
    }

     @AfterEach
     void deleteBookingPatternItemObjects() {
        this.bookingDaoService.deleteBooking(this.booking);
        this.ledgerDaoService.deleteLedger(this.ledgerHave);
        this.ledgerDaoService.deleteLedger(this.ledgerShould);
        this.categoryDaoService.deleteCategory(this.category);
        this.patternPayloadDaoService.deleteBookingPayload(this.patternPayload);
     }

     @Test
     void writeBookingPatternItemTest() {
     BookingPatternItem patternItem = ModelFactory.getBookingPatternItem();
     patternItem.setBooking(booking);
     patternItem.setPayload(this.patternPayload);
     int patternItemId = this.patternItemDao.write(patternItem);

     BookingPatternItem savedPatternItem = this.patternItemDao.read(patternItemId);
     assertEquals(patternItem.getBooking().getDate().getDayOfMonth(), savedPatternItem.getBooking().getDate().getDayOfMonth());
     assertEquals(patternItem.getBooking().getDate().getMonth(), savedPatternItem.getBooking().getDate().getMonth());
     assertEquals(patternItem.getPayload().getBookingPatternPayload().size(), savedPatternItem.getPayload().getBookingPatternPayload().size());
     assertEquals(patternItem.getBooking().getLedgerShould().getLedgerNumber(), savedPatternItem.getBooking().getLedgerShould().getLedgerNumber());
     assertEquals(patternItem.getBooking().getReferenceNumber(), savedPatternItem.getBooking().getReferenceNumber());
     assertEquals(patternItem.getBooking().getValue(), savedPatternItem.getBooking().getValue());
     this.patternItemDao.delete(savedPatternItem);
     }
}
