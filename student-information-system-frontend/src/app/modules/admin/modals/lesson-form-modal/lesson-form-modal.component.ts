import {Component, Input, OnInit} from '@angular/core';
import {Faculty} from "../../../../models/entity/Faculty";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ModalService} from "../../../modal/modal.service";
import {OnModalInit} from "../../../modal/abstracts/on-modal-init";
import {Lesson} from "../../../../models/entity/Lesson";

@Component({
  selector: 'app-lesson-form-modal',
  templateUrl: './lesson-form-modal.component.html',
  styleUrls: ['./lesson-form-modal.component.scss']
})
export class LessonFormModalComponent implements OnModalInit {

  @Input() method!: string;
  @Input() lesson!: Lesson;

  form: FormGroup;

  constructor(private formBuilder: FormBuilder, private modalService: ModalService) {

    this.form = formBuilder.group({
      name: [null, Validators.required],
      description: [null, [Validators.required,Validators.maxLength(250)]],
      code: [null, [Validators.required,Validators.maxLength(6)]]
    });

  }

  onModalInit(): void {
    console.log('Modal initialized');
    if (this.lesson) {
      this.form.patchValue(this.lesson);
    }
  }

  closeModal(choose: boolean) {
    this.modalService.close({
      choose: choose,
      method: this.method,
      data: this.lesson
    });
  }

  save() {
    if (this.lesson) {
      this.lesson.name = this.form.get('name')?.value;
      this.lesson.description = this.form.get('description')?.value;
      this.lesson.code = this.form.get('code')?.value;
    }
    else
      this.lesson = this.form.value;
    this.closeModal(true);
  }

  cancel() {
    this.closeModal(false);
  }

}
