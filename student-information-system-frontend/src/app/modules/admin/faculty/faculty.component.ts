import {Component, OnInit} from '@angular/core';
import {FacultyService} from "../../../services/faculty.service";
import {Faculty} from "../../../models/entity/Faculty";
import {ModalService} from "../../modal/modal.service";
import {FacultyFormModalComponent} from "../modals/faculty-form-modal/faculty-form-modal.component";
import Swal from "sweetalert2";

declare var $: any

@Component({
  selector: 'app-faculty',
  templateUrl: './faculty.component.html',
  styleUrls: ['./faculty.component.scss']
})
export class FacultyComponent implements OnInit {

  faculties: Faculty[] = [];


  constructor(private facultyService: FacultyService, private modalService: ModalService) {
  }

  async ngOnInit() {
    this.faculties = await this.facultyService.getAll();
    setTimeout(() => {
      this.initJQuery();
    });
  }

  initJQuery() {
    $("#tbl_faculty").DataTable({
      "responsive": true, "lengthChange": false, "autoWidth": false,
      "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
    }).buttons().container().appendTo('#tbl_faculty_wrapper .col-md-6:eq(0)');
  }

  newFaculty() {
    const modal = this.modalService.open(FacultyFormModalComponent, {
      props: {
        method: 'CREATE'
      },
      centered: true,
      fullscreen: true,
      scrollable: true,
      backdropDismiss: false
    });

    modal.afterClose = (data) => {
      this.facultyService.create(data.data)
        .then(async (value: any) => {
          this.faculties = await this.facultyService.getAll();
        });
    }
  }

  updateFaculty(faculty: Faculty) {
    const modal = this.modalService.open(FacultyFormModalComponent, {
      props: {
        method: 'UPDATE',
        faculty: faculty
      },
      centered: true,
      fullscreen: true,
      scrollable: true,
      backdropDismiss: false
    });

    modal.afterClose = (data) => {
      this.facultyService.update(data.data, data.data.facultyId)
        .then(async (value: any) => {
          this.faculties = await this.facultyService.getAll();
        });
    }
  }


  deleteSwAlert(faculty: Faculty) {
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
        await this.deleteFaculty(faculty)
          .then(async (value) => {
            await sweetAlert.fire(
              'Deleted!',
              'Has been deleted.',
              'success'
            )
            this.faculties = await this.facultyService.getAll();
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

  async deleteFaculty(faculty: Faculty) {
    await this.facultyService.delete(faculty.facultyId as number);
  }
}
