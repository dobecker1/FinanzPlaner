import { Pipe, PipeTransform } from "@angular/core";
import { Ledger } from "src/app/models/ledger";

@Pipe({name: 'ledgerElementNull'})
export class LedgerElementNullPipe implements PipeTransform {
    transform(ledger: Ledger): string {
        return (ledger != null ? ledger.ledgerNumber.toString() : "");
    }
}