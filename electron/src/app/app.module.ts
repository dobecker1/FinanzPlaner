import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgModule } from '@angular/core';


import { AccordionModule } from "primeng/accordion";
import { FileUploadModule } from "primeng/fileupload";

import { AppComponent } from './app.component';

import { NgxElectronModule } from 'ngx-electron';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    NgxElectronModule,
    AccordionModule,
    FileUploadModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
