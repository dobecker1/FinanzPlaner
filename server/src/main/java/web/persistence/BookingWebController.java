package web.persistence;

import daoLayer.services.BookingService;
import models.booking.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingWebController {

    @Autowired
    BookingService bookingService;

    public BookingWebController()  {}

    @PostMapping("/saveBooking")
    public String saveBooking(@RequestBody Booking booking) {
        this.bookingService.saveBooking(booking);
        return "OK";
    }

    @GetMapping("/getAllBookings")
    public List<Booking> getAllBookings() {
        return this.bookingService.getAllBookings();
    }
}
