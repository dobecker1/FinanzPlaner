import { Component, ViewChild } from '@angular/core';
import { ElectronService } from 'ngx-electron';
import { Booking } from './models/booking';
import { BookingListComponent } from './components/booking/booking-list/booking-list.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'ang-electron';

  uploadedFiles: any[] = [];
  uplFile: File;

  @ViewChild(BookingListComponent)
  private bookingList: BookingListComponent;

  constructor(private electronService: ElectronService) {}

  onUpload(event) {
    for(let file of event.files) {
      this.uplFile = file;
      console.log(this.uplFile);
      this.electronService.ipcRenderer.send('onUploadFile', this.uplFile.path);
    }
  }

  onBooked(booking: Booking) {
    this.bookingList.loadBookings();
  }
}
