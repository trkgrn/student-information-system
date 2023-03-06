import {Component, Input} from '@angular/core';
import {OnModalInit} from "../../modal/abstracts/on-modal-init";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ModalService} from "../../modal/modal.service";
import {User} from "../../../models/entity/User";

@Component({
  selector: 'app-user-update-form-modal',
  templateUrl: './user-update-form-modal.component.html',
  styleUrls: ['./user-update-form-modal.component.scss']
})
export class UserUpdateFormModalComponent implements OnModalInit {

  @Input() method!: string;
  @Input() user!: User;

  form: FormGroup;

  constructor(private formBuilder: FormBuilder, private modalService: ModalService) {

    this.form = formBuilder.group({
      firstName: [null, Validators.required],
      lastName: [null, Validators.required],
      telNo: [null, [Validators.required, Validators.pattern("^[0-9]{10,10}$")]],
      address: [null, Validators.required],
      email: [null, [Validators.required, Validators.pattern("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")]],
    });

  }

  onModalInit(): void {
    console.log('Modal initialized');
    if (this.user) {
      this.form.patchValue(this.user);
    }
  }

  closeModal(choose: boolean) {
    this.modalService.close({
      choose: choose,
      method: this.method,
      data: this.user
    });
  }

  save() {
    if (this.user) {
      this.user.firstName = this.form.value.firstName;
      this.user.lastName = this.form.value.lastName;
      this.user.telNo = this.form.value.telNo;
      this.user.address = this.form.value.address;
      this.user.email = this.form.value.email;
    }
    else
      this.user = this.form.value;
    this.closeModal(true);
  }

  cancel() {
    this.closeModal(false);
  }
}
