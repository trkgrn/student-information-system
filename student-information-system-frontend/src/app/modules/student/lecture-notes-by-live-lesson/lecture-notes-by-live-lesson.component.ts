import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {LectureNoteService} from "../../../services/lecture-note.service";
import {FileUtil} from "../../resource/file-util";
import {LectureNote} from "../../../models/entity/LectureNote";
import {LiveLesson} from "../../../models/entity/LiveLesson";

@Component({
  selector: 'app-lecture-notes-by-live-lesson',
  templateUrl: './lecture-notes-by-live-lesson.component.html',
  styleUrls: ['./lecture-notes-by-live-lesson.component.scss']
})
export class LectureNotesByLiveLessonComponent implements OnInit {
  liveLessonId?: number;
  lectureNote?: LectureNote;
  liveLesson?: LiveLesson;
  constructor(private route: ActivatedRoute, private lectureNoteService: LectureNoteService, public fileUtil: FileUtil) {
  }

  async ngOnInit() {
    let id: any = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.liveLessonId = id;
      this.lectureNote = await this.lectureNoteService.getByLiveLessonId(this.liveLessonId as number);
      this.liveLesson = this.lectureNote?.liveLesson;
    }
  }

}
