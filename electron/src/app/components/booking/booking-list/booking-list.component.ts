import { Component, OnInit } from "@angular/core";
import { BookingService } from "../services/booking.service";
import { Booking } from "src/app/models/booking";
import { MatTableDataSource } from "@angular/material";
import { LedgerElementNullPipe } from "../../ledger/pipes/ledger-element-null.pipe";
import { Observable } from "rxjs";
import { BookingMetadata } from "src/app/models/bookingMetadata";

@Component({
  selector: 'booking-list',
  templateUrl: './booking-list.component.html',
  styleUrls: ['./booking-list.component.css'],
  providers: [ LedgerElementNullPipe ]
})
export class BookingListComponent implements OnInit{
    
  //bookingDataSource: MatTableDataSource<Booking>;
  bookingDataSource: MatTableDataSource<Booking>;
  displayedBookingColumns: String[];
  bookings: Observable<Booking[]>;

  constructor(private bookingService: BookingService, private ledgerElementNullPipe: LedgerElementNullPipe) {
    
  }

  ngOnInit() {
    this.loadBookings();
    this.displayedBookingColumns = ['date', 'referenceNo', 'bookingDescription', 'shouldLedger', 'subShouldLedger', 'haveLedger', 'subHaveLedger',
     'value', 'actions'];
  }

  loadBookings() {
     this.bookings = this.bookingService.getAllBookings()
     this.bookings.subscribe(bookings => this.bookingDataSource = new MatTableDataSource(bookings));
  }

  addBookingToTable(booking: Booking) {
    this.bookingDataSource.data.push(booking);
  }

  deleteBooking(booking: Booking) {
    this.bookingService.deleteBooking(booking.id)
    .subscribe(response => {
      if(response) {
        this.loadBookings(); 
      } else {
        console.log("Error deleting Booking");
      }
    });
  }
}
