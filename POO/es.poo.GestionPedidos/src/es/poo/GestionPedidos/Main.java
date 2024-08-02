package es.poo.GestionPedidos;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestionPedidos app = new GestionPedidos();
		
		app.InicarApp();
		
		mensaje("Fin del programa...");
		sc.close();
	}
	/**
	 * Esta función sirve para escribir por pantalla una cadena de texto de tipo String
	 * "msj" es la cadena de texto que se pasa como parametro.
	 * @param msj
	 */
	public static void mensaje(String msj) {
		System.out.println(msj);
	}

}
