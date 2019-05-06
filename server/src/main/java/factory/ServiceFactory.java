package factory;

import daoLayer.services.BookingService;
import daoLayer.services.LedgerService;
import daoLayer.services.daoServices.BookingDaoService;
import daoLayer.services.daoServices.LedgerDaoService;

public class ServiceFactory {

    public static LedgerDaoService getLedgerDaoService() {
        return new LedgerDaoService();
    }

    public static LedgerService getLedgerService() {
        return new LedgerService();
    }

    public static BookingDaoService getBookingDaoService() {
        return new BookingDaoService();
    }

    public static BookingService getBookingService() {
        return new BookingService();
    }
}
