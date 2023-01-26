import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  isUserSignedIn() {
    return localStorage.getItem('token') !== null;
  }

  getRole() {
    let role = localStorage.getItem("role") as string;
    return role;
  }

  roleMatch(allowedRoles: any) {
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


}
