import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscribeBookComponent } from './subscribe-book.component';

describe('SubscribeBookComponent', () => {
  let component: SubscribeBookComponent;
  let fixture: ComponentFixture<SubscribeBookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubscribeBookComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubscribeBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
