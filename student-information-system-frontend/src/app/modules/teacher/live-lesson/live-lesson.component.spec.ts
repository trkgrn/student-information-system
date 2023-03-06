import {ComponentFixture, TestBed} from '@angular/core/testing';

import {LiveLessonComponent} from './live-lesson.component';

describe('LiveLessonComponent', () => {
  let component: LiveLessonComponent;
  let fixture: ComponentFixture<LiveLessonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LiveLessonComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LiveLessonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
