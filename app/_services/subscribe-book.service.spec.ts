import { TestBed } from '@angular/core/testing';

import { SubscribeBookService } from './subscribe-book.service';

describe('SubscribeBookService', () => {
  let service: SubscribeBookService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SubscribeBookService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
