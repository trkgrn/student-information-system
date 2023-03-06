import {TestBed} from '@angular/core/testing';

import {LiveLessonService} from './live-lesson.service';

describe('LiveLessonService', () => {
  let service: LiveLessonService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LiveLessonService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
