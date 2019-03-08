import { Ledger } from "./ledger";

export class Booking {

    private id: number;
    date: Date;
    referenceNumber: string;
    bookingDescription: string;
    ledgerShould: Ledger;
    ledgerHave: Ledger;
    subLedgerShould: Ledger;
    subLedgerHave: Ledger;
    value: number;
    referencePath: string;

    constructor() {}

    public getId(): number {
        return this.id;
    }
}