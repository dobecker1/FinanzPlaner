package booking;

import daoLayer.services.BookingService;
import daoLayer.services.LedgerService;
import factory.ServiceFactory;
import models.booking.Booking;

public class BookingHelper {

    private BookingService bookingService;
    private LedgerService ledgerService;

    public BookingHelper(BookingService bookingService) {
        this.bookingService = bookingService;
        this.ledgerService = ServiceFactory.getLedgerService();
    }

    public int book(Booking booking) {
        this.ledgerService.changeLedgerValue(booking.getLedgerShould(), booking.getValue());
        this.ledgerService.changeLedgerValue(booking.getLedgerHave(), -booking.getValue());
        return this.bookingService.saveBooking(booking);
    }

    public boolean updateLedgers(Booking booking) {
        Booking oldBooking = this.bookingService.getBookingById(booking.getId());

        //undo changes on ledgers
        this.ledgerService.changeLedgerValue(oldBooking.getLedgerShould(), -oldBooking.getValue());
        this.ledgerService.changeLedgerValue(oldBooking.getLedgerHave(), oldBooking.getValue());
        //TODO change subledger values


        //refresh ledgers of new Booking
        booking.setLedgerShould(this.ledgerService.getLedgerById(booking.getLedgerShould().getId()));
        booking.setLedgerHave(this.ledgerService.getLedgerById(booking.getLedgerHave().getId()));

        //change Values of ledgers
        this.ledgerService.changeLedgerValue(booking.getLedgerShould(), booking.getValue());
        this.ledgerService.changeLedgerValue(booking.getLedgerHave(), -booking.getValue());
        return true;
    }
}
