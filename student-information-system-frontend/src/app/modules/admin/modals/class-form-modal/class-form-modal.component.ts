import {Component, Input} from '@angular/core';
import {Branch} from "../../../../models/entity/Branch";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ModalService} from "../../../modal/modal.service";
import {OnModalInit} from "../../../modal/abstracts/on-modal-init";
import {Class} from "../../../../models/entity/Class";
import {BranchService} from "../../../../services/branch.service";

@Component({
  selector: 'app-class-form-modal',
  templateUrl: './class-form-modal.component.html',
  styleUrls: ['./class-form-modal.component.scss']
})
export class ClassFormModalComponent implements OnModalInit {

  @Input() method!: string;
  @Input() class!: Class;

  form: FormGroup;
  branches: Branch[] = [];

  constructor(private branchService: BranchService, private formBuilder: FormBuilder, private modalService: ModalService) {

    this.form = formBuilder.group({
      gradeYear: [null, Validators.required],
      branch: [new Branch(), Validators.required]
    });

  }

  async onModalInit() {
    console.log('Modal initialized');
    this.branches = await this.branchService.getAll();

    if (this.class) {
      this.form.patchValue(this.class);
    }
  }

  closeModal(choose: boolean) {
    this.modalService.close({
      choose: choose,
      method: this.method,
      data: this.class
    });
  }

  save() {
    if (this.class) {
      this.class.gradeYear = this.form.get('gradeYear')?.value;
      this.class.branch = this.form.get('branch')?.value;
    } else
      this.class = this.form.value;
    this.closeModal(true);
  }

  cancel() {
    this.closeModal(false);
    console.log(JSON.stringify(this.form.value))
  }

}
