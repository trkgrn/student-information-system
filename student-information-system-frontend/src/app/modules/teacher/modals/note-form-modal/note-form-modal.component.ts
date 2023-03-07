import {Component, Input} from '@angular/core';
import {OnModalInit} from "../../../modal/abstracts/on-modal-init";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {FacultyService} from "../../../../services/faculty.service";
import {ModalService} from "../../../modal/modal.service";
import {Note} from "../../../../models/entity/Note";

@Component({
  selector: 'app-note-form-modal',
  templateUrl: './note-form-modal.component.html',
  styleUrls: ['./note-form-modal.component.scss']
})
export class NoteFormModalComponent implements OnModalInit {

  @Input() method!: string;
  @Input() note!: Note;

  form: FormGroup;

  constructor(private facultyService: FacultyService, private formBuilder: FormBuilder, private modalService: ModalService) {

    this.form = formBuilder.group({
      midtermExam: [null, [Validators.required, Validators.min(0), Validators.max(100)]],
      finalExam: [0, [Validators.min(0), Validators.max(100)]]
    });

  }

  async onModalInit() {
    console.log('Modal initialized');
    if (this.note) {
      this.form.patchValue(this.note)
    }
  }

  closeModal(choose: boolean) {
    this.modalService.close({
      choose: choose,
      method: this.method,
      data: this.note
    });
  }

  save() {
    if (this.note) {
      this.note.midtermExam = this.form.get('midtermExam')?.value;
      this.note.finalExam = this.form.get('finalExam')?.value
    } else
      this.note = this.form.value;
    this.closeModal(true);
  }

  cancel() {
    this.closeModal(false);
    console.log(JSON.stringify(this.form.value))
  }

}
