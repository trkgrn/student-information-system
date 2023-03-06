import {Component, OnInit} from '@angular/core';
import {LiveLesson} from "../../../models/entity/LiveLesson";
import {LiveLessonService} from "../../../services/live-lesson.service";
import {AuthService} from "../../../services/auth.service";
import {TeacherService} from "../../../services/teacher.service";
import {Teacher} from "../../../models/entity/Teacher";
import {Note} from "../../../models/entity/Note";
import {NoteService} from "../../../services/note.service";
import {ModalService} from "../../modal/modal.service";
import {NoteFormModalComponent} from "../modals/note-form-modal/note-form-modal.component";

@Component({
  selector: 'app-notes-by-live-lesson',
  templateUrl: './notes-by-live-lesson.component.html',
  styleUrls: ['./notes-by-live-lesson.component.scss']
})
export class NotesByLiveLessonComponent implements OnInit {


  teacher: Teacher = new Teacher();
  lessons:LiveLesson[] = [];
  notes:Note[] = [];
  selectedLiveLesson: LiveLesson = new LiveLesson();
  constructor(private liveLessonService:LiveLessonService,private authService:AuthService
              ,private teacherService:TeacherService, private noteService:NoteService,private modalService:ModalService)
  { }

  async ngOnInit() {
    let teacher:Teacher = await this.teacherService.getAuthenticatedTeacher();
    this.lessons = await this.liveLessonService.getByTeacherId(teacher.teacherId as number);
    this.lessons = this.lessons.filter(lesson => lesson.isActive == true);
  }


  async getNotesByLiveLesson(){
    this.notes = await this.noteService.getNotesByLiveLessonId(this.selectedLiveLesson.liveLessonId as number);
  }

  updateNote(note: Note) {
    const modal = this.modalService.open(NoteFormModalComponent, {
      props: {
        method: 'UPDATE',
        note: note
      },
      centered: true,
      fullscreen: true,
      scrollable: true,
      backdropDismiss: false
    });

    modal.afterClose = (data) => {
      if (data.choose)
        this.noteService.update(data.data, data.data.noteId as number)
          .then(async (value: any) => {
            this.notes = await this.noteService.getNotesByLiveLessonId(this.selectedLiveLesson.liveLessonId as number);
          });
    }
  }

}
