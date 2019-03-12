import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { BookingComponent } from "../booking/bookingPage/booking.component";
import { LedgerComponent } from "../ledger/ledgerPage/ledger.component";


const appRoutes: Routes = [
    {
        path:  'booking',
        component: BookingComponent
    },
    {
        path: 'ledger',
        component: LedgerComponent
    },
    {path: '', redirectTo: '/booking', pathMatch: 'full'}
]

@NgModule({
    imports: [
        RouterModule. forRoot(
            appRoutes,
            {
                enableTracing: false
            }
        )
        
    ],
    exports: [
                RouterModule
    ]
})
export class AppRoutingModule {}