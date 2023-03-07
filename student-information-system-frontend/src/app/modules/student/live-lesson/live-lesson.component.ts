import { Component, OnInit } from '@angular/core';
import {LiveLesson} from "../../../models/entity/LiveLesson";
import {LiveLessonService} from "../../../services/live-lesson.service";
import {AuthService} from "../../../services/auth.service";
import {TeacherService} from "../../../services/teacher.service";
import {Teacher} from "../../../models/entity/Teacher";
import {StudentService} from "../../../services/student.service";
import {Student} from "../../../models/entity/Student";

@Component({
  selector: 'app-live-lesson',
  templateUrl: './live-lesson.component.html',
  styleUrls: ['./live-lesson.component.scss']
})
export class LiveLessonComponent implements OnInit {

  lessons:LiveLesson[] = [];
  constructor(private liveLessonService:LiveLessonService,private authService:AuthService,private studentService:StudentService) { }

  async ngOnInit() {
    let student:Student = await this.studentService.getAuthenticatedStudent();
    this.lessons = await this.liveLessonService.getByStudentId(student.studentId as number);
    this.lessons = this.lessons.filter(lesson => lesson.isActive == true);
  }

}
