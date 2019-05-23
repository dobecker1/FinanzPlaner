import { Ledger } from "./ledger";

export class Booking {

    id: number;
    date: Date;
    referenceNumber: string;
    bookingDescription: string;
    ledgerShould: Ledger;
    ledgerHave: Ledger;
    subLedgerShould: Ledger;
    subLedgerHave: Ledger;
    value: number;
    referencePath: string;
    financialYear: string;

    constructor() {}

    public getId(): number {
        return this.id;
    }
}