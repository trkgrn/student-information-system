import {ComponentFixture, TestBed} from '@angular/core/testing';

import {LiveLessonFormModalComponent} from './live-lesson-form-modal.component';

describe('LiveLessonFormModalComponent', () => {
  let component: LiveLessonFormModalComponent;
  let fixture: ComponentFixture<LiveLessonFormModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LiveLessonFormModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LiveLessonFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
