import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {TeacherRoutingModule} from "./teacher-routing.module";
import { LiveLessonComponent } from './live-lesson/live-lesson.component';



@NgModule({
  declarations: [
    LiveLessonComponent
  ],
  imports: [
    CommonModule,
    TeacherRoutingModule
  ]
})
export class TeacherModule { }
