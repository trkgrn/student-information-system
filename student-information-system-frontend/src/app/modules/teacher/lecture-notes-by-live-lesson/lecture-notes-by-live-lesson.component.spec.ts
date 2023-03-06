import {ComponentFixture, TestBed} from '@angular/core/testing';

import {LectureNotesByLiveLessonComponent} from './lecture-notes-by-live-lesson.component';

describe('LectureNotesByLiveLessonComponent', () => {
  let component: LectureNotesByLiveLessonComponent;
  let fixture: ComponentFixture<LectureNotesByLiveLessonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LectureNotesByLiveLessonComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LectureNotesByLiveLessonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
