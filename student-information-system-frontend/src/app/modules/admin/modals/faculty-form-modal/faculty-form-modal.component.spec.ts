import {ComponentFixture, TestBed} from '@angular/core/testing';

import {FacultyFormModalComponent} from './faculty-form-modal.component';

describe('FacultyFormModalComponent', () => {
  let component: FacultyFormModalComponent;
  let fixture: ComponentFixture<FacultyFormModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FacultyFormModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacultyFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
