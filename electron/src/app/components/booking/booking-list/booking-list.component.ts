import { Component, OnInit, NgZone } from "@angular/core";
import { BookingService } from "../services/booking.service";
import { Booking } from "src/app/models/booking";
import { MatTableDataSource, MatDialog } from "@angular/material";
import { LedgerElementNullPipe } from "../../ledger/pipes/ledger-element-null.pipe";
import { Observable } from "rxjs";
import { BookingMetadata } from "src/app/models/bookingMetadata";
import { BookingEditDialog } from "../booking-edit-dialog/booking-edit-dialog.component";
import { NotificationService } from "src/app/services/notification.service";

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

  constructor(private bookingService: BookingService, private ledgerElementNullPipe: LedgerElementNullPipe,
    public dialog: MatDialog, private notifier: NotificationService) {
    
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

  editBooking(booking: Booking) {
      const bookingEditDialog = this.dialog.open(BookingEditDialog, {
        data: booking
      });
      bookingEditDialog.afterClosed().subscribe(editedBooking => {
        console.log(editedBooking);
        if(editedBooking != undefined) {
          this.bookingService.updateBookingMetadata(editedBooking).subscribe(response => {
            if(response) {
              console.log("Booking updated");
              this.notifier.showSuccess("Buchungssatz wurde erfolgreich aktualisiert");
            }
          });
        }
      })
  }
}
