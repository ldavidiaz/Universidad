import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BtnIncrementarComponent } from './btn-incrementar.component';

describe('BtnIncrementarComponent', () => {
  let component: BtnIncrementarComponent;
  let fixture: ComponentFixture<BtnIncrementarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BtnIncrementarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BtnIncrementarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});