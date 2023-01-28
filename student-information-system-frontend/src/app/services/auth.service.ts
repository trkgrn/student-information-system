import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {map, Observable} from "rxjs";
import {HttpService} from "./http.service";
import {AuthRequest} from "../models/request/AuthRequest";
import {AuthResponse} from "../models/response/AuthResponse";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private router:Router, private http:HttpService) { }

  getRole() {
    return localStorage.getItem("role") as string;
  }

  getToken() {
    return localStorage.getItem('token') as string;
  }

  isSignedIn() {
    return localStorage.getItem('token') !== null;
  }

  isRoleMatch(allowedRoles: any) {
    let isMatch = false;
    const userRole = this.getRole()

    if (userRole != null && userRole) {
      for (let j = 0; j < allowedRoles.length; j++) {
        if (userRole == allowedRoles[j]) {
          isMatch = true;
          return isMatch;
        }
        else {
          continue;
        }
      }
    }
    return isMatch
  }

  unauthorized() {
    localStorage.removeItem('token');
    this.router.navigateByUrl('unauthorized');
  }

  signOut() {
    localStorage.removeItem('token');
    this.router.navigateByUrl('auth/login');
  }

  login(auth: AuthRequest) {
    return this.http.post('/auth/login', auth);
  }

  register(user: any) {
    return this.http.post("/auth/register", user);
  }

}
