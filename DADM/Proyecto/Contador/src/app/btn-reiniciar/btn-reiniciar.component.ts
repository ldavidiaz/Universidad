import { Component, OnInit } from '@angular/core';
import { CommunicationService } from '../services/communication.service';
import { InputService } from '../services/input.service';
import { CheckboxService } from '../services/checkbox.service';

@Component({
  selector: 'app-btn-reiniciar',
  standalone: true,
  templateUrl: './btn-reiniciar.component.html',
  styleUrls: ['./btn-reiniciar.component.css']
})
export class BtnReiniciarComponent {
  inputValue: any = 0;
  isChecked: boolean = false;

  constructor(
    private setContador: CommunicationService,
    private inputService: InputService,
    private chbService: CheckboxService
  ) {
    this.inputService.inputValue$.subscribe(inputValue => {
      this.inputValue = inputValue !== '' ? parseInt(inputValue, 10) : 0;
    });

    this.chbService.isChecked$.subscribe(isChecked => {
      this.isChecked = isChecked;
      this.updateCheckbox(isChecked);
    });
  }


  async onReset() {
    if (this.inputValue >= 0 || this.isChecked) {
      await this.updateCounter(this.inputValue);
    } else if (!this.isChecked && this.inputValue < 0) {
      alert('No se puede reiniciar el contador con un valor negativo');
    }
   /*  console.log("In btn-reiniciar: onReset() called"); */
  }

  async updateCounter(value: any): Promise<void> {
    return new Promise(resolve => {
      //this.setContador.contador = value;
      this.setContador.setContador(value);
      /* console.log("In btn-reiniciar: updateCounter() called"); */
      resolve();
    });
    
  }

  updateCheckbox(checked: boolean) {
    this.isChecked = checked;
  }
}
