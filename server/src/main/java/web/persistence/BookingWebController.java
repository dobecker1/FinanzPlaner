package web.persistence;

import daoLayer.services.BookingService;
import daoLayer.services.LedgerService;
import models.booking.Booking;
import models.booking.BookingImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookingWebController {

    @Autowired
    BookingService bookingService;

    @Autowired
    LedgerService ledgerService;

    public BookingWebController()  {}

    @PostMapping("/bookings")
    public String saveBooking(@RequestBody Booking booking) {
        this.bookingService.book(booking);
        return "OK";
    }

    @DeleteMapping("/bookings/{id}")
    public String deleteBooking(@PathVariable int id) {
        this.bookingService.deleteBooking(id);
        return "OK";
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
