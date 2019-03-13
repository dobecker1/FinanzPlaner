import { Component, ViewChild, OnInit, AfterViewInit } from "@angular/core";
import { LedgerService } from "src/app/services/ledger.service";
import { MatTableDataSource, MatSort } from "@angular/material";
import { Ledger } from "src/app/models/ledger";

@Component({
    selector: 'ledger-list',
    templateUrl: './ledger-list.component.html',
    styleUrls: ['./ledger-list.component.css']
})
export class LedgerListComponent implements OnInit, AfterViewInit{

    ledgerTableSource: MatTableDataSource<Ledger>;
    displayedLedgerColumns: String[];

    @ViewChild(MatSort) sort: MatSort;

    constructor(private ledgerService: LedgerService) {
        this.displayedLedgerColumns = ['ledgerNumber', 'name', 'description', 'value']
    }

    ngOnInit() {
        this.loadLedgers();
    }

    ngAfterViewInit() {
        this.ledgerTableSource.sort = this.sort;
    }

    loadLedgers() {
        this.ledgerService.getAllLedgers()
        .subscribe(ledgers => this.ledgerTableSource = new MatTableDataSource(ledgers));
    }

    applyFilter(filterValue: string) {
        this.ledgerTableSource.filter = filterValue.trim().toLowerCase();
    }
}