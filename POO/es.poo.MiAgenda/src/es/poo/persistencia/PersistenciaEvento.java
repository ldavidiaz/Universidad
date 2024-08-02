package es.poo.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import es.poo.database.DBAgenda;
import es.poo.model.Cita;
import es.poo.model.Evento;
import es.poo.model.Reunion;

public class PersistenciaEvento {
	private static ArrayList <Evento> listEvento = DBAgenda.getListaEventos();
	public static ArrayList<Evento> getListEvento() {
		return listEvento;
	}


	/***
	 * 
	 * Este método se encarga de persistir los eventos en formato JSON en un archivo de texto.
	 * Utiliza la biblioteca Gson para convertir la lista de eventos en una cadena JSON.
	 * Luego escribe la cadena JSON en el archivo especificado por la ruta de archivo.
	 * @param rutaFichero La ruta del archivo donde se guardarán los eventos en formato JSON.
	 *
	 * @throws FileNotFoundException
	 */
    public void persistToJson(String rutaFichero) {

    	RuntimeTypeAdapterFactory<Evento> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Evento.class,"tipo")
                .registerSubtype(Evento.class,"evento")
			    .registerSubtype(Cita.class,"cita")
			    .registerSubtype(Reunion.class,"reunion");
    	
    	RuntimeTypeAdapterFactory<Cita> runtimeTypeAdapterFactory2 = RuntimeTypeAdapterFactory
    	        .of(Cita.class,"tipo")
    	        .registerSubtype(Cita.class,"cita");
    	        
    	RuntimeTypeAdapterFactory<Reunion> runtimeTypeAdapterFactory3 = RuntimeTypeAdapterFactory
    	        .of(Reunion.class,"tipo")
    	        .registerSubtype(Reunion.class, "reunion");
    	
    	Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).registerTypeAdapterFactory(runtimeTypeAdapterFactory2).registerTypeAdapterFactory(runtimeTypeAdapterFactory3).create();

        String eventoSerializados = gson.toJson(listEvento);

        try{
        
        	// Intentamos abrir el fichero de texto
            FileOutputStream fop;
            File file = new File(rutaFichero);
			fop = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fop);
            
            osw.write(eventoSerializados);//Escribimos la información
            
            osw.close();//Y cerramos el fichero de texto

        }
        catch (IOException e){//Si hay un error, escribimos el mensaje de error en el Log
            e.printStackTrace();
        }
        
    }

	/**
	 * Este método se encarga de cargar los eventos desde un archivo JSON en formato de texto.
	 * Lee el archivo de texto especificado por la ruta de archivo y obtiene la cadena JSON.
	 * Luego utiliza la biblioteca Gson para convertir la cadena JSON en una lista de eventos.
	 * Finalmente, establece la lista de eventos en la clase DBAgenda.
	 * @param rutaFichero La ruta del archivo JSON desde donde se cargarán los eventos.
	 */
    public void loadFromJson(String rutaFichero) {//Obtener animal del fichero de texto
        String cadena = "";

        try {// Componemos una cadena de texto a partir de varias lineas de archivo, donde estan guardados los datos de los usuarios
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
            }
        } catch (FileNotFoundException e) {//Si hay algún error, lo mostramos en el Log
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (cadena.isEmpty()) {
            //return null;
        } else {
        	
        	RuntimeTypeAdapterFactory<Evento> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                    .of(Evento.class,"tipo")
                    .registerSubtype(Evento.class,"evento")
    			    .registerSubtype(Cita.class,"cita")
    			    .registerSubtype(Reunion.class,"reunion");
        	
        	RuntimeTypeAdapterFactory<Cita> runtimeTypeAdapterFactory2 = RuntimeTypeAdapterFactory
        	        .of(Cita.class,"tipo")
        	        .registerSubtype(Cita.class,"cita");
        	RuntimeTypeAdapterFactory<Reunion> runtimeTypeAdapterFactory3 = RuntimeTypeAdapterFactory
        	        .of(Reunion.class,"tipo")
        	        .registerSubtype(Reunion.class, "reunion");
			Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).registerTypeAdapterFactory(runtimeTypeAdapterFactory2).registerTypeAdapterFactory(runtimeTypeAdapterFactory3).create();
			    			
    		Type listType = new TypeToken<ArrayList<Evento>>(){}.getType();
    		listEvento = gson.fromJson(cadena.split("\\n")[0], listType);        	
        	DBAgenda.setListaEventos(listEvento);
        }
    }

}
