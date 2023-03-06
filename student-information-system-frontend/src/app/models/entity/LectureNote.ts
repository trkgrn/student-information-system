import {LiveLesson} from "./LiveLesson";
import {WeeklyNotes} from "./WeeklyNotes";

export class LectureNote {
  lectureNoteId?: number;
  liveLesson?: LiveLesson;
  weeklyNotes?: WeeklyNotes[];

  constructor(liveLesson?: LiveLesson) {
    this.liveLesson = liveLesson;
  }
}


