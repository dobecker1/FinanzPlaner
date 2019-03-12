import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgModule } from '@angular/core';
import { Router } from '@angular/router';

import { AccordionModule } from "primeng/accordion";
import { FileUploadModule } from "primeng/fileupload";

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';

import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { NavigatorComponent } from './components/navigation/navigator.component';

import { NgxElectronModule } from 'ngx-electron';

import { BookingModule } from './components/booking/booking.module';
import { LedgerModule } from './components/ledger/ledger.module';
import { AppRoutingModule } from './components/navigation/app-routing.module';

const appRoutes: Routes = [
 // {path: 'booking', component: }
]

@NgModule({
  declarations: [
    AppComponent,
    NavigatorComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    NgxElectronModule,
    AccordionModule,
    FileUploadModule,
    MatToolbarModule,
    BookingModule,
    LedgerModule,
    AppRoutingModule,
    MatButtonModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(router: Router) {}
 }
