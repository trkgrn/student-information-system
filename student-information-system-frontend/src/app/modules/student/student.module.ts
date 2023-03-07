import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LessonRequestComponent} from './lesson-request/lesson-request.component';
import {StudentRoutingModule} from "./student-routing.module";
import {SemesterGradeComponent} from './semester-grade/semester-grade.component';
import {FormsModule} from "@angular/forms";
import { LiveLessonComponent } from './live-lesson/live-lesson.component';
import { LectureNotesByLiveLessonComponent } from './lecture-notes-by-live-lesson/lecture-notes-by-live-lesson.component';


@NgModule({
  declarations: [
    LessonRequestComponent,
    SemesterGradeComponent,
    LiveLessonComponent,
    LectureNotesByLiveLessonComponent
  ],
  imports: [
    CommonModule,
    StudentRoutingModule,
    FormsModule
  ]
})
export class StudentModule { }
