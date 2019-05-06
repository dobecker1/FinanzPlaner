package serviceTests;

import daoLayer.services.BookingService;
import daoLayer.services.LedgerService;
import factory.ModelFactory;
import factory.ServiceFactory;
import models.booking.Booking;
import models.ledger.Ledger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingServiceTest {

    private BookingService bookingService = ServiceFactory.getBookingService();
    private LedgerService ledgerService = ServiceFactory.getLedgerService();
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
        this.ledgerHave.setValue(150);
        this.ledgerHave.setLedgerNumber(456);

        this.ledgerShould.setId(this.ledgerService.saveLedger(this.ledgerShould));
        this.ledgerHave.setId(this.ledgerService.saveLedger(this.ledgerHave));

        this.booking = ModelFactory.getBooking();
        this.booking.setBookingDescription("Test Booking Description");
        LocalDate date = LocalDate.of(2019,1,1);
        this.booking.setDate(date);
        this.booking.setFinancialYear("2018");
        this.booking.setReferenceNumber("S01");
        this.booking.setValue(75);
        this.booking.setLedgerShould(this.ledgerShould);
        this.booking.setLedgerHave(this.ledgerHave);
    }

    @AfterEach
    void deleteLedgersAndBooking() {
        this.ledgerService.deleteLedger(this.ledgerShould);
        this.ledgerService.deleteLedger(this.ledgerHave);
    }

    @Test
    void bookTest() {
        this.booking.setId(this.bookingService.book(this.booking));
        Ledger savedShould = this.ledgerService.getLedgerById(this.ledgerShould.getId());
        Ledger savedHave = this.ledgerService.getLedgerById(this.ledgerHave.getId());
        assertEquals(75, savedShould.getValue());
        assertEquals(75, savedHave.getValue());
        this.bookingService.deleteBooking(this.booking);
    }

    @Test
    void updateBookingTest() {
        this.booking.setId(this.bookingService.book(this.booking));
        Ledger shouldLedgerNew = ModelFactory.getLedger();
        shouldLedgerNew.setLedgerNumber(444);
        shouldLedgerNew.setValue(100);
        shouldLedgerNew.setName("New Ledger");
        shouldLedgerNew.setDescription("New Description");
        shouldLedgerNew.setSubLedger(false);
        shouldLedgerNew.setId(this.ledgerService.saveLedger(shouldLedgerNew));

        this.booking.setLedgerShould(shouldLedgerNew);
        this.bookingService.updateBooking(this.booking);
        Ledger savedShould = this.ledgerService.getLedgerById(this.ledgerShould.getId());
        Ledger savedHave = this.ledgerService.getLedgerById(this.ledgerHave.getId());
        Ledger savedNew = this.ledgerService.getLedgerById(shouldLedgerNew.getId());
        assertEquals(0, savedShould.getValue());
        assertEquals(75, savedHave.getValue());
        assertEquals(175, savedNew.getValue());
        this.bookingService.deleteBooking(this.booking);
        this.ledgerService.deleteLedger(shouldLedgerNew);
    }

}
