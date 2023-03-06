import {TestBed} from '@angular/core/testing';

import {LectureNoteService} from './lecture-note.service';

describe('LectureNoteService', () => {
  let service: LectureNoteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LectureNoteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
