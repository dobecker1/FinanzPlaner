import { Component, ViewChild } from "@angular/core";
import { LedgerListComponent } from "../ledger-list/ledger-list.component";
import { Ledger } from "src/app/models/ledger";

@Component({
    selector: 'ledger',
    templateUrl: './ledger.component.html',
    styleUrls: ['ledger.component.css']
})
export class LedgerComponent {
    
    @ViewChild(LedgerListComponent)
    private ledgerList: LedgerListComponent;

    onLedgerCreated(ledger: Ledger) {
        this.ledgerList.loadLedgers();
    }
}