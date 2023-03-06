import {ComponentFixture, TestBed} from '@angular/core/testing';

import {UserUpdateFormModalComponent} from './user-update-form-modal.component';

describe('UserUpdateFormModalComponent', () => {
  let component: UserUpdateFormModalComponent;
  let fixture: ComponentFixture<UserUpdateFormModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserUpdateFormModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserUpdateFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
