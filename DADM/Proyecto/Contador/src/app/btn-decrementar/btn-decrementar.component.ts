import { Component } from '@angular/core';
import { CommunicationService } from '../services/communication.service';
import { CheckboxService } from '../services/checkbox.service';
import { take } from 'rxjs';

@Component({
  selector: 'app-btn-decrementar',
  standalone: true,
  templateUrl: './btn-decrementar.component.html',
  styleUrls: ['./btn-decrementar.component.css']
})
export class BtnDecrementarComponent{

  isChecked: boolean = false;

  constructor(private setContador: CommunicationService, private checkboxService: CheckboxService) {
    this.checkboxService.isChecked$.subscribe(isChecked => {
      this.isChecked = isChecked;
      this.updateCheckbox(isChecked);
    });
  }


  updateCheckbox(checked: boolean) {
    this.isChecked = checked;
  }
  decrementar(){
    this.setContador.contador$.pipe(take(1)).subscribe(
      currentCount => {
        if (this.isChecked || Number(currentCount) > 0){
          const newCount = Number(currentCount) - 1;
          this.setContador.setContador(newCount);
        }
      }
    ).unsubscribe();
  }

}
