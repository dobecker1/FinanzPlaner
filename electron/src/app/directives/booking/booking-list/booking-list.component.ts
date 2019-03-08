import { Component } from "@angular/core";
import { BookingService } from "src/app/services/booking.service";
import { Booking } from "src/app/models/booking";
import { MatTableDataSource } from "@angular/material";

@Component({
  selector: 'booking-list',
  templateUrl: './booking-list.component.html',
  styleUrls: ['./booking-list.component.css']
})
export class BookingListComponent {
    
  bookings: MatTableDataSource<Booking>;
  displayedBookingColumns: String[];

  constructor(private bookingService: BookingService) {
    this.bookings = new MatTableDataSource(this.bookingService.getAllBookings());
    this.displayedBookingColumns = ['date', 'shouldLedger'];
  }
}
