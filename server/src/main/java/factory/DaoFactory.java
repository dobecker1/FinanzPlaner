package factory;

import daoLayer.sqlDao.*;

public class DaoFactory {

    public static BookingDao getBookingDao() {
        return new BookingDao();
    }

    public static BookingPatternPayloadDao getBookingPatternPayloadDao() {
        return new BookingPatternPayloadDao();
    }

    public static BookingPatternItemDao getBookingPatternItemDao() {
        return new BookingPatternItemDao();
    }

    public static LedgerDao getLedgerDao() {
        return new LedgerDao();
    }

    public static CategoryDao getCategoryDao() {
        return new CategoryDao();
    }

    public static InputFieldDao getInputFieldDao() {
        return new InputFieldDao();
    }

    public static BookingInformationDao getBookingInformationDao() {
        return new BookingInformationDao();
    }
}
