import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {LectureNoteService} from "../../../services/lecture-note.service";
import {LectureNote} from "../../../models/entity/LectureNote";
import {LiveLesson} from "../../../models/entity/LiveLesson";
import {ModalService} from "../../modal/modal.service";
import {FileUploaderComponent} from "../../resource/file-uploader/file-uploader.component";
import {FileUtil} from "../../resource/file-util";
import {CloudStorageService} from "../../../services/cloud-storage.service";
import {File} from "../../../models/entity/File";
import {WeeklyNotesService} from "../../../services/weekly-notes.service";
import Swal from "sweetalert2";

declare var $: any;

@Component({
  selector: 'app-lecture-notes-by-live-lesson',
  templateUrl: './lecture-notes-by-live-lesson.component.html',
  styleUrls: ['./lecture-notes-by-live-lesson.component.scss']
})
export class LectureNotesByLiveLessonComponent implements OnInit {

  liveLessonId?: number;
  lectureNote?: LectureNote;
  liveLesson?: LiveLesson;

  description: string = ""
  editMode: boolean= false;
  editedWeeklyNoteId?: number;

  constructor(private route: ActivatedRoute, private lectureNoteService: LectureNoteService, private modalService: ModalService
    , public fileUtil: FileUtil, private cloud: CloudStorageService, private weeklyNotesService: WeeklyNotesService) {
  }


  editDescription(desc:string,weeklyNotesId:number) {
    this.editMode = true;
    this.description = desc;
    this.editedWeeklyNoteId = weeklyNotesId;
  }
  editDescriptionCancel() {
    this.editMode = false;
    this.editedWeeklyNoteId = undefined;
    this.description = "";
    $('#editDescription').modal('hide')
  }
  async editDescriptionSave() {

    if (this.editedWeeklyNoteId) {
      await this.weeklyNotesService.updateDescription(this.description,this.editedWeeklyNoteId).then(async () => {
        this.lectureNote = await this.lectureNoteService.getByLiveLessonId(this.liveLessonId as number);
        this.editDescriptionCancel();
      });
    }
  }


  async ngOnInit() {
    let id: any = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.liveLessonId = id;
      this.lectureNote = await this.lectureNoteService.getByLiveLessonId(this.liveLessonId as number);
      this.liveLesson = this.lectureNote?.liveLesson;
    }
  }


  newDocs(weeklyNoteId?: number) {
    const modal = this.modalService.open(FileUploaderComponent, {
      props: {
        title: 'Upload File',
        weeklyNoteId: weeklyNoteId
      },
      centered: true,
      scrollable: true,
      backdropDismiss: false,
      size: 'lg'
    });

    modal.afterClose = async (data) => {
      if (data.change) {
        this.lectureNote = await this.lectureNoteService.getByLiveLessonId(this.liveLessonId as number);
      }
    }
  }

  async deleteDocs(weeklyNotesId: number, file: File) {

    const sweetAlert = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: true,
    });

    sweetAlert.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, cancel!',
      reverseButtons: true
    }).then(async (result) => {
      if (result.value) {
        await this.delete(weeklyNotesId, file);
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        await sweetAlert.fire(
          'Cancelled',
          'Successfully cancelled.',
          'error'
        )
      }
    });

  }

  async delete(weeklyNotesId: number, file: File) {
    await this.cloud.deleteFileStorage(file.cloudPath!).then(async () => {
      await this.weeklyNotesService.deleteFile(file, weeklyNotesId)
        .then(async () => {
          this.lectureNote = await this.lectureNoteService.getByLiveLessonId(this.liveLessonId as number);
        })

    });
  }

}
