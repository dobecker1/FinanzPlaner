import { Injectable } from "@angular/core";

import { Observable, of } from "rxjs";
import { Booking } from "src/app/models/booking";
import { HttpHeaders, HttpClient } from "@angular/common/http";
import { BookingMetadata } from "src/app/models/bookingMetadata";

@Injectable()
export class BookingService {

    httpOptions: any;
    private basicBookingUrl: string = "http://localhost:8080/bookings";
    constructor(private http: HttpClient) {
        this.httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json/'
            })
        };
    }

    saveBooking(booking: Booking): Observable<number> {
        return this.http.post<number>(this.basicBookingUrl, booking, {headers: this.httpOptions, observe: 'body', responseType: 'json'});
    }

    saveBookingMetadata(booking: BookingMetadata): Observable<number> {
        return this.http.post<number>(this.basicBookingUrl + "/metadata", booking, {headers: this.httpOptions, observe: 'body', responseType: 'json'});
    }

    deleteBooking(id: number): Observable<boolean> {
        return this.http.delete<boolean>(this.basicBookingUrl + "/" + id, {headers: this.httpOptions, responseType: 'json'});
    }

    getBookingsByStartEndDate(start: Date, end: Date): Observable<Booking[]> {
        return this.http.get<Booking[]>(this.basicBookingUrl + "/" + start + "/" + end);
    }

    getAllBookings(): Observable<Booking[]> {
       return this.http.get<Booking[]>(this.basicBookingUrl);
    }

    getAllBookingMetadata(): Observable<BookingMetadata[]> {
        return this.http.get<BookingMetadata[]>(this.basicBookingUrl + "/metadata");
    }
}