import { OnInit, Input, Component } from "@angular/core";
import { Category } from "src/app/models/category";

@Component({
    selector: 'category-form',
    templateUrl: './category-form.component.html',
    styleUrls: [ './category-form.component.css' ]
})
export class CategoryFormComponent implements OnInit {

    @Input() category: Category;

    constructor() {}

    ngOnInit(): void {
       
    }

    onSubmit() {}

}