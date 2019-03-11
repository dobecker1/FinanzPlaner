import { Injectable } from "@angular/core";
import { Ledger } from "../models/ledger";
import { Booking } from "../models/booking";
import { Observable, of } from "rxjs";

@Injectable()
export class BookingService {

    bookings: Booking[] = [];

    saveBooking(booking: Booking): void {
        this.bookings.push(booking);
        console.log(this.bookings);
    }

    getAllBookings(): Observable<Booking[]> {
       return of(this.bookings);
    }
}