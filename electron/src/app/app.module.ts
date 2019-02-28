import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgModule } from '@angular/core';


import { AccordionModule } from "primeng/accordion";
import { FileUploadModule } from "primeng/fileupload";

import { MatFormFieldModule } from "@angular/material/form-field";
import { MatCardModule } from "@angular/material/card";
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { BookingFormComponent } from './directives/booking/booking-form.component';
import { NavigatorComponent } from './directives/navigation/navigator.component';

import { LedgerService } from './services/ledger.service';

import { NgxElectronModule } from 'ngx-electron';

const appRoutes: Routes = [
 // {path: 'booking', component: }
]

@NgModule({
  declarations: [
    AppComponent,
    BookingFormComponent,
    NavigatorComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    NgxElectronModule,
    AccordionModule,
    FileUploadModule,
    MatFormFieldModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatAutocompleteModule,
    MatToolbarModule,
    MatSidenavModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    LedgerService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
