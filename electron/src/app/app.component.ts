import { Component } from '@angular/core';
import { ElectronService } from 'ngx-electron';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'ang-electron';

  uploadedFiles: any[] = [];
  uplFile: File;

  constructor(private electronService: ElectronService) {}

  onUpload(event) {
    for(let file of event.files) {
      this.uplFile = file;
      console.log(this.uplFile);
      this.electronService.ipcRenderer.send('onUploadFile', this.uplFile.path);
    }
  }
}
