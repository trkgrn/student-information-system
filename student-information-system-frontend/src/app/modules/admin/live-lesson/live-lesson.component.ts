import {Component, OnInit} from '@angular/core';
import {ModalService} from "../../modal/modal.service";
import Swal from "sweetalert2";
import {LiveLessonService} from "../../../services/live-lesson.service";
import {LiveLesson} from "../../../models/entity/LiveLesson";
import {LiveLessonFormModalComponent} from "../modals/live-lesson-form-modal/live-lesson-form-modal.component";
import {LectureNoteService} from "../../../services/lecture-note.service";
import {LectureNote} from "../../../models/entity/LectureNote";

declare var $: any;

@Component({
  selector: 'app-live-lesson',
  templateUrl: './live-lesson.component.html',
  styleUrls: ['./live-lesson.component.scss']
})
export class LiveLessonComponent implements OnInit {

  liveLessons: LiveLesson[] = [];


  constructor(private liveLessonService: LiveLessonService, private modalService: ModalService,
              private lectureNoteService: LectureNoteService) {
  }

  async ngOnInit() {
    this.liveLessons = await this.liveLessonService.getAll();
    setTimeout(() => {
      this.initJQuery();
    });
  }

  initJQuery() {
    $("#tbl_live_lesson").DataTable({
      "responsive": true, "lengthChange": false, "autoWidth": false,
      "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
    }).buttons().container().appendTo('#tbl_live_lesson_wrapper .col-md-6:eq(0)');
  }

  newLiveLesson() {
    const modal = this.modalService.open(LiveLessonFormModalComponent, {
      props: {
        method: 'CREATE'
      },
      centered: true,
      fullscreen: true,
      scrollable: true,
      backdropDismiss: false
    });

    modal.afterClose = (data) => {
      if (data.choose)
        this.liveLessonService.create(data.data)
          .then(async (value: any) => {
            await this.lectureNoteService.create(new LectureNote(value));
            this.liveLessons = await this.liveLessonService.getAll();
          });
    }
  }

  updateLiveLesson(liveLesson: LiveLesson) {
    const modal = this.modalService.open(LiveLessonFormModalComponent, {
      props: {
        method: 'UPDATE',
        liveLesson: liveLesson
      },
      centered: true,
      fullscreen: true,
      scrollable: true,
      backdropDismiss: false
    });

    modal.afterClose = (data) => {
      if (data.choose)
        this.liveLessonService.update(data.data, data.data.liveLessonId as number)
          .then(async (value: any) => {
            this.liveLessons = await this.liveLessonService.getAll();
          });
    }
  }


  deleteSwAlert(liveLesson: LiveLesson) {
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
        await this.deleteLiveLesson(liveLesson)
          .then(async (value) => {
            await sweetAlert.fire(
              'Deleted!',
              'Has been deleted.',
              'success'
            )
            this.liveLessons = await this.liveLessonService.getAll();
          })
          .catch(reason =>
            sweetAlert.fire(
              'Not Deleted!',
              'Has not been deleted.',
              'error'
            ));
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        await sweetAlert.fire(
          'Cancelled',
          'Successfully cancelled.',
          'error'
        )
      }
    });
  }

  async deleteLiveLesson(liveLesson: LiveLesson) {
    await this.liveLessonService.delete(liveLesson.liveLessonId as number);
  }

}
