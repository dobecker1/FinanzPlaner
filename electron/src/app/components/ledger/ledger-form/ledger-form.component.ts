import { Component, Output, EventEmitter, ViewChild } from "@angular/core";
import { Ledger } from "src/app/models/ledger";
import { LedgerService } from "src/app/components/ledger/services/ledger.service";
import { NgForm } from "@angular/forms";
import { take, catchError } from 'rxjs/operators';
import { Observable } from "rxjs";
import { NotificationService } from "../services/notification.service";

@Component({
    selector: 'ledger-form',
    templateUrl: './ledger-form.component.html',
    styleUrls: ['./ledger-form.component.css']
})
export class LedgerFormComponent {
    
    ledger: Ledger = new Ledger();

    @Output() ledgerCreated: EventEmitter<Ledger> = new EventEmitter<Ledger>();


    constructor(private ledgerService: LedgerService, private notifier: NotificationService) {}

    onSubmit(ledgerForm: NgForm) {
        let newLedger: Ledger = new Ledger();
        newLedger.name = this.ledger.name;
        newLedger.ledgerNumber = this.ledger.ledgerNumber;
        newLedger.description = this.ledger.description;
        newLedger.value = 0;
        // let newId: Observable<number> = this.ledgerService.saveLedger(newLedger).pipe(take(1),
        // catchError(this.handleError));
        // newId.subscribe(id => {
        //     newLedger.id = id;
        //     this.ledgerCreated.emit(newLedger);
        //     ledgerForm.resetForm();
        // });        
        this.ledgerService.saveLedger(newLedger).subscribe(responseLedger => {
            this.notifier.showSuccess("Konto " + newLedger.ledgerNumber + " wurde gespeichert");
            newLedger.id = responseLedger;
            this.ledgerCreated.emit(newLedger);
            ledgerForm.resetForm();

        });        
    }
}