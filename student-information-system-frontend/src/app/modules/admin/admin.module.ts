import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AdminRoutingModule} from "./admin-routing.module";
import { FacultyComponent } from './faculty/faculty.component';
import { BranchComponent } from './branch/branch.component';
import { ClassComponent } from './class/class.component';
import { FacultyFormModalComponent } from './modals/faculty-form-modal/faculty-form-modal.component';
import {SweetAlert2Module} from "@sweetalert2/ngx-sweetalert2";
import {ReactiveFormsModule} from "@angular/forms";
import { BranchFormModalComponent } from './modals/branch-form-modal/branch-form-modal.component';
import { ClassFormModalComponent } from './modals/class-form-modal/class-form-modal.component';
import { LessonComponent } from './lesson/lesson.component';
import { LessonFormModalComponent } from './modals/lesson-form-modal/lesson-form-modal.component';
import { LiveLessonComponent } from './live-lesson/live-lesson.component';
import { LiveLessonFormModalComponent } from './modals/live-lesson-form-modal/live-lesson-form-modal.component';
import { LessonRequestComponent } from './lesson-request/lesson-request.component';



@NgModule({
  declarations: [
    FacultyComponent,
    BranchComponent,
    ClassComponent,
    FacultyFormModalComponent,
    BranchFormModalComponent,
    ClassFormModalComponent,
    LessonComponent,
    LessonFormModalComponent,
    LiveLessonComponent,
    LiveLessonFormModalComponent,
    LessonRequestComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    SweetAlert2Module,
    ReactiveFormsModule
  ]
})
export class AdminModule { }
