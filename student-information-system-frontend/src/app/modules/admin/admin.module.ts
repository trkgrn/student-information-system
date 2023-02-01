import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AdminRoutingModule} from "./admin-routing.module";
import { FacultyComponent } from './faculty/faculty.component';
import { BranchComponent } from './branch/branch.component';
import { ClassComponent } from './class/class.component';



@NgModule({
  declarations: [
    FacultyComponent,
    BranchComponent,
    ClassComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
