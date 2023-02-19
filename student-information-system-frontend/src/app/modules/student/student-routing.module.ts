import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {HomeComponent} from "../home/home.component";
import {LessonRequestComponent} from "./lesson-request/lesson-request.component";
import {SemesterGradeComponent} from "./semester-grade/semester-grade.component";


const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'lesson-request', component: LessonRequestComponent},
  {path: 'semester-grade', component: SemesterGradeComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule {
}
