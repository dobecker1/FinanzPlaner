import { Component } from '@angular/core';
import { LedgerService } from 'src/app/services/ledger.service';

@Component({
  selector: 'booking-form',
  templateUrl: './booking-form.component.html',
  styleUrls: ['./booking-form.component.css']
})
export class BookingFormComponent {

  ledgers: Ledger[];

  constructor(private ledgerService: LedgerService) {
    this.ledgers = ledgerService.getAllLedgers();
  }

}
