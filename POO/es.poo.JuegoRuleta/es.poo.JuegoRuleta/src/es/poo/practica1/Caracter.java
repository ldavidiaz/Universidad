package es.poo.practica1;

public class Caracter {
	private static final String CONSONANTE ="[bcdfghjklmnñpqrstvwxyzBCDFGHJKLMNÑPQRSTVXWYZ]";
	private static final String VOCAL ="[aeiouAEIOU]";
	
	/**
	 * Comprueba que el parámetro que se pasa es una consonante
	 * @param consonante
	 * @return verdadero si es consonante, falso en caso contrario
	 */
	public static boolean comprobarC(String consonante) {
		
		return consonante!=null && consonante.matches(CONSONANTE);
	}

	/**
	 * Comprueba que el parámetro que se pasa es vocal
	 * @param vocal
	 * @return verdadero si es vocal, falso en caso contrario
	 */
	public static boolean comprobarV(String vocal) {
		
		return vocal!=null && vocal.matches(VOCAL);
	}	
}
