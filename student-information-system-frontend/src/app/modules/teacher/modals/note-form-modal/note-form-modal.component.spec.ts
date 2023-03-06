import {ComponentFixture, TestBed} from '@angular/core/testing';

import {NoteFormModalComponent} from './note-form-modal.component';

describe('NoteFormModalComponent', () => {
  let component: NoteFormModalComponent;
  let fixture: ComponentFixture<NoteFormModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NoteFormModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NoteFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
