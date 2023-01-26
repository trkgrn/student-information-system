import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {AuthGuard} from "./components/auth/auth.guard";

const routes: Routes = [
  {path:"home",component:HomeComponent,canActivate:[AuthGuard],data:{roles:["Teacher","Student"]}},
  {path: 'auth', loadChildren: () => import('./components/auth/auth.module').then(m => m.AuthModule)},
  {path: 'error', loadChildren: () => import('./components/error/error.module').then(m => m.ErrorModule)},
  {path: "**", redirectTo: "error/notfound", pathMatch: "full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
