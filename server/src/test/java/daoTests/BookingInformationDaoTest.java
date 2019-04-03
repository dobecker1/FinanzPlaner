package daoTests;

import daoLayer.dao.BookingInformationDao;
import daoLayer.dao.LedgerDao;
import factory.ModelFactory;
import models.ledger.Ledger;
import models.patternBooking.interfaces.BookingInformation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingInformationDaoTest {

    private final BookingInformationDao bookingInformationDao = new BookingInformationDao();
    private final LedgerDao ledgerDao = new LedgerDao();
    private BookingInformation bookingInformation;
    private Ledger ledgerShould;
    private Ledger ledgerHave;

    @BeforeEach
    void createBookingInfo() {
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
    }

    @AfterEach
    void deleteBookingInfo() {
        this.bookingInformationDao.deleteBookingInformation(this.bookingInformation);
        this.ledgerDao.deleteLedger(this.ledgerShould);
        this.ledgerDao.deleteLedger(this.ledgerHave);
    }

    @Test
    void writeBookingInfoTest() {
        this.ledgerDao.write(this.ledgerShould);
        this.ledgerDao.write(this.ledgerHave);
        this.bookingInformationDao.write(this.bookingInformation);
        BookingInformation info = this.bookingInformationDao.read(this.bookingInformation.getId());
        assertEquals(this.bookingInformation.getBookingDescription(), info.getBookingDescription());
        assertEquals(this.bookingInformation.getLedgerShould().getLedgerNumber(), info.getLedgerShould().getLedgerNumber());
        assertEquals(this.bookingInformation.getLedgerHave().getLedgerNumber(), info.getLedgerHave().getLedgerNumber());
        assertEquals(this.bookingInformation.getValue(), info.getValue());
    }
}
