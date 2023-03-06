import {LectureNote} from "./LectureNote";
import {File} from "./File";

export class WeeklyNotes {
  weeklyNotesId?: number;
  description?: string;
  weekNumber?: number;
  lectureNote?: LectureNote;
  files?: File[];
}
