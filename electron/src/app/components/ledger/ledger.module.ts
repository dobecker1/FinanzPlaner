import { NgModule } from "@angular/core";

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';

import { LedgerFormComponent } from './ledger-form/ledger-form.component';
import { LedgerComponent } from './ledgerPage/ledger.component';

@NgModule({
    imports: [
        FormsModule,
        ReactiveFormsModule,
        MatButtonModule,
        MatFormFieldModule,
        MatInputModule,
        MatCardModule
    ],
    declarations: [
        LedgerFormComponent,
        LedgerComponent
    ],
    exports: [
        LedgerFormComponent,
        LedgerComponent
    ]
})
export class LedgerModule {}