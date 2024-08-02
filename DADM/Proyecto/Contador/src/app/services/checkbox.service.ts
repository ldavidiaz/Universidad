import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CheckboxService {
  private isCheckedSubject = new BehaviorSubject<boolean>(false);
  isChecked$ = this.isCheckedSubject.asObservable();

  setChecked(isChecked: boolean) {
    this.isCheckedSubject.next(isChecked);
    /* console.log("In checkbox.service: setChecked() called") */
  }
}