import { Component, ViewChild } from "@angular/core";
import { BookingListComponent } from "../booking-list/booking-list.component";
import { Booking } from "src/app/models/booking";

@Component({
    selector: 'booking',
    templateUrl: './booking.component.html',
    styleUrls: ['./booking.component.css']
})
export class BookingComponent {

  @ViewChild(BookingListComponent)
  private bookingList: BookingListComponent;

  onBooked(booking: Booking) {
    this.bookingList.loadBookings();
  }
}