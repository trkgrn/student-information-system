import {Component, OnInit} from '@angular/core';
import {LiveLesson} from "../../../models/entity/LiveLesson";
import {LiveLessonService} from "../../../services/live-lesson.service";
import {LessonRequestService} from "../../../services/lesson-request.service";
import {LessonRequest} from "../../../models/entity/LessonRequest";
import {StudentService} from "../../../services/student.service";
import {Student} from "../../../models/entity/Student";

@Component({
  selector: 'app-lesson-request',
  templateUrl: './lesson-request.component.html',
  styleUrls: ['./lesson-request.component.scss']
})
export class LessonRequestComponent implements OnInit {

  student: Student = new Student();
  lessons: LiveLesson[] = [];
  requests: LessonRequest[] = [];

  constructor(private liveLessonService: LiveLessonService, private studentService: StudentService,
              private lessonRequestService: LessonRequestService) {
  }

  async ngOnInit() {
    this.student = await this.studentService.getAuthenticatedStudent();
    this.lessons = await this.liveLessonService.getAvailableLessons();
    this.requests = await this.lessonRequestService.getLessonRequestsByStudentId(this.student.studentId as number);
  }

  async createLessonRequest(lesson: any) {
    let lessonRequest:LessonRequest = {lessonRequestId: 0, liveLesson: lesson, student: this.student, isApproved: undefined}
    await this.lessonRequestService.create(lessonRequest)
      .then(async () => {
      this.lessons = await this.liveLessonService.getAvailableLessons();
      this.requests = await this.lessonRequestService.getLessonRequestsByStudentId(this.student.studentId as number);
    });
  }

}
