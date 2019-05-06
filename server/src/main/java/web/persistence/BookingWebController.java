package web.persistence;

import daoLayer.services.BookingService;
import daoLayer.services.LedgerService;
import factory.ServiceFactory;
import models.booking.Booking;
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
    public String saveBooking(@RequestBody Booking booking) {
        this.bookingService.book(booking);
        return "OK";
    }

    @PutMapping("/bookings")
    public boolean updateBooking(@RequestBody Booking booking) {
        return this.bookingService.updateBooking(booking);
    }

    @DeleteMapping("/bookings/{id}")
    public boolean deleteBooking(@PathVariable int id) {
        return this.bookingService.deleteBooking(id);
    }

    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return new ArrayList<>();
    }

    @GetMapping("/getBookingsByStartEndDate/{start}/{end}")
    public List<Booking> getBookingsByStartEndDate(@PathVariable LocalDate start, @PathVariable LocalDate end) {
        return this.bookingService.findBookingsByStartEndDate(start, end);
    }
}
