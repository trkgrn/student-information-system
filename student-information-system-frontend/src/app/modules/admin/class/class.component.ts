import {Component, OnInit} from '@angular/core';
import {ModalService} from "../../modal/modal.service";
import Swal from "sweetalert2";
import {Class} from "../../../models/entity/Class";
import {ClassService} from "../../../services/class.service";
import {ClassFormModalComponent} from "../modals/class-form-modal/class-form-modal.component";

declare var $: any
@Component({
  selector: 'app-class',
  templateUrl: './class.component.html',
  styleUrls: ['./class.component.scss']
})
export class ClassComponent implements OnInit {

  classes: Class[] = [];


  constructor(private classService: ClassService, private modalService: ModalService) {
  }

  async ngOnInit() {
    this.classes = await this.classService.getAll();
    setTimeout(() => {
      this.initJQuery();
    });
  }

  initJQuery() {
    $("#tbl_class").DataTable({
      "responsive": true, "lengthChange": false, "autoWidth": false,
      "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
    }).buttons().container().appendTo('#tbl_class_wrapper .col-md-6:eq(0)');
  }

  newClass() {
    const modal = this.modalService.open(ClassFormModalComponent, {
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
        this.classService.create(data.data)
          .then(async (value: any) => {
            this.classes = await this.classService.getAll();
          });
    }
  }

  updateClass(updClass:Class) {
    const modal = this.modalService.open(ClassFormModalComponent, {
      props: {
        method: 'UPDATE',
        class: updClass
      },
      centered: true,
      fullscreen: true,
      scrollable: true,
      backdropDismiss: false
    });

    modal.afterClose = (data) => {
      if (data.choose)
        this.classService.update(data.data, data.data.classId)
          .then(async (value: any) => {
            this.classes = await this.classService.getAll();
          });
    }
  }


  deleteSwAlert(delClass: Class) {
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
        await this.deleteClass(delClass)
          .then(async (value) => {
            await sweetAlert.fire(
              'Deleted!',
              'Has been deleted.',
              'success'
            )
            this.classes = await this.classService.getAll();
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

  async deleteClass(updClass:Class) {
    await this.classService.delete(updClass.classId as number);
  }

}
