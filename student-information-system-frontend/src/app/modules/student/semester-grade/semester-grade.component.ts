import {Component, OnInit} from '@angular/core';
import {NoteService} from "../../../services/note.service";
import {Note} from "../../../models/entity/Note";
import {Student} from "../../../models/entity/Student";
import {StudentService} from "../../../services/student.service";
import {Semester} from "../../../models/entity/Semester";

@Component({
  selector: 'app-semester-grade',
  templateUrl: './semester-grade.component.html',
  styleUrls: ['./semester-grade.component.scss']
})
export class SemesterGradeComponent implements OnInit {

  student: Student = new Student();
  notes: Note[] = [];
  semesters: Semester[] = [];
  selectedSemester: Semester = new Semester();

  constructor(private noteService: NoteService, private studentService: StudentService) {
  }

  async ngOnInit() {
    this.student = await this.studentService.getAuthenticatedStudent();
    await this.noteService.getSemestersByStudentId(this.student.studentId as number)
      .then(async (res: any) => {
        if (res.length > 0) {
          this.semesters = res;
          this.selectedSemester = res[0];
          this.notes = await this.noteService.getNotesByStudentIdAndSemester(this.student.studentId as number,
            this.selectedSemester.educationSeason?.educationSeasonId as number,
            this.selectedSemester.period?.periodId as number)
        }
      });
  }

   async notesBySemester() {
    console.log(this.selectedSemester);
    this.notes = await this.noteService.getNotesByStudentIdAndSemester(this.student.studentId as number,
      this.selectedSemester.educationSeason?.educationSeasonId as number,
      this.selectedSemester.period?.periodId as number)
  }


}
