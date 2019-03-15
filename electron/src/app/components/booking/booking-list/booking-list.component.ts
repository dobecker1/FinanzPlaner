import { Component, OnInit } from "@angular/core";
import { BookingService } from "../services/booking.service";
import { Booking } from "src/app/models/booking";
import { MatTableDataSource } from "@angular/material";
import { LedgerElementNullPipe } from "../../ledger/pipes/ledger-element-null.pipe";

@Component({
  selector: 'booking-list',
  templateUrl: './booking-list.component.html',
  styleUrls: ['./booking-list.component.css'],
  providers: [ LedgerElementNullPipe ]
})
export class BookingListComponent implements OnInit{
    
  bookingDataSource: MatTableDataSource<Booking>;
  displayedBookingColumns: String[];

  constructor(private bookingService: BookingService, private ledgerElementNullPipe: LedgerElementNullPipe) {
    
  }

  ngOnInit() {
    this.loadBookings();
    this.displayedBookingColumns = ['date', 'referenceNo', 'bookingDescription', 'shouldLedger', 'subShouldLedger', 'haveLedger', 'subHaveLedger', 'value'];
  }

  loadBookings() {
    this.bookingService.getAllBookings()
    .subscribe(bookings => this.bookingDataSource = new MatTableDataSource(bookings));
  }
}
