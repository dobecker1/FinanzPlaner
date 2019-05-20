export class BookingMetadata {

    private id: number;
    date: Date;
    referenceNumber: string;
    bookingDescription: string;
    ledgerShould: number;
    ledgerHave: number;
    subLedgerShould: number;
    subLedgerHave: number;
    value: number;
    referencePath: string;
    financialYear: string;

    constructor() {}

    public getId(): number {
        return this.id;
    }
}