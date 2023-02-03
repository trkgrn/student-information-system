import {Component, Input, OnInit} from '@angular/core';
import {Faculty} from "../../../../models/entity/Faculty";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ModalService} from "../../../modal/modal.service";
import {OnModalInit} from "../../../modal/abstracts/on-modal-init";
import {Branch} from "../../../../models/entity/Branch";
import {FacultyService} from "../../../../services/faculty.service";

@Component({
  selector: 'app-branch-form-modal',
  templateUrl: './branch-form-modal.component.html',
  styleUrls: ['./branch-form-modal.component.scss']
})
export class BranchFormModalComponent implements OnModalInit {

  @Input() method!: string;
  @Input() branch!: Branch;

  form: FormGroup;
  faculties: Faculty[] = [];

  constructor(private facultyService: FacultyService, private formBuilder: FormBuilder, private modalService: ModalService) {

    this.form = formBuilder.group({
      name: [null, Validators.required],
      faculty: [new Faculty(), Validators.required]
    });

  }

 async onModalInit() {
    console.log('Modal initialized');
    this.faculties = await this.facultyService.getAll();

    if (this.branch) {
      const selectedFaculty = this.faculties.find(f => f.name === this.branch.faculty?.name)
      this.form.patchValue({...this.branch, faculty: selectedFaculty})
    }
  }

  closeModal(choose: boolean) {
    this.modalService.close({
      choose: choose,
      method: this.method,
      data: this.branch
    });
  }

  save() {
    if (this.branch) {
      this.branch.name = this.form.get('name')?.value;
      this.branch.faculty = this.form.get('faculty')?.value;
    } else
      this.branch = this.form.value;
    this.closeModal(true);
  }

  cancel() {
    this.closeModal(false);
    console.log(JSON.stringify(this.form.value))
  }

}
