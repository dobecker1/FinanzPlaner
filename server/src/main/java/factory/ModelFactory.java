package factory;

import models.booking.Booking;
import models.booking.BookingImpl;
import models.ledger.Ledger;
import models.ledger.LedgerImpl;

public class ModelFactory {

    public static Ledger getLedger() {
        return new LedgerImpl();
    }

    public static Booking getBooking() {
        return new BookingImpl();
    }
}
