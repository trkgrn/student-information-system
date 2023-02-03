import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {HomeComponent} from "../home/home.component";
import {FacultyComponent} from "./faculty/faculty.component";
import {BranchComponent} from "./branch/branch.component";
import {ClassComponent} from "./class/class.component";
import {LessonComponent} from "./lesson/lesson.component";
import {LiveLessonComponent} from "./live-lesson/live-lesson.component";

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'faculty', component: FacultyComponent},
  {path: 'branch', component: BranchComponent},
  {path: 'class', component: ClassComponent},
  {path: 'lesson', component: LessonComponent},
  {path: 'live-lesson', component: LiveLessonComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {
}
