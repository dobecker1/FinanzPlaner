class Ledger {

    private id: string;
    name: string;
    ledgerNumber: number;
    description: string;
    value: number;

    constructor(name: string, ledgerNumber: number, description:  string, value: number) {
        this.name = name;
        this.ledgerNumber = ledgerNumber;
        this.description = description;
        this.value = value;
    }

    getId (): string {
        return this.id;
    }
}