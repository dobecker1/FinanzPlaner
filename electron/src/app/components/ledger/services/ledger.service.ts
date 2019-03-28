import { Injectable } from "@angular/core";
import { Ledger } from "../../../models/ledger";

import { Observable, of, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpParams } from "@angular/common/http";


@Injectable()
export class LedgerService {

    httpOptions: any;

    constructor(private http: HttpClient) {
        this.httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            },),
            responseType: 'json'
        };
    }

    saveLedger(ledger: Ledger): Observable<Ledger> {
       return this.http.post<Ledger>("http://localhost:8080/saveLedger", ledger, {headers: this.httpOptions, observe: 'body', responseType: 'json'});
    }

    deleteLedger(id: number): void{
        this.http.delete("http://localhost:8080/deleteLedger?id=" + id, {headers: this.httpOptions, responseType: 'text'})
        .subscribe(response => {
            console.log(response);
        });
    }

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