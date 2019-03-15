import { Injectable } from "@angular/core";

import { Observable, of } from "rxjs";
import { Booking } from "src/app/models/booking";
import { HttpHeaders, HttpClient } from "@angular/common/http";

@Injectable()
export class BookingService {

    httpOptions: any;
    constructor(private http: HttpClient) {
        this.httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json/'
            })
        };
    }

    saveBooking(booking: Booking): void {
        this.http.post<Booking>("http://localhost:8080/saveBooking", booking, this.httpOptions);
    }

    getAllBookings(): Observable<Booking[]> {
       return this.http.get<Booking[]>('http://localhost:8080/getAllBookings');
    }
}