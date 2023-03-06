import {Component, OnInit} from '@angular/core';
import {LiveLessonService} from "../../../services/live-lesson.service";
import {LiveLesson} from "../../../models/entity/LiveLesson";
import {AuthService} from "../../../services/auth.service";
import {TeacherService} from "../../../services/teacher.service";
import {Teacher} from "../../../models/entity/Teacher";

@Component({
  selector: 'app-live-lesson',
  templateUrl: './live-lesson.component.html',
  styleUrls: ['./live-lesson.component.scss']
})
export class LiveLessonComponent implements OnInit {

  lessons:LiveLesson[] = [];
  constructor(private liveLessonService:LiveLessonService,private authService:AuthService,private teacherService:TeacherService) { }

  async ngOnInit() {
    let teacher:Teacher = await this.teacherService.getAuthenticatedTeacher();
    this.lessons = await this.liveLessonService.getByTeacherId(teacher.teacherId as number);
    this.lessons = this.lessons.filter(lesson => lesson.isActive == true);
  }

}
