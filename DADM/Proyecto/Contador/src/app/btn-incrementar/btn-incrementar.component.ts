import { Component } from '@angular/core';
import { CommunicationService } from '../services/communication.service';
import { take } from 'rxjs/operators';

@Component({
  selector: 'app-btn-incrementar',
  standalone: true,
  templateUrl: './btn-incrementar.component.html',
  styleUrls: ['./btn-incrementar.component.css']
})
export class BtnIncrementarComponent {
  constructor(private contadorService: CommunicationService) {}

  incrementar(){
    // Obtener el valor actual del contador, incrementarlo y actualizar el contador
    this.contadorService.contador$.pipe(take(1)).subscribe(
      currentCount => {
        const newCount = currentCount + 1;
        this.contadorService.setContador(newCount);
      }
    ).unsubscribe(); // Desuscribirse inmediatamente ya que solo necesitamos el valor actual una vez
  }
}
