package daoLayer.services;

import booking.BookingHelper;
import daoLayer.services.daoServices.BookingDaoService;
import daoLayer.services.exceptions.LedgerServiceException;
import factory.ModelFactory;
import factory.ServiceFactory;
import models.booking.Booking;
import models.booking.metadata.BookingMetadata;

import java.time.LocalDate;
import java.util.List;

public class BookingService {

    private BookingDaoService bookingDaoService;
    private BookingHelper bookingHelper;

    public BookingService() {
        this.bookingDaoService = ServiceFactory.getBookingDaoService();
        this.bookingHelper = new BookingHelper(this);
    }

    public int saveBooking(Booking booking) {
        return this.bookingDaoService.saveBooking(booking);
    }

    public boolean updateBooking(Booking booking) throws LedgerServiceException {

        if(bookingHelper.updateLedgers(booking)) {
            return this.bookingDaoService.updateBooking(booking);
        } else {
            return false;
        }
    }

    public int book(Booking booking) throws LedgerServiceException {
        return this.bookingHelper.book(booking);
    }

    public int bookMetadata(BookingMetadata bookingMetadata) throws LedgerServiceException {
        return this.bookingHelper.book(this.bookingHelper.convertMetadataToBooking(bookingMetadata));
    }



    public void deleteBooking(Booking booking) throws LedgerServiceException {
        this.bookingHelper.resetBookingLedgers(booking);
        this.bookingDaoService.deleteBooking(booking);
    }

    public boolean deleteBooking(int id) throws LedgerServiceException {
        this.bookingHelper.resetBookingLedgers(this.bookingDaoService.findBookingById(id));
        return this.bookingDaoService.deleteBooking(id);
    }

    public Booking getBookingById(int id) {
        return this.bookingDaoService.findBookingById(id);
    }

    public List<Booking> findBookingsByStartEndDate(LocalDate start, LocalDate end) {
        return this.bookingDaoService.findBookingsByStartEndDate(start, end);
    }

    public List<Booking> findAllBookings() {
        return this.bookingDaoService.findAllBookings();
    }

    public List<BookingMetadata> findAllBookingMetadata() {
        return this.bookingDaoService.findAllBookingMetadata();
    }


}
