import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LiveLessonComponent} from "./live-lesson/live-lesson.component";
import {NotesByLiveLessonComponent} from "./notes-by-live-lesson/notes-by-live-lesson.component";

const routes: Routes = [
  {path: 'lessons', component: LiveLessonComponent},
  {path: 'notes', component: NotesByLiveLessonComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeacherRoutingModule {
}
