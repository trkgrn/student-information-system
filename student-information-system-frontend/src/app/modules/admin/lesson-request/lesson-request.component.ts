import {Component, OnInit} from '@angular/core';
import {LessonRequest} from "../../../models/entity/LessonRequest";
import {LessonRequestService} from "../../../services/lesson-request.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-lesson-request',
  templateUrl: './lesson-request.component.html',
  styleUrls: ['./lesson-request.component.scss']
})
export class LessonRequestComponent implements OnInit {

  requests: LessonRequest[] = [];
  constructor(private lessonRequestService:LessonRequestService) { }

  async ngOnInit() {
    this.requests = await this.lessonRequestService.getLessonRequestsByWaiting();
  }

  approve(req: LessonRequest) {
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
      confirmButtonText: 'Yes, approve it!',
      cancelButtonText: 'No, cancel!',
      reverseButtons: true
    }).then(async (result) => {
      if (result.value) {
        await this.lessonRequestService.approve(req.lessonRequestId as number)
          .then(async (value:any) => {
            await sweetAlert.fire(
              'Approved!',
              'Has been approved.',
              'success'
            )
            this.requests = await this.lessonRequestService.getLessonRequestsByWaiting();
          })
          .catch((reason:any) =>
            sweetAlert.fire(
              'Not Approved!',
              'Has not been approved.',
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

  reject(req: LessonRequest) {
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
      confirmButtonText: 'Yes, not approve it!',
      cancelButtonText: 'No, cancel!',
      reverseButtons: true
    }).then(async (result) => {
      if (result.value) {
        await this.lessonRequestService.reject(req.lessonRequestId as number)
          .then(async (value:any) => {
            await sweetAlert.fire(
              'Not Approved!',
              'Succesfully not approved.',
              'success'
            )
            this.requests = await this.lessonRequestService.getLessonRequestsByWaiting();
          })
          .catch((reason:any) =>
            sweetAlert.fire(
              'Disapproval Failed!',
              'Has not been approved.',
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

}
