import { NgModule } from "@angular/core";
import { CommonModule }   from '@angular/common';

import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MatFormFieldModule } from "@angular/material/form-field";
import { MatCardModule } from "@angular/material/card";
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatTableModule } from '@angular/material/table';

import { BookingFormComponent } from './booking-form/booking-form.component';
import { BookingListComponent } from './booking-list/booking-list.component';

import { LedgerService } from '../../services/ledger.service';
import { BookingService } from '../../services/booking.service';




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
        MatTableModule
    ],
    declarations: [
        BookingFormComponent,
        BookingListComponent
    ],
    providers: [
        LedgerService,
        BookingService
    ],
    exports: [
        BookingFormComponent,
        BookingListComponent
    ]
})
export class BookingModule {}