import {TestBed} from '@angular/core/testing';

import {WeeklyNotesService} from './weekly-notes.service';

describe('WeeklyNotesService', () => {
  let service: WeeklyNotesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WeeklyNotesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
