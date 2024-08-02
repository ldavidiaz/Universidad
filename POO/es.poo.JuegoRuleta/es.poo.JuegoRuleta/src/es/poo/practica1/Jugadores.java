package es.poo.practica1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.google.gson.Gson;



///ESTA CLASE ITS DONE///
public class Jugadores {
	protected  ArrayList<Jugador> bdJugadores;
	
	public Jugadores()
	{
		bdJugadores = new ArrayList<Jugador>();
	}
	/**
	 * Método que guarda una instancia de Jugador en un ArrayList
	 * 
	 * @param {@code Objeto} dato
	 */
	public  void addJugador(Jugador dato)
	{
		bdJugadores.add(dato);
	}
	/**
	 * Serailiza los datos de la clase en formato JSON
	 * @return {@code String} en formato JSON
	 */
	public String serialize(){
		Gson gson = new Gson();		
        return gson.toJson(this);//Serializamos el jugador
	}
	
	/***
	 * Método para guardar datos en formato JSON en un fichero de texto
	 * @param rutaFichero Especifica el nombre del fichero donde se guardan los datos
	 * @throws FileNotFoundException
	 */
    public void persistToJson(String rutaFichero) {//TODO ARREGLAR NO SOBREESCRIBIR LINEA
    	//Serializo el objeto a cadena
        String jugadorSerializado = this.serialize();
    	
        try{
        
        		//Intentamos abrir el fichero de texto
            FileOutputStream fop;
            File file = new File(rutaFichero);
			fop = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fop);
            
            osw.write(jugadorSerializado);//Escribimos la información
           
            osw.close();//Y cerramos el fichero de texto

        }
        catch (IOException e){//Si hay un error, escribimos el mensaje de error en el Log
            e.printStackTrace();
        }
        
    }
	
    /**
     * Devuelve los datos de la clase deserializada del fichero que pasamos por parámetro
     * @param rutaFichero Especifica el nombre del fichero donde se guardan los datos
     * @return {@code Class}
     */
	public static Jugadores loadFromJson(String rutaFichero) {
		  String cadena = "";

	        try {//Componemos una cadena de texto a partir de varias lineas de archivo, donde estan guardados los datos de los usuarios
	            FileInputStream fis;
	            File file = new File(rutaFichero);
	            fis = new FileInputStream(file);
	            if (fis != null) {
	                InputStreamReader isr = new InputStreamReader(fis);
	                BufferedReader br = new BufferedReader(isr);
	                String linea = "";
	                StringBuilder sb = new StringBuilder();
	                while ((linea = br.readLine()) != null) {
	                    sb.append(linea);//Vamos leyendo linea a linea del texto y los vamos concatenando en un StringBuilder
	                }
	                isr.close();
	                cadena = sb.toString();//El StringBuilder lo transformamos a string
	              //  System.out.println("datos:"+cadena);
	            }
	        } catch (FileNotFoundException e) {//Si hay algún error, lo mostramos en el Log
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        if (cadena.isEmpty()) {
	            return null;
	        } else {
	        	//Deserializar
	            Gson gson = new Gson();
	            return gson.fromJson(cadena.split("\\n")[0], Jugadores.class);
	        }
	    }
	
}
