import { TestBed } from '@angular/core/testing';

import { LetterGradeService } from './letter-grade.service';

describe('LetterGradeService', () => {
  let service: LetterGradeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LetterGradeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
