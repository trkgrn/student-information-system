import { TestBed } from '@angular/core/testing';

import { EducationSeasonService } from './education-season.service';

describe('EducationSeasonService', () => {
  let service: EducationSeasonService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EducationSeasonService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
