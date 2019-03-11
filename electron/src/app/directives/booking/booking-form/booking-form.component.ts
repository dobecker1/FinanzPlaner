import { Component } from '@angular/core';
import { LedgerService } from 'src/app/services/ledger.service';
import { Observable } from 'rxjs';
import { FormControl } from '@angular/forms';
import {map, startWith} from 'rxjs/operators';
import { Ledger } from 'src/app/models/ledger';
import { copyFile } from 'fs';
import { Booking } from 'src/app/models/booking';
import { BookingService } from 'src/app/services/booking.service';

@Component({
  selector: 'booking-form',
  templateUrl: './booking-form.component.html',
  styleUrls: ['./booking-form.component.css']
})
export class BookingFormComponent {

  ledgerShouldCtrl = new FormControl();
  ledgerHaveCtrl = new FormControl();
  ledgers: Ledger[];
  filteredLedgers: Observable<Ledger[]>;

  date: Date;
  referenceNumber: string;
  bookingDescription: string;
  ledgerShould: Ledger;
  subLedgerShould: Ledger;
  ledgerHave: Ledger;
  subLedgerHave: Ledger;
  value: number;

  constructor(private ledgerService: LedgerService, private bookingService: BookingService) {
    this.ledgers = this.ledgerService.getAllLedgers();
    this.filteredLedgers = this.ledgerShouldCtrl.valueChanges
    .pipe(
      startWith(''),
      map(ledger => ledger ? this.filterLedgers(ledger) : this.ledgers.slice())
    );
    this.filteredLedgers = this.ledgerHaveCtrl.valueChanges
    .pipe(
      startWith(''),
      map(ledger => ledger ? this.filterLedgers(ledger) : this.ledgers.slice())
    );
  }

  private filterLedgers(value: string): Ledger[] {
    const filterValue = value.toString().toLowerCase();

    return this.ledgers.filter(ledger => ledger.name.toLowerCase().indexOf(filterValue) === 0 || ledger.ledgerNumber.toString().startsWith(value));
  }

  private searchForLedger(ledgerNumber: number) : Ledger{
    for(let i: number = 0; i < this.ledgers.length; i++) {
      if(this.ledgers[i].ledgerNumber === ledgerNumber) {
        return this.ledgers[i];
      } else if(i === this.ledgers.length -1) {
        return null;
      }      
    }
  }


  onSaveBookingClick() {
    let booking: Booking = new Booking();
    booking.date = this.date;
    booking.referenceNumber = this.referenceNumber;
    booking.bookingDescription = this.bookingDescription;
    let ledgerShould = this.ledgerShouldCtrl.value;
    if(this.ledgerShouldCtrl.value != null) {
      typeof ledgerShould === 'object' ? booking.ledgerShould = ledgerShould : 
      booking.ledgerShould = this.searchForLedger(parseInt(ledgerShould));
    }
    let ledgerHave = this.ledgerHaveCtrl.value;
    if(ledgerHave != null) {
      typeof ledgerHave === 'object' ? booking.ledgerHave = ledgerHave : 
      booking.ledgerHave = this.searchForLedger(parseInt(ledgerHave));
    }
    booking.value = this.value;
    this.bookingService.saveBooking(booking);
  }

  displayLedger(ledger) {
    return ledger ? ledger.ledgerNumber : "";
  }

}
