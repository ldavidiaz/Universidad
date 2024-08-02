import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChkbPermitirNegativosComponent } from './chkb-permitir-negativos.component';

describe('ChkbPermitirNegativosComponent', () => {
  let component: ChkbPermitirNegativosComponent;
  let fixture: ComponentFixture<ChkbPermitirNegativosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChkbPermitirNegativosComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ChkbPermitirNegativosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});