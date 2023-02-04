import {Class} from "./Class";
import {Teacher} from "./Teacher";
import {EducationSeason} from "./EducationSeason";
import {Lesson} from "./Lesson";
import {Period} from "./Period";

export class LiveLesson{
  liveLessonId?: number;
  _class?: Class;
  teacher?: Teacher;
  educationSeason?: EducationSeason;
  lesson?: Lesson;
  midtermPercent?: number;
  finalPercent?: number;
  isActive?: boolean;
  period?: Period;
}
