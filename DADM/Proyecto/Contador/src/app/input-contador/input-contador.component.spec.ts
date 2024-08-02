import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InputContadorComponent } from './input-contador.component';

describe('InputContadorComponent', () => {
  let component: InputContadorComponent;
  let fixture: ComponentFixture<InputContadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InputContadorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InputContadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});