package es.poo.controller;

import java.io.File;
import java.io.FileNotFoundException;

import es.poo.database.DBAgenda;
import es.poo.model.Contacto;
import es.poo.persistencia.PersistenciaContacto;
import es.poo.persistencia.PersistenciaEvento;

@SuppressWarnings("unused")
public class Main {
	private static final String RUTA_DB_CONTACTOS = "db_contactos.txt";
	
	private static final String RUTA_DB_EVENTOS = "db_eventos.txt";
    
	public static void main(String[] args) throws FileNotFoundException  {
		
		File f = new File(RUTA_DB_CONTACTOS);
		if(f.exists()) { 
			PersistenciaContacto des = new PersistenciaContacto();
			des.loadFromJson(RUTA_DB_CONTACTOS);
		}
		File f2 = new File(RUTA_DB_EVENTOS);
		if(f2.exists()) { 
			PersistenciaEvento des = new PersistenciaEvento();
			des.loadFromJson(RUTA_DB_EVENTOS);
		}
		
		
        VentanasController controller = new VentanasController();
        controller.mostrarVentanaPrincipal();
        
    }

}