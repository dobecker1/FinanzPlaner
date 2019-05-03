package booking;

import daoLayer.services.BookingService;
import daoLayer.services.LedgerService;
import models.booking.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("bookingHelper")
public class BookingHelper {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private LedgerService ledgerService;

    public int book(Booking booking) {
        this.ledgerService.changeLedgerValue(booking.getLedgerShould(), booking.getValue());
        this.ledgerService.changeLedgerValue(booking.getLedgerHave(), -booking.getValue());
        return this.bookingService.saveBooking(booking);
    }

    public boolean undoOldBooking(Booking booking) {
        Booking oldBooking = this.bookingService.getBookingById(booking.getId());

        //only value changed
        //clear old Ledger values
        this.ledgerService.changeLedgerValue(oldBooking.getLedgerShould(), -oldBooking.getValue());
        this.ledgerService.changeLedgerValue(oldBooking.getLedgerHave(), oldBooking.getValue());

        //change Values of ledgers
        this.ledgerService.changeLedgerValue(booking.getLedgerShould(), booking.getValue());
        this.ledgerService.changeLedgerValue(booking.getLedgerHave(), -booking.getValue());
        return true;
    }
}
