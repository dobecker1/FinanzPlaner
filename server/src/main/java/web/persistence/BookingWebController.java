package web.persistence;

import daoLayer.services.BookingService;
import daoLayer.services.LedgerService;
import daoLayer.services.exceptions.LedgerServiceException;
import factory.ServiceFactory;
import models.booking.Booking;
import models.booking.metadata.BookingMetadata;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookingWebController {

    BookingService bookingService;
    LedgerService ledgerService;

    public BookingWebController()  {
        this.bookingService = ServiceFactory.getBookingService();
        this.ledgerService = ServiceFactory.getLedgerService();
    }

    @PostMapping("/bookings")
    public int saveBooking(@RequestBody Booking booking) throws LedgerServiceException {
        return this.bookingService.book(booking);
    }

    @PostMapping("/bookings/metadata")
    public int saveBookingMetadata(@RequestBody BookingMetadata bookingMetadata) throws LedgerServiceException {
        return this.bookingService.bookMetadata(bookingMetadata);
    }

    @PutMapping("/bookings")
    public boolean updateBooking(@RequestBody Booking booking) throws LedgerServiceException {
        return this.bookingService.updateBooking(booking);
    }

    @DeleteMapping("/bookings/{id}")
    public boolean deleteBooking(@PathVariable int id) throws LedgerServiceException {
        return this.bookingService.deleteBooking(id);
    }

    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return this.bookingService.findAllBookings();
    }

    @GetMapping("/bookings/{start}/{end}")
    public List<Booking> getBookingsByStartEndDate(@PathVariable LocalDate start, @PathVariable LocalDate end) {
        return this.bookingService.findBookingsByStartEndDate(start, end);
    }

    @GetMapping("/bookings/metadata")
    public List<BookingMetadata> getAllBookingMetadata() {
        return this.bookingService.findAllBookingMetadata();
    }
}
