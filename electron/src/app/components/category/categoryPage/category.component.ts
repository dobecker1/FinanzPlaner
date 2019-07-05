import { Component } from "@angular/core";
import { Category } from "src/app/models/category";
import { FlatTreeControl } from "@angular/cdk/tree";
import { MatTreeFlattener, MatTreeFlatDataSource } from "@angular/material";

@Component({
    selector: 'category',
    templateUrl: './category.component.html',
    styleUrls: ['./category.component.css']
})
export class CategoryComponent {

    private transformer = (node: CategoryNode, level: number) => {
        return {
            expandable: !!node.children && node.children.length > 0,
            name: node.name,
            level: level
        };
    }

    treeControl = new FlatTreeControl<ExampleFlatNode>(
        node => node.level, node => node.expandable
    );

    treeFlattener = new MatTreeFlattener(
        this.transformer, node => node.level, node => node.expandable, node => node.children
    );

    dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);

    constructor() {
        this.dataSource.data = CATEGORY_DATA;
    }

    hasChild = (_: number, node: ExampleFlatNode) => node.expandable;
}

interface CategoryNode {
    name: string;
    children?: CategoryNode[];
}

interface ExampleFlatNode {
    expandable: boolean;
    name: string;
    level: number;
}

const CATEGORY_DATA: CategoryNode[] = [
    {
        name: "Auto",
        children: [
            {name: "Opel Corsa"},
            {name: "VW Golf GTE"},
            {name: "VW Passat"}
        ]
    },
    {
        name: "Computerspiele",
        children: [
            {name: "Magic Arena"},
            {name: "World of Warcraft"},
            {name: "Hearthstone"}
        ]
    },
    {
        name: "Softare AG"
    }
]