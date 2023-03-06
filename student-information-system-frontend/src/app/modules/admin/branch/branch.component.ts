import {Component, OnInit} from '@angular/core';
import {ModalService} from "../../modal/modal.service";
import Swal from "sweetalert2";
import {Branch} from "../../../models/entity/Branch";
import {BranchService} from "../../../services/branch.service";
import {BranchFormModalComponent} from "../modals/branch-form-modal/branch-form-modal.component";

declare var $: any
@Component({
  selector: 'app-branch',
  templateUrl: './branch.component.html',
  styleUrls: ['./branch.component.scss']
})
export class BranchComponent implements OnInit {

  branches: Branch[] = [];


  constructor(private branchService: BranchService, private modalService: ModalService) {
  }

  async ngOnInit() {
    this.branches = await this.branchService.getAll();
    setTimeout(() => {
      this.initJQuery();
    });
  }

  initJQuery() {
    $("#tbl_branch").DataTable({
      "responsive": true, "lengthChange": false, "autoWidth": false,
      "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
    }).buttons().container().appendTo('#tbl_branch_wrapper .col-md-6:eq(0)');
  }

  newBranch() {
    const modal = this.modalService.open(BranchFormModalComponent, {
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
      this.branchService.create(data.data)
        .then(async (value: any) => {
          this.branches = await this.branchService.getAll();
        });
    }
  }

  updateBranch(branch: Branch) {
    const modal = this.modalService.open(BranchFormModalComponent, {
      props: {
        method: 'UPDATE',
        branch: branch
      },
      centered: true,
      fullscreen: true,
      scrollable: true,
      backdropDismiss: false
    });

    modal.afterClose = (data) => {
      if (data.choose)
      this.branchService.update(data.data, data.data.branchId)
        .then(async (value: any) => {
          this.branches = await this.branchService.getAll();
        });
    }
  }


  deleteSwAlert(branch: Branch) {
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
        await this.deleteBranch(branch)
          .then(async (value) => {
            await sweetAlert.fire(
              'Deleted!',
              'Has been deleted.',
              'success'
            )
            this.branches = await this.branchService.getAll();
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

  async deleteBranch(branch: Branch) {
    await this.branchService.delete(branch.branchId as number);
  }

}
