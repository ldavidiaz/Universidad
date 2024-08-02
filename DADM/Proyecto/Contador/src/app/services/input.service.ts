import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InputService {
    private inputValueSubject= new BehaviorSubject<string>('');
    inputValue$ = this.inputValueSubject.asObservable();
    getInputValue() {
      /* console.log("In input.service: getInputValue() called") */
      return this.inputValueSubject.asObservable();
    }
  
    setInputValue(value: any) {
      this.inputValueSubject.next(value);
     /*  console.log("In input.service: setInputValue() called") */
    }
  
    resetInputValue() {
      this.inputValueSubject.next('');
      /* console.log("In input.service: resetInputValue() called") */
    }
}