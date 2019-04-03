package factory;

import models.booking.Booking;
import models.booking.BookingImpl;
import models.category.Category;
import models.category.CategoryImpl;
import models.ledger.Ledger;
import models.ledger.LedgerImpl;
import models.patternBooking.impl.BookingPatternItemImpl;
import models.patternBooking.interfaces.BookingInformation;
import models.patternBooking.impl.BookingInformationImpl;
import models.patternBooking.interfaces.BookingPattern;
import models.patternBooking.impl.BookingPatternImpl;
import models.patternBooking.interfaces.BookingPatternItem;

public class ModelFactory {

    public static Ledger getLedger() {
        return new LedgerImpl();
    }

    public static Booking getBooking() {
        return new BookingImpl();
    }

    public static Category getCategory() {
        return new CategoryImpl();
    }

    public static BookingInformation getBookingInformation() {
        return new BookingInformationImpl();
    }

    public static BookingPattern getBookingPattern() {
        return new BookingPatternImpl();
    }

    public static BookingPatternItem getBookingPatternItem() {
        return new BookingPatternItemImpl();
    }
}
