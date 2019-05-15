import { Injectable } from "@angular/core";
import { Ledger } from "../../../models/ledger";

import { Observable, of, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpParams } from "@angular/common/http";


@Injectable()
export class LedgerService {

    httpOptions: any;
    private basicLedgerUrl: string = "http://localhost:8080/ledgers";

    constructor(private http: HttpClient) {
        this.httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            },),
            responseType: 'json'
        };
    }

    saveLedger(ledger: Ledger): Observable<number> {
       return this.http.post<number>(this.basicLedgerUrl, ledger, {headers: this.httpOptions, observe: 'body', responseType: 'json'});
    }

    updateLedger(ledger: Ledger): Observable<boolean> {
        return this.http.put<boolean>(this.basicLedgerUrl, ledger, {headers: this.httpOptions, observe: 'body', responseType: 'json'});
    }

    deleteLedger(id: number): Observable<boolean> {
        return this.http.delete<boolean>(this.basicLedgerUrl + "/" + id, {headers: this.httpOptions, responseType: 'json'});
    }

    getAllLedgers(): Observable<Ledger[]> {
        return this.http.get<Ledger[]>(this.basicLedgerUrl);
    }

    getLedgers(subLedgers: boolean): Observable<Ledger[]> {
        return this.http.get<Ledger[]>(this.basicLedgerUrl + "/" + subLedgers);
    }

    handleError(error) {
        console.log(error);
        let errorMessage: string = '';
        errorMessage = 'Error Code: ' + error.status + "\nMessage: "
        + error.error.message;
        window.alert(errorMessage);
        return throwError(errorMessage);
    }
}