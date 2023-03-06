import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {User} from "../../models/entity/User";
import {Router} from "@angular/router";
import {StudentService} from "../../services/student.service";
import {Student} from "../../models/entity/Student";
import {ModalService} from "../modal/modal.service";
import {UserUpdateFormModalComponent} from "./user-update-form-modal/user-update-form-modal.component";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  user: User = new User();
  student:Student = new Student();

  constructor(public authService: AuthService,private studentService:StudentService,private router:Router,
              private modalService:ModalService,private userService:UserService) { }

  async ngOnInit() {
    this.user = await this.authService.getAuthenticatedUser();
    if (this.user.role?.name === 'STUDENT') {
      this.student = await this.studentService.getByUserId(this.user.userId as number);
    }
  }

  async logout() {
    await this.authService.logout()
      .then(() => {
        localStorage.clear();
        this.router.navigateByUrl('/auth/login');
      })
      .catch((err:any) => {
        console.log(err);
      });
  }

  openUpdateFormModal() {
    const modal = this.modalService.open(UserUpdateFormModalComponent, {
      props: {
        method: 'UPDATE',
        user: this.user
      },
      centered: true,
      fullscreen: true,
      scrollable: true,
      backdropDismiss: false
    });

    modal.afterClose = (data) => {
      console.log(data.data)
      if (data.choose)
        this.userService.update(data.data,data.data.userId)
          .then((res:User) => {
          this.user = res;
        });

    }
  }

}
