import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BtnReiniciarComponent } from './btn-reiniciar.component';

describe('BtnReiniciarComponent', () => {
  let component: BtnReiniciarComponent;
  let fixture: ComponentFixture<BtnReiniciarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BtnReiniciarComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BtnReiniciarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});