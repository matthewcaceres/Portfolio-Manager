import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvestAccountComponent } from './invest-account.component';

describe('InvestAccountComponent', () => {
  let component: InvestAccountComponent;
  let fixture: ComponentFixture<InvestAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InvestAccountComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InvestAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
