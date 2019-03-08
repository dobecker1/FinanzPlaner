import { Injectable } from "@angular/core";
import { Ledger } from "../models/ledger";
import { Booking } from "../models/booking";

@Injectable()
export class BookingService {

    getAllBookings(): Booking[] {
        let bookings: Booking[] = [];
        let booking = new Booking();
        booking.date = new Date();
        booking.ledgerShould = new Ledger("Gehalt SAG", 401, "Gehaltskonto", 2000);
        bookings.push(booking);
        
        return bookings;
    }
}