import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../services/auth.service";
import {Router} from "@angular/router";
import {User} from "../../../models/entity/User";
import {Role} from "../../../models/entity/Role";
import {RoleService} from "../../../services/role.service";
import {StudentService} from "../../../services/student.service";
import {TeacherService} from "../../../services/teacher.service";
import {Student} from "../../../models/entity/Student";
import {Teacher} from "../../../models/entity/Teacher";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;
  roles: Role[] = [];

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router,
              private roleService: RoleService, private studentService: StudentService, private teacherService: TeacherService) {
    this.form = formBuilder.group({
      firstName: [null, Validators.required],
      lastName: [null, Validators.required],
      tckNo: [null, [Validators.required, Validators.pattern("^[1-9]{1}[0-9]{9}[02468]{1}$")]],
      telNo: [null, [Validators.required, Validators.pattern("^[0-9]{10,10}$")]],
      address: [null, Validators.required],
      email: [null, [Validators.required, Validators.pattern("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")]],
      password: [null, [Validators.required, Validators.minLength(5)]],
      role: [null, Validators.required]
    });
  }

  async ngOnInit() {
    this.roles = await this.roleService.getAll()
  }

  async register() {
    let user: User = this.form.value;
    user.role = this.roles.find(role => role.roleId == user.role);
    await this.authService.register(user)
      .then(async (resp: User) => {
        if (resp.role?.name == "STUDENT") {
          await this.studentService.create({studentId: 0, studentNo: "", user: resp} as Student);
        } else if (resp.role?.name == "TEACHER") {
          await this.teacherService.create({teacherId: 0, user: resp} as Teacher);
        }
        await this.router.navigateByUrl('/auth/login');
      }).catch((err: any) => {
        console.log(err);
      });
  }

}
