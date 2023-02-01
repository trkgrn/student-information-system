import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, NgForm, Validators} from "@angular/forms";
import {AuthService} from "../../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: FormGroup;

  constructor(private formBuilder: FormBuilder, private authService: AuthService,
              private router: Router) {
    this.form = formBuilder.group({
      tckNo: [null, Validators.required],
      password: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    localStorage.clear()
  }

  async login() {
    let auth = this.form.value;

    await this.authService.login(auth)
      .then((resp: any) => {
        if (typeof auth.tckNo === "string") {
          localStorage.setItem('token', resp.token as string);
          localStorage.setItem('role', resp.role as string);
        }

        this.navigateByRole();

      }).catch((err: any) => {
        alert(err);
      });

  }

  navigateByRole() {
    switch (this.authService.getRole()) {
      case 'ADMIN':
        this.router.navigateByUrl('/admin');
        break;
      case 'STUDENT':
        this.router.navigateByUrl('/home');
        break;
      case 'TEACHER':
        this.router.navigateByUrl('/home');
        break;
    }
  }


}
