package daoTests;

import daoLayer.services.daoServices.LedgerDaoService;
import daoLayer.services.exceptions.LedgerServiceException;
import daoLayer.sqlDao.BookingDao;
import factory.ModelFactory;
import models.booking.Booking;
import models.booking.metadata.BookingMetadata;
import models.ledger.Ledger;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookingDaoTest {


    private LedgerDaoService ledgerDaoService = new LedgerDaoService();
    private BookingDao bookingDao = new BookingDao();

    private Booking booking;
    private Ledger ledgerShould;
    private Ledger ledgerHave;

    @BeforeEach
    void createLedgersAndBooking() throws LedgerServiceException {
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
        this.booking.setBookingDescription("Test Booking Description");
        LocalDate date = LocalDate.of(2019,1,1);
        this.booking.setDate(date);
        this.booking.setFinancialYear("2018");
        this.booking.setReferenceNumber("S01");
        this.booking.setValue(200);
    }

    @AfterEach
    void deleteLedgersAndBooking() {
        this.ledgerDaoService.deleteLedger(this.ledgerShould);
        this.ledgerDaoService.deleteLedger(this.ledgerHave);
    }

    @Test
    void writeBookingTest() {
        this.booking.setLedgerShould(this.ledgerShould);
        this.booking.setLedgerHave(this.ledgerHave);
        this.bookingDao.write(booking);
        Booking savedBooking = this.bookingDao.findAllBookings().get(0);
        assertEquals(this.booking.getBookingDescription(), savedBooking.getBookingDescription());
        assertEquals(this.booking.getLedgerShould().getLedgerNumber(), savedBooking.getLedgerShould().getLedgerNumber());
        this.bookingDao.delete(savedBooking);
    }


    @Test
    void findBookingsByStartEndDateTest() {
        this.booking.setLedgerShould(this.ledgerShould);
        this.booking.setLedgerHave(this.ledgerHave);
        this.booking.setId(this.bookingDao.write(booking));
        List<Booking> bookings = this.createBookingList();
        for(Booking booking : bookings) {
            this.bookingDao.write(booking);
        }
        LocalDate start = LocalDate.of(2019,1,1);
        LocalDate end = LocalDate.of(2019,6,1);
        List<Booking> savedBookings = this.bookingDao.findBookingsByStartEndDate(start, end);
        assertEquals(2, savedBookings.size());
        for(Booking booking : this.bookingDao.findAllBookings()) {
            this.bookingDao.delete(booking);
        }
    }

    @Test
    void updateBookingTest() throws LedgerServiceException {
        this.booking.setLedgerShould(this.ledgerShould);
        this.booking.setLedgerHave(this.ledgerHave);
        this.booking.setId(this.bookingDao.write(booking));
        this.booking.setBookingDescription("Updated Descr");
        this.booking.setReferenceNumber("Updated Ref");
        this.booking.setValue(999);
        this.booking.setFinancialYear("Updated Year");
        this.booking.setReferencePath("Updated Path");
        Ledger shouldLedgerNew = ModelFactory.getLedger();
        shouldLedgerNew.setValue(555);
        shouldLedgerNew.setName("New ledger");
        shouldLedgerNew.setLedgerNumber(999);
        shouldLedgerNew.setSubLedger(false);
        shouldLedgerNew.setId(this.ledgerDaoService.saveLedger(shouldLedgerNew));
        this.booking.setLedgerShould(shouldLedgerNew);
        this.bookingDao.updateBooking(this.booking);
        Booking updatedBooking = this.bookingDao.read(this.booking.getId());
        assertEquals("Updated Descr", updatedBooking.getBookingDescription());
        assertEquals("Updated Ref", updatedBooking.getReferenceNumber());
        assertEquals(999, updatedBooking.getValue());
        assertEquals("Updated Year", updatedBooking.getFinancialYear());
        assertEquals("Updated Path", updatedBooking.getReferencePath());
        assertEquals(999, updatedBooking.getLedgerShould().getLedgerNumber());
        this.bookingDao.delete(this.booking);
        this.ledgerDaoService.deleteLedger(shouldLedgerNew);
    }

    @Test
    void findAllBookingMetadataTest() {
        this.booking.setLedgerShould(this.ledgerShould);
        this.booking.setLedgerHave(this.ledgerHave);
        this.booking.setId(this.bookingDao.write(this.booking));

        for(Booking book : this.createBookingList()) {
            this.bookingDao.write(book);
        }
        List<BookingMetadata> bookings = this.bookingDao.findAllBookingMetadata();
        assertEquals(3, bookings.size());
        for(BookingMetadata meta : bookings) {
            assertEquals(this.ledgerShould.getId(), meta.getLedgerShould());
            assertEquals(this.ledgerHave.getId(), meta.getLedgerHave());
            this.bookingDao.delete(meta.getId());
        }
    }

    private List<Booking> createBookingList() {
        List<Booking> bookings = new ArrayList<>();

        Booking booking1 = ModelFactory.getBooking();
        booking1.setLedgerHave(this.ledgerHave);
        booking1.setLedgerShould(this.ledgerShould);
        booking1.setFinancialYear("2019");
        booking1.setValue(200);
        LocalDate localDate1 = LocalDate.of(2019, 5,1);
        booking1.setDate(localDate1);
        booking1.setReferenceNumber("S04");
        booking1.setBookingDescription("Booking 1 Description");

        Booking booking2 = ModelFactory.getBooking();
        booking2.setLedgerHave(this.ledgerHave);
        booking2.setLedgerShould(this.ledgerShould);
        booking2.setFinancialYear("2019");
        booking2.setValue(500);
        LocalDate localDate2 = LocalDate.of(2019, 6, 15);
        booking2.setDate(localDate2);
        booking2.setReferenceNumber("S04");
        booking2.setBookingDescription("Booking 2 Description");

        bookings.add(booking1);
        bookings.add(booking2);

        return bookings;
    }

}
