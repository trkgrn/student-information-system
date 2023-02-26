import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {TeacherRoutingModule} from "./teacher-routing.module";
import { LiveLessonComponent } from './live-lesson/live-lesson.component';
import { NotesByLiveLessonComponent } from './notes-by-live-lesson/notes-by-live-lesson.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { NoteFormModalComponent } from './modals/note-form-modal/note-form-modal.component';



@NgModule({
  declarations: [
    LiveLessonComponent,
    NotesByLiveLessonComponent,
    NoteFormModalComponent
  ],
    imports: [
        CommonModule,
        TeacherRoutingModule,
        FormsModule,
        ReactiveFormsModule
    ]
})
export class TeacherModule { }
