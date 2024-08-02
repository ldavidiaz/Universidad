import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { CommunicationService } from '../services/communication.service';

@Component({
  selector: 'app-contador',
  standalone: true,
  templateUrl: './contador.component.html',
  styleUrls: ['./contador.component.css']
})
export class ContadorComponent implements OnInit, OnDestroy {
  contador: number | undefined;
  private subscription: Subscription | undefined;

  constructor(public communicationService: CommunicationService) {}

  ngOnInit() {
    // Suscribirse al observable contador$
    this.subscription = this.communicationService.contador$.subscribe(
      value => {
        this.contador = value;
        console.log('Valor del contador:', this.contador);
      }
    );
  }

  ngOnDestroy() {
    // Desuscribirse para evitar fugas de memoria
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
