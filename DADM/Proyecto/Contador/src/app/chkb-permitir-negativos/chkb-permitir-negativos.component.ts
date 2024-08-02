import { Component, OnInit } from '@angular/core';
import { CheckboxService } from '../services/checkbox.service';

@Component({
  selector: 'app-chkb-permitir-negativos',
  standalone: true,
  templateUrl: './chkb-permitir-negativos.component.html',
  styleUrls: ['./chkb-permitir-negativos.component.css']
})
export class ChkbPermitirNegativosComponent{
  isChecked: boolean = false;

  constructor(private checkboxService: CheckboxService) {
    this.checkboxService.isChecked$.subscribe(isChecked => {
      this.isChecked = isChecked;
    });
  }

  onChange(event: Event) {
    const target = event.target as HTMLInputElement;
    this.checkboxService.setChecked(target.checked);
    /* console.log("In chkb-permitir-negativos: onChange() called"); */
  }
}
