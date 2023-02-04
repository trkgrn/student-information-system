import {Component, Input, OnInit} from '@angular/core';
import {ClassService} from "../../../../services/class.service";
import {TeacherService} from "../../../../services/teacher.service";
import {LessonService} from "../../../../services/lesson.service";
import {EducationSeasonService} from "../../../../services/education-season.service";
import {Class} from "../../../../models/entity/Class";
import {Teacher} from "../../../../models/entity/Teacher";
import {EducationSeason} from "../../../../models/entity/EducationSeason";
import {Lesson} from "../../../../models/entity/Lesson";
import {LiveLesson} from "../../../../models/entity/LiveLesson";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ModalService} from "../../../modal/modal.service";
import {Branch} from "../../../../models/entity/Branch";
import {OnModalInit} from "../../../modal/abstracts/on-modal-init";
import {Period} from "../../../../models/entity/Period";
import {PeriodService} from "../../../../services/period.service";

@Component({
  selector: 'app-live-lesson-form-modal',
  templateUrl: './live-lesson-form-modal.component.html',
  styleUrls: ['./live-lesson-form-modal.component.scss']
})
export class LiveLessonFormModalComponent implements OnModalInit {
  @Input() method!: string;
  @Input() liveLesson!: LiveLesson;

  sliderValue = 0;
  form: FormGroup;
  classes: Class[] = [];
  teachers: Teacher[] = [];
  educationSeasons: EducationSeason[] = [];
  lessons: Lesson[] = [];
  periods: Period[] = [];
  isActives: boolean[] = [true, false];

  constructor(private classService: ClassService, private teacherService: TeacherService, private lessonService: LessonService,
              private educationSeasonService: EducationSeasonService, private periodService: PeriodService,
              private formBuilder: FormBuilder, private modalService: ModalService) {
    this.form = formBuilder.group({
      _class: [new Class(), Validators.required],
      teacher: [new Teacher(), Validators.required],
      educationSeason: [new EducationSeason(), Validators.required],
      lesson: [new Lesson(), Validators.required],
      midtermPercent: [50, Validators.required],
      finalPercent: [50, Validators.required],
      isActive: [true, Validators.required],
      period: [new Period(), Validators.required]
    });
  }

  async onModalInit() {
    console.log('Modal initialized');
    this.classes = await this.classService.getAll();
    this.teachers = await this.teacherService.getAll();
    this.educationSeasons = await this.educationSeasonService.getAll();
    this.lessons = await this.lessonService.getAll();
    this.periods = await this.periodService.getAll();

    if (this.liveLesson) {
      const selectedClass = this.classes.find(c => c.classId === this.liveLesson._class?.classId);
      const selectedTeacher = this.teachers.find(t => t.teacherId === this.liveLesson.teacher?.teacherId);
      const selectedEducationSeason = this.educationSeasons.find(e => e.educationSeasonId === this.liveLesson.educationSeason?.educationSeasonId);
      const selectedLesson = this.lessons.find(l => l.lessonId === this.liveLesson.lesson?.lessonId);
      const selectedPeriod = this.periods.find(p => p.periodId === this.liveLesson.period?.periodId);
      const selectedIsActive = this.isActives.find(i => i === this.liveLesson.isActive);

      this.form.patchValue({
        ...this.liveLesson,
        _class: selectedClass,
        teacher: selectedTeacher,
        educationSeason: selectedEducationSeason,
        lesson: selectedLesson,
        period: selectedPeriod,
        isActive: selectedIsActive
      });
    }
  }

  rangeChange(event: any) {
    const value: number = Number(event.target.value);
    this.form.patchValue({
      midtermPercent: value,
      finalPercent: 100- value
    });
    console.log(this.form.value)
  }

  closeModal(choose: boolean) {
    this.modalService.close({
      choose: choose,
      method: this.method,
      data: this.liveLesson
    });
  }

  save() {
    if (this.liveLesson) {
      const id = this.liveLesson.liveLessonId;
      this.liveLesson = this.form.value;
      this.liveLesson.liveLessonId = id;
    } else
      this.liveLesson = this.form.value;
    this.closeModal(true);
  }

  cancel() {
    this.closeModal(false);
    console.log(JSON.stringify(this.form.value))
  }

}
