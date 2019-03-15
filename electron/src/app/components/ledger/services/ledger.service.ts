import { Injectable } from "@angular/core";
import { Ledger } from "../../../models/ledger";

import { Observable, of, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from "@angular/common/http";


@Injectable()
export class LedgerService {

    httpOptions: any;

    constructor(private http: HttpClient) {
        this.httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };
    }

    saveLedger(ledger: Ledger): Observable<Ledger> {
       return this.http.post<Ledger>("http://localhost:8080/saveLedger", ledger, this.httpOptions)
       .pipe(
           catchError(this.handleError('saveLedger', ledger))
       );
    }

    deleteLedger() {}

    getAllLedgers(): Observable<Ledger[]> {
        return this.http.get<Ledger[]>('http://localhost:8080/getAllLedgers');
    }

    private handleError(error: HttpErrorResponse) {
        if(error.error instanceof ErrorEvent) {
            console.error('An error occured: ', error.error.message);
        } else {
            console.error(
                'Returned code ${error.status}, ' +
                'body was: ${error.error}'
            );
        }
        return throwError('Ein Fehler ist aufgetreten.');
    }
}