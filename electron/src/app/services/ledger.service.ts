import { Injectable } from "@angular/core";

@Injectable()
export class LedgerService {

    saveLedger(ledger: Ledger): void {}

    deleteLedger() {}

    getAllLedgers(): Ledger[] {
        let ledgers: Ledger[] = [];
        ledgers.push(new Ledger("Gehalt SAG", 401, "Gehaltskonto", 2000));
        ledgers.push(new Ledger("Diesel Corsa", 321, "Diesel Opel Corsa", 950));
        return ledgers;
    }
}