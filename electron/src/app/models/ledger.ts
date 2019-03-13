export class Ledger {

    private id: number;
    name: string;
    ledgerNumber: number;
    description: string;
    value: number;

    constructor() {}

    getId (): number {
        return this.id;
    }
}