import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ClassFormModalComponent} from './class-form-modal.component';

describe('ClassFormModalComponent', () => {
  let component: ClassFormModalComponent;
  let fixture: ComponentFixture<ClassFormModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClassFormModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClassFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
