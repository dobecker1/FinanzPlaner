package booking;

import daoLayer.services.BookingService;
import daoLayer.services.LedgerService;
import daoLayer.services.exceptions.LedgerServiceException;
import factory.ModelFactory;
import factory.ServiceFactory;
import models.booking.Booking;
import models.booking.metadata.BookingMetadata;

public class BookingHelper {

    private BookingService bookingService;
    private LedgerService ledgerService;

    public BookingHelper(BookingService bookingService) {
        this.bookingService = bookingService;
        this.ledgerService = ServiceFactory.getLedgerService();
    }

    public int book(Booking booking) throws LedgerServiceException {
        this.ledgerService.changeLedgerValue(booking.getLedgerShould(), booking.getValue());
        this.ledgerService.changeLedgerValue(booking.getLedgerHave(), -booking.getValue());
        if(booking.getSubLedgerShould() != null) {
            this.ledgerService.changeLedgerValue(booking.getSubLedgerShould(), booking.getValue());
        }
        if(booking.getSubLedgerHave() != null) {
            this.ledgerService.changeLedgerValue(booking.getSubLedgerHave(), -booking.getValue());
        }
        return this.bookingService.saveBooking(booking);
    }

    public boolean updateLedgers(Booking booking) throws LedgerServiceException {
        Booking oldBooking = this.bookingService.getBookingById(booking.getId());

        //undo changes on ledgers
        //this.ledgerService.changeLedgerValue(oldBooking.getLedgerShould(), -oldBooking.getValue());
        //this.ledgerService.changeLedgerValue(oldBooking.getLedgerHave(), oldBooking.getValue());
        resetBookingLedgers(oldBooking);

        //refresh ledgers of new Booking and change value of subLedgers
        booking.setLedgerShould(this.ledgerService.getLedgerById(booking.getLedgerShould().getId()));
        booking.setLedgerHave(this.ledgerService.getLedgerById(booking.getLedgerHave().getId()));
        if(booking.getSubLedgerShould() != null) {
            booking.setSubLedgerShould(this.ledgerService.getLedgerById(booking.getSubLedgerShould().getId()));
            this.ledgerService.changeLedgerValue(booking.getSubLedgerShould(), booking.getValue());
        }
        if(booking.getSubLedgerHave() != null) {
            booking.setSubLedgerHave(this.ledgerService.getLedgerById(booking.getSubLedgerHave().getId()));
            this.ledgerService.changeLedgerValue(booking.getSubLedgerHave(), booking.getValue());
        }

        //change Values of ledgers
        this.ledgerService.changeLedgerValue(booking.getLedgerShould(), booking.getValue());
        this.ledgerService.changeLedgerValue(booking.getLedgerHave(), -booking.getValue());
        return true;
    }

    public void resetBookingLedgers(Booking booking) throws LedgerServiceException {
        this.ledgerService.changeLedgerValue(booking.getLedgerShould(), -booking.getValue());
        this.ledgerService.changeLedgerValue(booking.getLedgerHave(), booking.getValue());
        if(booking.getSubLedgerShould() != null) {
            this.ledgerService.changeLedgerValue(booking.getSubLedgerShould(), -booking.getValue());
        }
        if(booking.getSubLedgerHave() != null) {
            this.ledgerService.changeLedgerValue(booking.getSubLedgerHave(), booking.getValue());
        }
    }

    public Booking convertMetadataToBooking(BookingMetadata bookingMetadata) {
        Booking booking = ModelFactory.getBooking();
        booking.setId(bookingMetadata.getId());
        booking.setReferencePath(bookingMetadata.getReferencePath());
        booking.setFinancialYear(bookingMetadata.getFinancialYear());
        booking.setValue(bookingMetadata.getValue());
        booking.setReferenceNumber(bookingMetadata.getReferenceNumber());
        booking.setBookingDescription(bookingMetadata.getBookingDescription());
        booking.setDate(bookingMetadata.getDate());
        booking.setLedgerShould(this.ledgerService.getLedgerById(bookingMetadata.getLedgerShould()));
        booking.setLedgerHave(this.ledgerService.getLedgerById(bookingMetadata.getLedgerHave()));
        if(bookingMetadata.getSubLedgerShould() > 0) {
            booking.setSubLedgerShould(this.ledgerService.getLedgerById(bookingMetadata.getSubLedgerShould()));
        }
        if(bookingMetadata.getSubLedgerHave() > 0) {
            booking.setSubLedgerHave(this.ledgerService.getLedgerById(bookingMetadata.getSubLedgerHave()));
        }
        return booking;
    }
}
