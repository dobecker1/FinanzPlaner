import { DataSource } from "@angular/cdk/table";
import { Booking } from "src/app/models/booking";
import { BookingService } from "src/app/services/booking.service";
import { CollectionViewer } from "@angular/cdk/collections";
import { Observable, BehaviorSubject } from "rxjs";

export class BookingsDataSource implements DataSource<Booking> {

    private bookingsSubject = new BehaviorSubject<Booking[]>([]);

    constructor(private bookingService: BookingService) {}

    connect(collectionViewer: CollectionViewer): Observable<Booking[]> {
        return this.bookingsSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.bookingsSubject.complete();
    }

    loadBookings() {
        this.bookingService.getAllBookings()
        .subscribe(bookings => this.bookingsSubject.next(bookings));
    }
}