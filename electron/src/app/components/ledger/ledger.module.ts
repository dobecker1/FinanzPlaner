import { NgModule } from "@angular/core";

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { HttpClientModule } from '@angular/common/http';

import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSortModule } from '@angular/material/sort';
import { MatDialogModule } from '@angular/material/dialog';

import { LedgerFormComponent } from './ledger-form/ledger-form.component';
import { LedgerListComponent } from './ledger-list/ledger-list.component';
import { LedgerComponent } from './ledgerPage/ledger.component';
import { LedgerDialog } from './ledger-dialog/ledger-dialog.component';

@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        MatButtonModule,
        MatIconModule,
        MatFormFieldModule,
        MatInputModule,
        MatCardModule,
        MatTableModule,
        MatSidenavModule,
        MatSortModule,
        MatDialogModule
    ],
    entryComponents: [
        LedgerDialog
    ],
    declarations: [
        LedgerFormComponent,
        LedgerListComponent,
        LedgerDialog,
        LedgerComponent
    ],
    exports: [
        LedgerFormComponent,
        LedgerListComponent,
        LedgerDialog,
        LedgerComponent
    ]
})
export class LedgerModule {}