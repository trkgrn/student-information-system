import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(public authService:AuthService, private router:Router) { }

  ngOnInit(): void {
  }

  async logout() {
   await this.authService.logout()
     .then(() => {
        this.router.navigate(['/auth/login']);
       localStorage.clear();
     })
     .catch((err:any) => {
        console.log(err);
     });
  }

}
