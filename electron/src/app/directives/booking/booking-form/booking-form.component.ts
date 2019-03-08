import { Component } from '@angular/core';
import { LedgerService } from 'src/app/services/ledger.service';
import { Observable } from 'rxjs';
import { FormControl } from '@angular/forms';
import {map, startWith} from 'rxjs/operators';
import { Ledger } from 'src/app/models/ledger';

@Component({
  selector: 'booking-form',
  templateUrl: './booking-form.component.html',
  styleUrls: ['./booking-form.component.css']
})
export class BookingFormComponent {

  ledgerCtrl = new FormControl();
  ledgers: Ledger[];
  filteredLedgers: Observable<Ledger[]>;

  constructor(private ledgerService: LedgerService) {
    this.ledgers = this.ledgerService.getAllLedgers();
    this.filteredLedgers = this.ledgerCtrl.valueChanges
    .pipe(
      startWith(''),
      map(ledger => ledger ? this.filterLedgers(ledger) : this.ledgers.slice())
    );

  }

  private filterLedgers(value: string): Ledger[] {
    const filterValue = value.toString().toLowerCase();

    return this.ledgers.filter(ledger => ledger.name.toLowerCase().indexOf(filterValue) === 0 || ledger.ledgerNumber.toString().startsWith(value));
  }

}
