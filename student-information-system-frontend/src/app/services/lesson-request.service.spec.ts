import { TestBed } from '@angular/core/testing';

import { LessonRequestService } from './lesson-request.service';

describe('LessonRequestService', () => {
  let service: LessonRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LessonRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
