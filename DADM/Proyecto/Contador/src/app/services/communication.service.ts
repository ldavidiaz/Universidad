import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

//contador
export class CommunicationService {
  private contadorSubject = new BehaviorSubject<number>(0);
  contador$ = this.contadorSubject.asObservable();

  getContador() {
    return this.contador$;
  }

  setContador(value: number) {
    this.contadorSubject.next(value);
  }
}
