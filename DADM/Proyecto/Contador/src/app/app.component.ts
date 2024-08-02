import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BtnIncrementarComponent } from './btn-incrementar/btn-incrementar.component';
import { BtnDecrementarComponent } from './btn-decrementar/btn-decrementar.component';
import { BtnReiniciarComponent } from './btn-reiniciar/btn-reiniciar.component';
import { ContadorComponent } from './contador/contador.component';
import { ChkbPermitirNegativosComponent } from './chkb-permitir-negativos/chkb-permitir-negativos.component';
import { InputContadorComponent } from './input-contador/input-contador.component';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    ContadorComponent,
    BtnIncrementarComponent,
    BtnDecrementarComponent,
    BtnReiniciarComponent,
    InputContadorComponent,
    ChkbPermitirNegativosComponent,
    RouterOutlet
          ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  host: {ngSkipHydration: 'true'},
})
export class AppComponent implements OnInit {

  title = 'Contador';
  ngOnInit() {  }
}