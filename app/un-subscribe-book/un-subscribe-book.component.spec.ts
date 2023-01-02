import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnSubscribeBookComponent } from './un-subscribe-book.component';

describe('UnSubscribeBookComponent', () => {
  let component: UnSubscribeBookComponent;
  let fixture: ComponentFixture<UnSubscribeBookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnSubscribeBookComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnSubscribeBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
