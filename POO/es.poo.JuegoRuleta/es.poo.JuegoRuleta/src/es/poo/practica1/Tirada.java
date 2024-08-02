package es.poo.practica1;

import java.util.Random;
///ESTA CLASE ITS DONE///
public class Tirada {

	//TODO Metodo tirar->devuelve puntuaci�n por la que se va a jugar
	private static int[] valorTirada= {10,20,30,40,0};
	
	static Random rnd = new Random();

	/**
	 * Método para obtener un valor de forma aleatorio entre estos valor {10,20,30,40,0}
	 * @return {@code int}
	 */
	public static int Tirar() {
		int valor= valorTirada[(int) (rnd.nextDouble()*4+0)];
		return  valor;
	}
	
}
