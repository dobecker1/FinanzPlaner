import { NgModule } from "@angular/core";
import { CommonModule }   from '@angular/common';

import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

//Angular Material
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatCardModule } from "@angular/material/card";
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatTableModule } from '@angular/material/table';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';

//PrimeNG
import { ChartModule } from 'primeng/chart';
import { ProgressBarModule } from 'primeng/progressbar';
import { ToastModule } from 'primeng/toast';

import { BookingFormComponent } from './booking-form/booking-form.component';
import { BookingListComponent } from './booking-list/booking-list.component';
import { BookingComponent } from './bookingPage/booking.component';

import { LedgerService } from '../ledger/services/ledger.service';
import { BookingService } from './services/booking.service';

import { LedgerElementNullPipe } from '../ledger/pipes/ledger-element-null.pipe';
import { BookingEditDialog } from "./booking-edit-dialog/booking-edit-dialog.component";




@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatCardModule,
        MatInputModule,
        MatButtonModule,
        MatNativeDateModule,
        MatDatepickerModule,
        MatAutocompleteModule,
        MatSidenavModule,
        MatTableModule,
        MatProgressSpinnerModule,
        MatProgressBarModule,
        MatListModule,
        MatIconModule,
        MatDialogModule,
        ChartModule,
        ProgressBarModule,
        ToastModule
        
    ],
    entryComponents: [
        BookingEditDialog
    ],
    declarations: [
        BookingFormComponent,
        BookingListComponent,
        BookingComponent,
        BookingEditDialog,
        LedgerElementNullPipe
    ],
    providers: [
        LedgerService,
        BookingService,
        LedgerElementNullPipe
    ],
    exports: [
        BookingFormComponent,
        BookingListComponent,
        BookingComponent,
        BookingEditDialog,
        LedgerElementNullPipe
    ]
})
export class BookingModule {}