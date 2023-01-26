import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {NotfoundComponent} from "./notfound/notfound.component";
import {UnauthorizedComponent} from "./unauthorized/unauthorized.component";
import {ForbiddenComponent} from "./forbidden/forbidden.component";

const routes: Routes = [
  {path: 'notfound', component: NotfoundComponent},
  {path: 'unauthorized', component: UnauthorizedComponent},
  {path: 'forbidden', component: ForbiddenComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ErrorRoutingModule {
}
