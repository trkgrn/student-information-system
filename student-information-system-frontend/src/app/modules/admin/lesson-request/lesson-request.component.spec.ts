import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LessonRequestComponent } from './lesson-request.component';

describe('LessonRequestComponent', () => {
  let component: LessonRequestComponent;
  let fixture: ComponentFixture<LessonRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LessonRequestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LessonRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
