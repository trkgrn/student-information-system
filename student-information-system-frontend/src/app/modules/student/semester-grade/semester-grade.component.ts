import { Component, OnInit } from '@angular/core';
import {NoteService} from "../../../services/note.service";
import {Note} from "../../../models/entity/Note";
import {Student} from "../../../models/entity/Student";
import {StudentService} from "../../../services/student.service";

@Component({
  selector: 'app-semester-grade',
  templateUrl: './semester-grade.component.html',
  styleUrls: ['./semester-grade.component.scss']
})
export class SemesterGradeComponent implements OnInit {

  student: Student = new Student();
  notes: Note[] = [];
  constructor(private noteService:NoteService, private studentService:StudentService) { }

  async ngOnInit() {
  this.student = await this.studentService.getAuthenticatedStudent();
  this.notes = await this.noteService.getNotesByStudentId(this.student.studentId as number);
  }


}
