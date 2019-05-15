import { Component, ViewChild, OnInit } from "@angular/core";
import { LedgerService } from "src/app/components/ledger/services/ledger.service";
import { MatTableDataSource, MatSort, MatDialog } from "@angular/material";
import { Ledger } from "src/app/models/ledger";
import { LedgerDialog } from "../ledger-dialog/ledger-dialog.component";
import { NotificationService } from "../services/notification.service";

@Component({
    selector: 'ledger-list',
    templateUrl: './ledger-list.component.html',
    styleUrls: ['./ledger-list.component.css']
})
export class LedgerListComponent implements OnInit {

    ledgerTableSource: MatTableDataSource<Ledger>;
    displayedLedgerColumns: String[];

    @ViewChild(MatSort) sort: MatSort;

    constructor(private ledgerService: LedgerService, private notifier: NotificationService, public dialog: MatDialog) {
        this.displayedLedgerColumns = ['ledgerNumber', 'name', 'description', 'value', 'actions']
    }

    ngOnInit() {
        this.loadLedgers();
    }

    loadLedgers() {
        this.ledgerService.getAllLedgers()
        .subscribe(ledgers => {
            this.ledgerTableSource = new MatTableDataSource(ledgers)
            this.ledgerTableSource.sort = this.sort;
        });
    }

    addLedgerToList(ledger: Ledger) {
        this.ledgerTableSource.data.push(ledger);
    }

    applyFilter(filterValue: string) {
        this.ledgerTableSource.filter = filterValue.trim().toLowerCase();
    }

    editLedger(ledger: Ledger) {
        let editLedger: Ledger = new Ledger();
        editLedger.description = ledger.description;
        editLedger.ledgerNumber = ledger.ledgerNumber;
        editLedger.name = ledger.name;
        const ledgerDialog = this.dialog.open(LedgerDialog, {
            data: editLedger
        });
        ledgerDialog.afterClosed().subscribe(editedLedger => {
            if(editedLedger != undefined) {
                console.log(editedLedger);
                ledger.description = editedLedger.description;
                ledger.ledgerNumber = editedLedger.ledgerNumber;
                ledger.name = editedLedger.name;
                this.ledgerService.updateLedger(ledger).subscribe(response => {
                    if(response) {
                        this.notifier.showSuccess("Konto " + ledger.ledgerNumber + " erfolgreich gespeichert");
                        //TODO reset Ledger after update failed
                    }
                });
            }
        });
    }

    deleteLedger(ledger: Ledger) {
        this.ledgerService.deleteLedger(ledger.id)
        .subscribe(response => {
            if(response) {
                this.loadLedgers();
            } else {
                console.log("Error deleting ledger");
            }
        });
    }

    updateLedger() {

    }
}