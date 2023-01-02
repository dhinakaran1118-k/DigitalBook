import { TestBed } from '@angular/core/testing';

import { UnSubscribeBookService } from './un-subscribe-book.service';

describe('UnSubscribeBookService', () => {
  let service: UnSubscribeBookService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UnSubscribeBookService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
