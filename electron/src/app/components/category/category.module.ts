import { NgModule } from "@angular/core";

//Angular Material
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatTreeModule } from '@angular/material/tree';

//Category Components
import { CategoryFormComponent } from './category-form/category-form.component';
import { CategoryComponent } from './categoryPage/category.component';

@NgModule({
    imports: [
        MatButtonModule,
        MatIconModule,
        MatGridListModule,
        MatTreeModule
    ],
    declarations: [
        CategoryFormComponent,
        CategoryComponent
    ],
    providers: [],
    exports: [
        CategoryFormComponent,
        CategoryComponent
    ]
})
export class CategoryModule {}