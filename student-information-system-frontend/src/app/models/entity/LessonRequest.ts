import {Student} from "./Student";
import {LiveLesson} from "./LiveLesson";

export class LessonRequest{
  constructor(number: number, lesson: LiveLesson, student: any, b?: boolean) {
  }

  lessonRequestId?: number;
  liveLesson?:LiveLesson;
  student?:Student;
  isApproved?:boolean;
}
