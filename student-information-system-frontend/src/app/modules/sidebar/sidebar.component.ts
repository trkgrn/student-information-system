import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {User} from "../../models/entity/User";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  title = '';
  currentActive = '';

  user:User = new User();

  constructor(public authService: AuthService) {
  }

  async ngOnInit() {
    this.title = this.authService.getRole().toLowerCase();
    this.title = this.title.charAt(0).toUpperCase() + this.title.slice(1);
    this.user = await this.authService.getAuthenticatedUser();
  }

  activeRoute(id: string) {
    this.clearPrevActive()
    document.getElementById(id)!.classList.add('active');
    this.currentActive = id;
  }

  clearPrevActive() {
    if (this.currentActive)
      document.getElementById(this.currentActive)!.classList.remove('active');
  }

}
