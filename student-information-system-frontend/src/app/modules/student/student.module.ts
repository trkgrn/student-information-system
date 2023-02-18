import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LessonRequestComponent } from './lesson-request/lesson-request.component';
import {StudentRoutingModule} from "./student-routing.module";



@NgModule({
  declarations: [
    LessonRequestComponent
  ],
  imports: [
    CommonModule,
    StudentRoutingModule
  ]
})
export class StudentModule { }
