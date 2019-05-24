import { Component, Inject, NgZone, ViewChild } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { Booking } from "src/app/models/booking";
import { BookingMetadata } from "src/app/models/bookingMetadata";
import { BookingFormComponent } from "../booking-form/booking-form.component";

@Component({
    selector: 'booking-edit-dialog',
    templateUrl: 'booking-edit-dialog.component.html',
    styleUrls: ['booking-edit-dialog.component.css']
})
export class BookingEditDialog {

    @ViewChild(BookingFormComponent)
    private bookingForm: BookingFormComponent;

    constructor(public bookingEditDialogRef: MatDialogRef<BookingEditDialog>, 
        @Inject(MAT_DIALOG_DATA) public booking: Booking, private ngZone:  NgZone) {
        }

    // onBooked(bookingMeta: BookingMetadata) {
    //     console.log(bookingMeta);
    //     // console.log(this.bookingEditDialogRef.disableClose);
    //      this.bookingEditDialogRef.close(bookingMeta);
    //     //this.closeDialog(); 
    // }
    

    submitBooking() {
        this.bookingEditDialogRef.close(this.bookingForm.getBookingMetadata());
    }
}