import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgModule } from '@angular/core';


import { AccordionModule } from "primeng/accordion";
import { FileUploadModule } from "primeng/fileupload";

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatButtonModule } from '@angular/material/button';

import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { NavigatorComponent } from './directives/navigation/navigator.component';

import { NgxElectronModule } from 'ngx-electron';

import { BookingModule } from './directives/booking/booking.module';

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
    MatSidenavModule,
    BookingModule,
    MatButtonModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
