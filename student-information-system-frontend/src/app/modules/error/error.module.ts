import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ErrorRoutingModule} from "./error-routing.module";
import {NotfoundComponent} from './notfound/notfound.component';
import {UnauthorizedComponent} from './unauthorized/unauthorized.component';
import {ForbiddenComponent} from './forbidden/forbidden.component';

@NgModule({
  declarations: [
    NotfoundComponent,
    UnauthorizedComponent,
    ForbiddenComponent
  ],
  imports: [
    CommonModule,
    ErrorRoutingModule
  ]
})
export class ErrorModule { }
