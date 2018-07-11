import { TestBed, inject } from '@angular/core/testing';

import { GlobalErrorHandler } from './error-handler.service';

describe('ErrorHandlerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GlobalErrorHandler]
    });
  });

  it('should be created', inject([GlobalErrorHandler], (service: GlobalErrorHandler) => {
    expect(service).toBeTruthy();
  }));
});
