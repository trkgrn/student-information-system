import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {SidebarComponent} from './modules/sidebar/sidebar.component';
import {NavbarComponent} from './modules/navbar/navbar.component';
import {RouterModule} from "@angular/router";
import {HomeComponent} from './modules/home/home.component';
import {AppRoutingModule} from "./app-routing.module";
import {AuthGuard} from "./modules/auth/auth.guard";
import {AuthService} from "./services/auth.service";
import {HttpService} from "./services/http.service";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {JwtInterceptor} from "./Interceptors/JwtInterceptor";
import {RoleService} from "./services/role.service";
import {FacultyService} from "./services/faculty.service";
import {ModalModule} from "./modules/modal/modal.module";
import {SweetAlert2Module} from "@sweetalert2/ngx-sweetalert2";
import {ReactiveFormsModule} from "@angular/forms";
import {BranchService} from "./services/branch.service";
import {ClassService} from "./services/class.service";

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    NavbarComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule,
    ModalModule,
    SweetAlert2Module.forRoot(),
    ReactiveFormsModule

  ],
  providers: [AuthGuard, AuthService, HttpService, RoleService, FacultyService,BranchService,ClassService, {
    provide: HTTP_INTERCEPTORS,
    useClass: JwtInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
