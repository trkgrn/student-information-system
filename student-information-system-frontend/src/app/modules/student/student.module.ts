import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LessonRequestComponent } from './lesson-request/lesson-request.component';
import {StudentRoutingModule} from "./student-routing.module";
import { SemesterGradeComponent } from './semester-grade/semester-grade.component';
import {FormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    LessonRequestComponent,
    SemesterGradeComponent
  ],
  imports: [
    CommonModule,
    StudentRoutingModule,
    FormsModule
  ]
})
export class StudentModule { }
