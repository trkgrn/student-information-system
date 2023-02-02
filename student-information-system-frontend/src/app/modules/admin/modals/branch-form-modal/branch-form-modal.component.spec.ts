import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchFormModalComponent } from './branch-form-modal.component';

describe('BranchFormModalComponent', () => {
  let component: BranchFormModalComponent;
  let fixture: ComponentFixture<BranchFormModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BranchFormModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BranchFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
