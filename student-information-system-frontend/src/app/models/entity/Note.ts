import {Student} from "./Student";
import {LiveLesson} from "./LiveLesson";
import {LetterGrade} from "./LetterGrade";

export class Note{
  noteId?: number;
  state?: string;
  midtermExam?: number;
  finalExam?: number;
  average?: number;
  letterGrade?: LetterGrade;
  student?: Student;
  liveLesson?: LiveLesson;
}
