import { Component, Inject } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material";
import { Ledger } from "src/app/models/ledger";

@Component({
    selector: 'ledger-dialog',
    templateUrl: 'ledger-dialog.component.html',
    styleUrls: ['ledger-dialog.component.css']
})
export class LedgerDialog {

    constructor(public ledgerDialogRef: MatDialogRef<LedgerDialog>, @Inject(MAT_DIALOG_DATA) public ledger: Ledger) {

    }


    onLedgerSubmit() {
        this.ledgerDialogRef.close(this.ledger);
    }

    onDialogClose() {
        this.ledgerDialogRef.close();
    }

}