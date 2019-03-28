package daoTests;

import daoLayer.dao.BookingDao;
import daoLayer.dao.LedgerDao;
import factory.ModelFactory;
import models.booking.Booking;
import models.ledger.Ledger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

public class BookingTest {

    private final LedgerDao ledgerDao = new LedgerDao();
    private final BookingDao bookingDao = new BookingDao();

    private Booking booking;
    private Ledger ledgerShould;
    private Ledger ledgerHave;

    @BeforeEach
    void createLedgersAndBooking() {
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

        this.booking = ModelFactory.getBooking();
        this.booking.setBookingDescription("Test Booking Description");
        this.booking.setDate(new Date());
        this.booking.setFinancialYear("2018");
        this.booking.setLedgerHave(this.ledgerHave);
        this.booking.setLedgerShould(this.ledgerShould);
        this.booking.setReferenceNumber("S01");
        this.booking.setValue(200);
    }

    @Test
    void writeBookingTest() {
        this.ledgerDao.write(this.ledgerShould);
        this.ledgerDao.write(this.ledgerHave);
        this.bookingDao.write(booking);
        Booking savedBooking = this.bookingDao.read(this.booking.getId());
        assertEquals(this.booking.getBookingDescription(), savedBooking.getBookingDescription());
        this.bookingDao.deleteBooking(this.booking.getId());
        this.ledgerDao.deleteLedger(this.ledgerShould.getId());
        this.ledgerDao.deleteLedger(this.ledgerHave.getId());
    }

}
