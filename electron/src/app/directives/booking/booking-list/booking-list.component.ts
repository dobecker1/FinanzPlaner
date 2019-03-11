import { Component } from "@angular/core";
import { BookingService } from "src/app/services/booking.service";
import { Booking } from "src/app/models/booking";
import { MatTableDataSource } from "@angular/material";
import { LedgerElementNullPipe } from "../../ledger/pipes/ledger-element-null.pipe";
import { BookingsDataSource } from "../bookings-data-source";

@Component({
  selector: 'booking-list',
  templateUrl: './booking-list.component.html',
  styleUrls: ['./booking-list.component.css'],
  providers: [ LedgerElementNullPipe ]
})
export class BookingListComponent {
    
  bookingDataSource: BookingsDataSource;
  displayedBookingColumns: String[];

  constructor(private bookingService: BookingService, private ledgerElementNullPipe: LedgerElementNullPipe) {
    this.bookingDataSource = new BookingsDataSource(this.bookingService);
    this.bookingDataSource.loadBookings();
    this.displayedBookingColumns = ['date', 'referenceNo', 'bookingDescription', 'shouldLedger', 'subShouldLedger', 'haveLedger', 'subHaveLedger', 'value'];
  }

  refreshBookings() {
    this.bookingDataSource.loadBookings();
  }
}
