import {Component, Input} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {OnModalInit} from "../../../modal/abstracts/on-modal-init";
import {ModalService} from "../../../modal/modal.service";
import {Faculty} from "../../../../models/entity/Faculty";

@Component({
  selector: 'app-faculty-form-modal',
  templateUrl: './faculty-form-modal.component.html',
  styleUrls: ['./faculty-form-modal.component.scss']
})
export class FacultyFormModalComponent implements OnModalInit {

  @Input() method!: string;
  @Input() faculty!: Faculty;

  form: FormGroup;

  constructor(private formBuilder: FormBuilder, private modalService: ModalService) {

    this.form = formBuilder.group({
      name: [null, Validators.required]
    });

  }

  onModalInit(): void {
    console.log('Faculty form Modal initialized');
    if (this.faculty) {
      this.form.patchValue(this.faculty);
    }
  }

  closeModal(choose: boolean) {
    this.modalService.close({
      choose: choose,
      method: this.method,
      data: this.faculty
    });
  }

  save() {
    if (this.faculty) {
      this.faculty.name = this.form.get('name')?.value;
    }
    else
      this.faculty = this.form.value;
    this.closeModal(true);
  }

  cancel() {
    this.closeModal(false);
  }
}
