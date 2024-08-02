package es.poo.practica1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Frase {
	private static ArrayList<String> arrayFrases = new ArrayList<String>();
	protected static ArrayList<String> frasesJugadas = new ArrayList<String>();
	private static Random rnd = new Random();
	private	static String nombreFichero=null;
	static int index;
	static int tamArray;
	
	/**
	 * Añade un string a un array(frases) y limpia los espacios 
	 * que hayan al principio y al final.
	 * @param frases
	 */
	public static void addArrayFrases(String frases) {
		arrayFrases.add(frases.trim());
	}
	
	/**
	 * Añade un string a un array(frases jugadas) y limpia los espacios 
	 * que hayan al principio y al final
	 * @param frases
	 */
	public static void addFrasesJugadas(String frases) {
		frasesJugadas.add(frases.trim());
	}
	
	/**
	 * Método que devuelve un string de forma aleatoria a partir del modo de juego
	 * @param modo
	 * @return el {@code String} de una lista de frases
	 */
	public static String dameFrase(RuletaFortuna.ModoJuego modo)
	{
		
		if(modo.equals(RuletaFortuna.ModoJuego.FACIL)) 
				nombreFichero="frasesFacil.txt";
		else if(modo.equals(RuletaFortuna.ModoJuego.MEDIO)) 
				nombreFichero="frasesMedio.txt";
		else 
				nombreFichero="frasesExperto.txt";
				
		BufferedReader br = null;
			
		try {
		    // Crear un objeto BufferedReader al que se le pasa 
		    //   un objeto FileReader con el nombre del fichero
		    br = new BufferedReader(new FileReader(nombreFichero));
		    // Leer la primera l�nea, guardando en un String
		    String texto = br.readLine();
		    addArrayFrases(texto);
		    // Repetir mientras no se llegue al final del fichero
		    while(!(texto == null)) {
		        // Hacer lo que sea con la l�nea le�da
		        // En este ejemplo s�lo se muestra por consola
		        // Leer la siguiente l�nea
		        texto = br.readLine();
		        if(texto!=null)
		        	addArrayFrases(texto);
		      //System.out.println(texto);
		    }
		}
		// Captura de excepci�n por fichero no encontrado
		catch (FileNotFoundException ex) {
		    System.out.println("Error: Fichero no encontrado");
		    ex.printStackTrace();
		}
		// Captura de cualquier otra excepci�n
		catch(Exception ex) {
		    System.out.println("Error de lectura del fichero");
		    ex.printStackTrace();
		}
		// Asegurar el cierre del fichero en cualquier caso
		finally {
		    try {
		        // Cerrar el fichero si se ha podido abrir
		        if(br != null) {
		            br.close();
		        }
		    }
		    catch (Exception ex) {
		        System.out.println("Error al cerrar el fichero");
		        ex.printStackTrace();
		    }
		}
	tamArray = arrayFrases.size();	
	index = (int) (rnd.nextDouble()*3+0);
	return arrayFrases.get(index);
	}
	/*
	public void limpiarMemoria() {
		arrayFrases.clear();
	}*/
}