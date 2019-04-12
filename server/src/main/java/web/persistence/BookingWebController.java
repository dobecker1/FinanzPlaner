package web.persistence;

import daoLayer.services.BookingService;
import daoLayer.services.LedgerService;
import models.booking.Booking;
import models.booking.BookingImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookingWebController {

    @Autowired
    BookingService bookingService;

    @Autowired
    LedgerService ledgerService;

    public BookingWebController()  {}

    @PostMapping("/saveBooking")
    public String saveBooking(@RequestBody Booking booking) {
        this.ledgerService.changeLedgerValue(booking.getLedgerShould(), booking.getValue());
        this.ledgerService.changeLedgerValue(booking.getLedgerHave(), -booking.getValue());
        this.bookingService.saveBooking(booking);
        return "OK";
    }

    @GetMapping("/getAllBookings")
    public List<Booking> getAllBookings() {
        return new ArrayList<>();
    }
}
