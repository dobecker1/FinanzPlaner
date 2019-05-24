import { ErrorHandler, Injectable, Injector } from "@angular/core";
import { HttpErrorResponse } from "@angular/common/http";
import { NotificationService } from "../../services/notification.service";

@Injectable()
export class LedgerErrorHandler implements ErrorHandler {
    constructor(private injector: Injector) {}


    handleError(error: Error | HttpErrorResponse) {
        const notifier = this.injector.get(NotificationService);
        if(error instanceof HttpErrorResponse) {
            notifier.showError(error.error.message);
        } else {
            notifier.showError(error.message);
        }
    }
}