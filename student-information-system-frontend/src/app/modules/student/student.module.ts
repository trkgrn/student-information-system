import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LessonRequestComponent } from './lesson-request/lesson-request.component';
import {StudentRoutingModule} from "./student-routing.module";
import { SemesterGradeComponent } from './semester-grade/semester-grade.component';



@NgModule({
  declarations: [
    LessonRequestComponent,
    SemesterGradeComponent
  ],
  imports: [
    CommonModule,
    StudentRoutingModule
  ]
})
export class StudentModule { }
