import { Component, ViewChild } from "@angular/core";
import { BookingListComponent } from "../booking-list/booking-list.component";
import { Booking } from "src/app/models/booking";

@Component({
    selector: 'booking',
    templateUrl: './booking.component.html',
    styleUrls: ['./booking.component.css']
})
export class BookingComponent {

  chartData: any = {
    labels: ['Erledigt', 'Ausstehend'],
    datasets: [
      {
        data: [15, 5],
        backgroundColor: [
          "#FF6384",
          "#36A2EB"
        ]
      }
    ]
  }

  barData: any = {
    labels: ["Januar", "Februar", "März", "April"],
    datasets: [
      {
        label: "Einnahmen",
        backgroundColor: '#9CCC65',
        borderColor: '#7CB342',
        data: [2000, 2200, 2300,  1950]
      },
      {
        label: "Ausgaben",
        backgroundColor: '#f44242',
        borderColor: '#1E88E5',
        data: [1800, 1500, 2000, 2100]
      }
    ]
  }

  @ViewChild(BookingListComponent)
  private bookingList: BookingListComponent;

  onBooked(booking: Booking) {
    this.bookingList.loadBookings();
  }
}