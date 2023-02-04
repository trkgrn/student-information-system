import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./modules/home/home.component";
import {AuthGuard} from "./modules/auth/auth.guard";
import {ProfileComponent} from "./modules/profile/profile.component";

const routes: Routes = [
  {path: '', redirectTo: 'profile', pathMatch: 'full'},
  {path: "home", component: HomeComponent, canActivate: [AuthGuard], data: {roles: ["TEACHER", "STUDENT", "ADMIN"]}},
  {path: "a/home", component: HomeComponent, canActivate: [AuthGuard], data: {roles: ["TEACHER", "STUDENT", "ADMIN"]}},
  {path: "a/home2", component: HomeComponent, canActivate: [AuthGuard], data: {roles: ["TEACHER", "STUDENT", "ADMIN"]}},
  {path: "a/home3", component: HomeComponent, canActivate: [AuthGuard], data: {roles: ["TEACHER", "STUDENT", "ADMIN"]}},
  {path: "widgets", component: HomeComponent, canActivate: [AuthGuard], data: {roles: ["TEACHER", "STUDENT", "ADMIN"]}},
  {path: "profile", component: ProfileComponent, canActivate: [AuthGuard], data: {roles: ["TEACHER", "STUDENT", "ADMIN"]}},
  {path: 'auth', loadChildren: () => import('./modules/auth/auth.module').then(m => m.AuthModule)},
  {path: 'error', loadChildren: () => import('./modules/error/error.module').then(m => m.ErrorModule)},
  {
    path: 'admin',
    loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule),
    canActivate: [AuthGuard],
    data: {roles: ["ADMIN"]}
  },
  {path: "**", redirectTo: "error/notfound", pathMatch: "full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
