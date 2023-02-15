import { Component, OnInit } from '@angular/core';
import {LiveLessonService} from "../../../services/live-lesson.service";
import {LiveLesson} from "../../../models/entity/LiveLesson";
import {UserService} from "../../../services/user.service";
import {AuthService} from "../../../services/auth.service";
import {User} from "../../../models/entity/User";

@Component({
  selector: 'app-live-lesson',
  templateUrl: './live-lesson.component.html',
  styleUrls: ['./live-lesson.component.scss']
})
export class LiveLessonComponent implements OnInit {

  lessons:LiveLesson[] = [];
  constructor(private liveLessonService:LiveLessonService,private authService:AuthService) { }

  async ngOnInit() {
    let user:User = await this.authService.getAuthenticatedUser();
    this.lessons = await this.liveLessonService.getByUserId(user.userId as number);
    this.lessons = this.lessons.filter(lesson => lesson.isActive == true);
  }

}
