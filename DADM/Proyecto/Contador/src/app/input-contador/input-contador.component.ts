import { Component, OnInit } from '@angular/core';
import { InputService } from '../services/input.service';
import { CommunicationService } from '../services/communication.service';
import { CheckboxService } from '../services/checkbox.service';

@Component({
  selector: 'app-input-contador',
  standalone: true,
  templateUrl: './input-contador.component.html',
  styleUrls: ['./input-contador.component.css']
})
export class InputContadorComponent {
  inputValue: any = '';
  isChecked: boolean = false;
  minimo = 0;

  constructor(
    private contador: CommunicationService,
    private inputService: InputService,
    private chbService: CheckboxService
  ) {
    this.contador.contador$.subscribe(contadorValue => {
      if (this.inputValue != '' && contadorValue.toString() == this.inputValue) {
        this.inputValue = '';
        this.inputService.setInputValue('');
      }
    });

    this.chbService.isChecked$.subscribe(isChecked => {
      this.isChecked = isChecked;
      //this.updateCheckbox(isChecked);
    });
  }

  onInputChange(event: Event) {
    const target = event.target as HTMLInputElement;
    let valorInput = parseInt(target.value, 10);

    if (this.isChecked || valorInput >= 0) {
      this.inputValue = valorInput;
      this.inputService.setInputValue(this.inputValue);
      this.minimo = -99999;
    } else if (!this.isChecked && valorInput <= 0) {
      this.inputValue = valorInput;
      this.inputService.setInputValue(this.inputValue);
      this.minimo = 0;
    }
  }

  updateCheckbox(checked: boolean) {
    this.isChecked = checked;
    /* console.log("In input-contador: updateCheckbox() called"); */
  }
}
