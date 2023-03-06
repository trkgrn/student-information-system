import {Component, OnInit} from '@angular/core';
import {ModalService} from "../../modal/modal.service";
import Swal from "sweetalert2";
import {Lesson} from "../../../models/entity/Lesson";
import {LessonService} from "../../../services/lesson.service";
import {LessonFormModalComponent} from "../modals/lesson-form-modal/lesson-form-modal.component";

declare var $: any
@Component({
  selector: 'app-lesson',
  templateUrl: './lesson.component.html',
  styleUrls: ['./lesson.component.scss']
})
export class LessonComponent implements OnInit {

  lessons: Lesson[] = [];


  constructor(private lessonService: LessonService, private modalService: ModalService) {
  }

  async ngOnInit() {
    this.lessons = await this.lessonService.getAll();
    setTimeout(() => {
      this.initJQuery();
    });
  }

  initJQuery() {
    $("#tbl_lesson").DataTable({
      "responsive": true, "lengthChange": false, "autoWidth": false,
      "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
    }).buttons().container().appendTo('#tbl_lesson_wrapper .col-md-6:eq(0)');
  }

  newLesson() {
    const modal = this.modalService.open(LessonFormModalComponent, {
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
        this.lessonService.create(data.data)
          .then(async (value: any) => {
            this.lessons = await this.lessonService.getAll();
          });
    }
  }

  updateLesson(lesson: Lesson) {
    const modal = this.modalService.open(LessonFormModalComponent, {
      props: {
        method: 'UPDATE',
        lesson: lesson
      },
      centered: true,
      fullscreen: true,
      scrollable: true,
      backdropDismiss: false
    });

    modal.afterClose = (data) => {
      if (data.choose)
        this.lessonService.update(data.data, data.data.lessonId)
          .then(async (value: any) => {
            this.lessons = await this.lessonService.getAll();
          });
    }
  }


  deleteSwAlert(lesson: Lesson) {
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
        await this.deleteLesson(lesson)
          .then(async (value) => {
            await sweetAlert.fire(
              'Deleted!',
              'Has been deleted.',
              'success'
            )
            this.lessons = await this.lessonService.getAll();
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

  async deleteLesson(lesson: Lesson) {
    await this.lessonService.delete(lesson.lessonId as number);
  }
}
