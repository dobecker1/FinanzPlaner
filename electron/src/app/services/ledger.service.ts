import { Injectable } from "@angular/core";
import { Ledger } from "../models/ledger";

import { Observable, of } from 'rxjs';

@Injectable()
export class LedgerService {

    ledgers: Ledger[] = [];

    saveLedger(ledger: Ledger): void {
        this.ledgers.push(ledger);
    }

    deleteLedger() {}

    getAllLedgers(): Observable<Ledger[]> {
        return of(this.ledgers);
    }
}