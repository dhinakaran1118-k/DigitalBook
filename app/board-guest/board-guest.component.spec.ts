import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoardGuestComponent } from './board-guest.component';

describe('BoardGuestComponent', () => {
  let component: BoardGuestComponent;
  let fixture: ComponentFixture<BoardGuestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoardGuestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoardGuestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
