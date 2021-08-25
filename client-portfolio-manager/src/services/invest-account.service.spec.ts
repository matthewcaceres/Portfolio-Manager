import { TestBed } from '@angular/core/testing';

import { InvestAccountService } from './invest-account.service';

describe('InvestAccountService', () => {
  let service: InvestAccountService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InvestAccountService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
