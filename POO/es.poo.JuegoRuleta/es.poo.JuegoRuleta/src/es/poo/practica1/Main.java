package es.poo.practica1;

import java.io.IOException;
import java.util.Scanner;


public class Main {
	protected static Scanner scanIn = new Scanner(System.in);
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		RuletaFortuna juego = new RuletaFortuna();
		boolean juegoNuevo=true;

		//LA PERSISTENCIA DE PARTIDAS NO FUNCIONA COMO DEBERÍA
		do {

		//juego.introducirDatosPartida();
		juego.Jugar();
		juegoNuevo = juego.finJuego();
		}while(juegoNuevo);

	scanIn.close();
	}
	public static void mensaje(String cadena) {
		System.out.println(cadena);
	}

}
