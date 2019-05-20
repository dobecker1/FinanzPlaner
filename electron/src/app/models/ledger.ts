export class Ledger {

    id: number;
    name: string;
    ledgerNumber: number;
    description: string;
    value: number;
    subLedger: boolean;

    constructor() {}

    getId (): number {
        return this.id;
    }
}