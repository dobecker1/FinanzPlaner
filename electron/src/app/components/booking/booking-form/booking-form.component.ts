import { Component, Output, EventEmitter, OnInit } from '@angular/core';
import { LedgerService } from 'src/app/components/ledger/services/ledger.service';
import { Observable } from 'rxjs';
import { FormControl, NgForm } from '@angular/forms';
import {map, startWith, switchMap} from 'rxjs/operators';
import { Ledger } from 'src/app/models/ledger';
import { Booking } from 'src/app/models/booking';
import { BookingService } from '../services/booking.service';
import { NotificationService } from '../../ledger/services/notification.service';
import { BookingMetadata } from 'src/app/models/bookingMetadata';

@Component({
  selector: 'booking-form',
  templateUrl: './booking-form.component.html',
  styleUrls: ['./booking-form.component.css']
})
export class BookingFormComponent implements OnInit {

  ledgerShouldCtrl = new FormControl();
  ledgerHaveCtrl = new FormControl();
  subLedgerShouldCtrl = new FormControl();
  subLedgerHaveCtrl = new FormControl();
  ledgers: Ledger[];
  subLedgers: Ledger[];
  filteredLedgersShould: Observable<Ledger[]>;
  filteredLedgersHave: Observable<Ledger[]>;
  filteredSubLedgersShould: Observable<Ledger[]>;
  filteredSubLedgersHave: Observable<Ledger[]>;

  booking: Booking = new Booking();

  date: Date;
  referenceNumber: string;
  bookingDescription: string;
  ledgerShould: Ledger;
  subLedgerShould: Ledger;
  ledgerHave: Ledger;
  subLedgerHave: Ledger;
  value: number;

  @Output() booked: EventEmitter<BookingMetadata> = new EventEmitter<BookingMetadata>();

  constructor(private ledgerService: LedgerService, private bookingService: BookingService) {
  }

  ngOnInit() {
    this.initLedgers();
    this.initFormFields();
  }

  private initLedgers() {
    this.ledgerService.getLedgers(false)
    .subscribe(ledgers => {
      this.ledgers = ledgers;
      this.filteredLedgersShould = this.setUpFilterLedgerOnChange(this.ledgers, false, this.ledgerShouldCtrl);
      this.filteredLedgersHave = this.setUpFilterLedgerOnChange(this.ledgers, false, this.ledgerHaveCtrl);      
    });
    this.ledgerService.getLedgers(true)
    .subscribe(ledgers => {
      this.subLedgers = ledgers;
      this.filteredSubLedgersShould = this.setUpFilterLedgerOnChange(this.subLedgers, true, this.subLedgerShouldCtrl);
      this.filteredSubLedgersHave = this.setUpFilterLedgerOnChange(this.subLedgers, true, this.subLedgerHaveCtrl);
    });
  }

  private initFormFields() {
    this.booking.financialYear = new Date().getFullYear().toString();
  }

  private setUpFilterLedgerOnChange(ledgers: Ledger[], subLedger: boolean, ledgerCtrl: FormControl): Observable<Ledger[]> {
    return ledgerCtrl.valueChanges
    .pipe(
      startWith(''),
      map(ledger => ledger ? this.filterLedgers(ledgers, ledger, subLedger) : ledgers.slice())
    );
  }

  private filterLedgers(ledgers: Ledger[], value: string, subLedger: boolean): Ledger[] {
    const filterValue = value.toString().toLowerCase();
    return ledgers.filter(ledger => (ledger.subLedger == subLedger) && (ledger.name.toLowerCase().includes(filterValue) || ledger.ledgerNumber.toString().startsWith(value)));
  }

  onSubmit(bookingForm: NgForm) {
    let newBooking: BookingMetadata = new BookingMetadata();
    newBooking.bookingDescription = this.booking.bookingDescription;
    newBooking.date = this.booking.date;
    newBooking.referenceNumber = this.booking.referenceNumber;
    newBooking.ledgerShould = this.booking.ledgerShould.id;
    newBooking.ledgerHave = this.booking.ledgerHave.id;
    if(this.booking.subLedgerShould != undefined) {
      newBooking.subLedgerShould = this.booking.subLedgerShould.id;
    } else {
      newBooking.subLedgerShould = -1;
    }
    if(this.booking.subLedgerHave != undefined) {
      newBooking.subLedgerHave = this.booking.subLedgerHave.id;
    } else {
      newBooking.subLedgerHave = -1;
    }
    newBooking.referencePath = this.booking.referencePath;
    newBooking.financialYear = this.booking.financialYear;
    newBooking.value = this.booking.value;

    console.log(newBooking);
    this.bookingService.saveBookingMetadata(newBooking).subscribe(bookingId => {
      console.log("BookingId: " + bookingId);
      this.booked.emit();
      this.resetCommonFields(bookingForm);
      this.resetLedgers();      
    });
  }

  resetCommonFields(bookingForm: NgForm) {
    bookingForm.controls.bookingDescription.reset();
    bookingForm.controls.referencePath.reset();
    bookingForm.controls.value.reset();
  }

  resetLedgers() {
    this.subLedgerHaveCtrl.reset();
    this.subLedgerShouldCtrl.reset();
    this.ledgerHaveCtrl.reset();
    this.ledgerShouldCtrl.reset();
  }

  displayLedger(ledger) {
    return ledger ? ledger.ledgerNumber : "";
  }



}
