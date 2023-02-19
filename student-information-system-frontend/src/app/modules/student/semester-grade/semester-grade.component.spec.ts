import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SemesterGradeComponent } from './semester-grade.component';

describe('SemesterGradeComponent', () => {
  let component: SemesterGradeComponent;
  let fixture: ComponentFixture<SemesterGradeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SemesterGradeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SemesterGradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
