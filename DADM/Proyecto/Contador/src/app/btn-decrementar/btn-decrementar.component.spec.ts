import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BtnDecrementarComponent } from './btn-decrementar.component';

describe('BtnDecrementarComponent', () => {
  let component: BtnDecrementarComponent;
  let fixture: ComponentFixture<BtnDecrementarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BtnDecrementarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BtnDecrementarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});