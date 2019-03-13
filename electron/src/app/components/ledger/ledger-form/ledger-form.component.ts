import { Component, Output, EventEmitter, ViewChild } from "@angular/core";
import { Ledger } from "src/app/models/ledger";
import { LedgerService } from "src/app/services/ledger.service";
import { NgForm } from "@angular/forms";

@Component({
    selector: 'ledger-form',
    templateUrl: './ledger-form.component.html',
    styleUrls: ['./ledger-form.component.css']
})
export class LedgerFormComponent {
    
    ledger: Ledger = new Ledger();

    @Output() ledgerCreated: EventEmitter<Ledger> = new EventEmitter<Ledger>();


    constructor(private ledgerService: LedgerService) {}

    onSubmit(ledgerForm: NgForm) {
        let newLedger: Ledger = new Ledger();
        newLedger.name = this.ledger.name;
        newLedger.ledgerNumber = this.ledger.ledgerNumber;
        newLedger.description = this.ledger.description;
        newLedger.value = 0;
        this.ledgerService.saveLedger(newLedger);
        this.ledgerCreated.emit(newLedger);
        ledgerForm.resetForm();
    }
}