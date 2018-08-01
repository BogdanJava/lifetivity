import { TestBed, inject } from '@angular/core/testing';

import { MonthlyService } from './monthly.service';

describe('MonthlyService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MonthlyService]
    });
  });

  it('should be created', inject([MonthlyService], (service: MonthlyService) => {
    expect(service).toBeTruthy();
  }));
});
