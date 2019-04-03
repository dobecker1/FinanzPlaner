package daoTests;

import daoLayer.dao.*;
import factory.ModelFactory;
import models.booking.Booking;
import models.category.Category;
import models.ledger.Ledger;
import models.patternBooking.interfaces.BookingInformation;
import models.patternBooking.interfaces.BookingPattern;
import models.patternBooking.interfaces.BookingPatternItem;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingPatternDaoTest extends BasicDao {

    private final BookingInformationDao bookingInfoDao = new BookingInformationDao();
    private final BookingPatternDao bookingPatternDao = new BookingPatternDao();
    private final LedgerDao ledgerDao = new LedgerDao();
    private final CategoryDao categoryDao = new CategoryDao();
    private final BookingDao bookingDao = new BookingDao();
    private BookingPattern bookingPattern;
    private BookingInformation bookingInformation;
    private Ledger ledgerShould;
    private Ledger ledgerHave;
    private Category category;

    private Booking booking;
    private Booking booking1;
    private Booking booking2;

    BookingPatternItem patternItem;
    BookingPatternItem patternItem1;
    BookingPatternItem patternItem2;


    @BeforeEach
    void createBookingPattern() {
        this.category = ModelFactory.getCategory();
        this.category.setName("Test Kategorie");

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

        this.bookingInformation = ModelFactory.getBookingInformation();
        this.bookingInformation.setBookingDescription("Test Description");
        this.bookingInformation.setLedgerShould(this.ledgerShould);
        this.bookingInformation.setLedgerHave(this.ledgerHave);
        this.bookingInformation.setValue(29.99);

        this.bookingPattern = ModelFactory.getBookingPattern();
        this.bookingPattern.setBookingInformation(this.bookingInformation);
        this.bookingPattern.setCategory(this.category);
        this.bookingPattern.setExecutionDate(new Date());
        this.bookingPattern.setName("Test Pattern");
        this.bookingPattern.setExecutionDatePattern("30 Tage");
    }

    @AfterEach
    void deleteBookingPattern() {
        this.bookingPatternDao.deleteBookingPattern(this.bookingPattern);
        this.bookingInfoDao.deleteBookingInformation(this.bookingInformation);
        this.categoryDao.deleteCategory(this.category);
        this.ledgerDao.deleteLedger(this.ledgerShould);
        this.ledgerDao.deleteLedger(this.ledgerHave);
    }

    @Test
    void writeBookingPatternTest() {
        this.categoryDao.write(this.category);
        this.ledgerDao.write(this.ledgerHave);
        this.ledgerDao.write(this.ledgerShould);
        this.bookingInfoDao.write(this.bookingInformation);
        this.bookingPatternDao.write(this.bookingPattern);

        BookingPattern pattern = this.bookingPatternDao.read(this.bookingPattern.getId());
        assertEquals(this.bookingPattern.getName(), pattern.getName());
        assertEquals(this.bookingPattern.getCategory().getId(), pattern.getCategory().getId());
        assertEquals(this.bookingPattern.getBookingInformation().getLedgerShould().getLedgerNumber(), pattern.getBookingInformation().getLedgerShould().getLedgerNumber());
        assertEquals(this.bookingPattern.getExecutionDatePattern(), pattern.getExecutionDatePattern());
    }

    @Test
    void writeBookingPatternItemTest() {
        this.ledgerDao.write(this.ledgerHave);
        this.ledgerDao.write(this.ledgerShould);

        Booking booking = ModelFactory.getBooking();
        booking.setLedgerShould(this.ledgerShould);
        booking.setLedgerHave(this.ledgerHave);
        booking.setValue(200);
        booking.setDate(new Date());
        booking.setBookingDescription("Booking Description");
        booking.setReferenceNumber("S03");
        this.bookingDao.write(booking);

        BookingPatternItem patternItem = ModelFactory.getBookingPatternItem();
        patternItem.setBooking(booking);
        patternItem.setPayload("{'inputs': ['TestField': 200, 'TestField2': 350]}");
        this.bookingPatternDao.writeBookingPatternItem(patternItem);

        BookingPatternItem savedPatternItem = this.bookingPatternDao.readBookingPatternItem(patternItem.getId());
        assertEquals(patternItem.getBooking().getDate(), savedPatternItem.getBooking().getDate());
        assertEquals(patternItem.getPayload(), savedPatternItem.getPayload());
        assertEquals(patternItem.getBooking().getLedgerShould().getLedgerNumber(), savedPatternItem.getBooking().getLedgerShould().getLedgerNumber());
        assertEquals(patternItem.getBooking().getReferenceNumber(), savedPatternItem.getBooking().getReferenceNumber());
        this.bookingPatternDao.deletePatternItem(patternItem);
        this.bookingDao.deleteBooking(booking);
    }

    @Test
    void getAllPatternItemsTest() {
        this.ledgerDao.write(this.ledgerHave);
        this.ledgerDao.write(this.ledgerShould);

        Booking booking = ModelFactory.getBooking();
        booking.setLedgerShould(this.ledgerShould);
        booking.setLedgerHave(this.ledgerHave);
        booking.setValue(200);
        booking.setDate(new Date());
        booking.setBookingDescription("Booking Description");
        booking.setReferenceNumber("S03");
        this.bookingDao.write(booking);

        BookingPatternItem patternItem = ModelFactory.getBookingPatternItem();
        patternItem.setBooking(booking);
        patternItem.setPayload("{'inputs': ['TestField': 200, 'TestField2': 350]}");
        BookingPatternItem patternItem1 = ModelFactory.getBookingPatternItem();
        patternItem1.setBooking(booking);
        patternItem1.setPayload("binput");
        BookingPatternItem patternItem2 = ModelFactory.getBookingPatternItem();
        patternItem2.setBooking(booking);
        patternItem2.setPayload("input 2");
        this.bookingPatternDao.writeBookingPatternItem(patternItem);
        this.bookingPatternDao.writeBookingPatternItem(patternItem1);
        this.bookingPatternDao.writeBookingPatternItem(patternItem2);
        List<BookingPatternItem> patternItems = this.bookingPatternDao.getAllPatternItems();
        assertEquals(3, patternItems.size());
        this.bookingPatternDao.deletePatternItem(patternItem);
        this.bookingPatternDao.deletePatternItem(patternItem1);
        this.bookingPatternDao.deletePatternItem(patternItem2);
        this.bookingDao.deleteBooking(booking);
    }

    @Test
    void getPatternItemsByStartEndDateTest() {
        this.ledgerDao.write(this.ledgerHave);
        this.ledgerDao.write(this.ledgerShould);

        this.booking = ModelFactory.getBooking();
        Calendar calendar = Calendar.getInstance();
        booking.setLedgerShould(this.ledgerShould);
        booking.setLedgerHave(this.ledgerHave);
        booking.setValue(200);
        calendar.set(2019, Calendar.APRIL, 3);
        booking.setDate(calendar.getTime());
        booking.setBookingDescription("Booking Description");
        booking.setReferenceNumber("S03");
        this.booking1 = ModelFactory.getBooking();
        booking1.setLedgerShould(this.ledgerShould);
        booking1.setLedgerHave(this.ledgerHave);
        booking1.setValue(200);
        calendar.set(2019, Calendar.APRIL, 10);
        booking1.setDate(calendar.getTime());
        booking1.setBookingDescription("Booking Description");
        booking1.setReferenceNumber("S03");
        this.booking2 = ModelFactory.getBooking();
        booking2.setLedgerShould(this.ledgerShould);
        booking2.setLedgerHave(this.ledgerHave);
        booking2.setValue(200);
        calendar.set(2019, Calendar.APRIL, 25);
        booking2.setDate(calendar.getTime());
        booking2.setBookingDescription("Booking Description");
        booking2.setReferenceNumber("S03");
        this.bookingDao.write(booking);
        this.bookingDao.write(booking1);
        this.bookingDao.write(booking2);

        this.patternItem = ModelFactory.getBookingPatternItem();
        patternItem.setBooking(booking);
        patternItem.setPayload("{'inputs': ['TestField': 200, 'TestField2': 350]}");
        this.patternItem1 = ModelFactory.getBookingPatternItem();
        patternItem1.setBooking(booking1);
        patternItem1.setPayload("binput");
        this.patternItem2 = ModelFactory.getBookingPatternItem();
        patternItem2.setBooking(booking2);
        patternItem2.setPayload("input 2");
        this.bookingPatternDao.writeBookingPatternItem(patternItem);
        this.bookingPatternDao.writeBookingPatternItem(patternItem1);
        this.bookingPatternDao.writeBookingPatternItem(patternItem2);
        List<BookingPatternItem> patternItems = this.bookingPatternDao.
                getPatternItemsByStartEndDate(patternItem.getBooking().getDate(), patternItem1.getBooking().getDate());
        assertEquals(2, patternItems.size());
        this.bookingPatternDao.deletePatternItem(patternItem);
        this.bookingPatternDao.deletePatternItem(patternItem1);
        this.bookingPatternDao.deletePatternItem(patternItem2);
        this.bookingDao.deleteBooking(booking);
        this.bookingDao.deleteBooking(booking1);
        this.bookingDao.deleteBooking(booking2);
    }

    @After
    void deletetDBDependencies() {

    }

}
