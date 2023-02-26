import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotesByLiveLessonComponent } from './notes-by-live-lesson.component';

describe('NotesByLiveLessonComponent', () => {
  let component: NotesByLiveLessonComponent;
  let fixture: ComponentFixture<NotesByLiveLessonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NotesByLiveLessonComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NotesByLiveLessonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
