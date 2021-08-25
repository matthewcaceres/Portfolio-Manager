import { TestBed } from '@angular/core/testing';

import { MarketMoversService } from './market-movers.service';

describe('MarketMoversService', () => {
  let service: MarketMoversService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MarketMoversService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
