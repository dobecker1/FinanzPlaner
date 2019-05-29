import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { BookingComponent } from "../booking/bookingPage/booking.component";
import { LedgerComponent } from "../ledger/ledgerPage/ledger.component";
import { CategoryComponent } from "../category/categoryPage/category.component";


const appRoutes: Routes = [
    {
        path:  'booking',
        component: BookingComponent
    },
    {
        path: 'ledger',
        component: LedgerComponent
    },
    {
        path: 'category',
        component: CategoryComponent
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