import { Component } from "@angular/core";
import { Ledger } from "src/app/models/ledger";

@Component({
    selector: 'ledger-form',
    templateUrl: './ledger-form.component.html',
    styleUrls: ['./ledger-form.component.css']
})
export class LedgerFormComponent {
    
    ledger: Ledger = new Ledger("", 0, "", 0);

    onSubmit() {
        console.log("Submit Ledger");
    }
}