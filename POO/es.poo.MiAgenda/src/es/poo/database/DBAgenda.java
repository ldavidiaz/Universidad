package es.poo.database;

import java.util.ArrayList;
import java.util.Objects;

import es.poo.model.Contacto;
import es.poo.model.Evento;
import es.poo.persistencia.PersistenciaContacto;
import es.poo.persistencia.PersistenciaEvento;
public abstract class DBAgenda {
	
    private static final String CONTACTO_ISREP = "El contacto ya existe en la base de datos.";
    private static final String CONTACTO_SAVED = "El contacto ha sido agregado con éxito.";
    private static final String CONTACTO_DB ="db_contactos.txt";
    private static final String EVENTO_DB ="db_eventos.txt";
    
	private static ArrayList<Contacto> listaContactos = new ArrayList<> ();
	private static ArrayList<Evento> listaEventos = new ArrayList<> ();

	// ADD CONTACTO
	/**
	 * Este método se utiliza para agregar un contacto a la lista de contactos.
	 * Verifica si el contacto ya existe en la lista mediante el número de teléfono y móvil.
	 * Si el contacto ya existe, devuelve un mensaje indicando que el contacto ya está repetido.
	 * Si el contacto no existe, se agrega a la lista de contactos y se guarda en el almacenamiento.
	 * @param contacto El contacto que se va a agregar.
	 * @return Un mensaje indicando el resultado de la operación.
	 */
	public static String addUser(Contacto contacto) {

        if (contactoExiste(contacto.getTelefono(),contacto.getMovil()) != null ) {
            //System.out.println(CONTACTO_ISREP);
            return CONTACTO_ISREP;
        } else {
            listaContactos.add(contacto);
            saveContacto();
            return CONTACTO_SAVED;
        }
	    
	}

	// ADD EVENTO
	/**
	 * Este método se utiliza para agregar un evento a la lista de eventos.
	 * Verifica si el evento ya existe en la lista.
	 * Si el evento ya existe, devuelve un mensaje indicando que el evento ya está repetido.
	 * Si el evento no existe, se agrega a la lista de eventos y se guarda en el almacenamiento.
	 * @param evento El evento que se va a agregar.
	 * @return Un mensaje indicando el resultado de la operación.
	 */
	public static String addEvento(Evento evento) {
		//OPCIONAL ESTA COMPROBACION, COMPROBAR SI FUNCIONA EN DEBUG
        if (eventoExiste(evento)) {
            //System.out.println(CONTACTO_ISREP);
            return CONTACTO_ISREP;
        } else {
            listaEventos.add(evento);
            saveEvento();
            return CONTACTO_SAVED;
        }  
	}


	//SAVE CONTACTO	
	/**
	 * Este método se utiliza para guardar la lista de contactos en el almacenamiento.
	 * Utiliza la clase PersistenciaContacto para persistir los contactos en formato JSON.
	 */
	public static void saveContacto() {
		PersistenciaContacto pc = new PersistenciaContacto();
		pc.persistToJson(CONTACTO_DB);
	}
	
	//SAVE EVENTO
	/**
	 * Este método se utiliza para guardar la lista de eventos en el almacenamiento.
	 * Utiliza la clase PersistenciaEvento para persistir los eventos en formato JSON.
	 */
	private static void saveEvento() {
		PersistenciaEvento pe = new PersistenciaEvento();
		pe.persistToJson(EVENTO_DB);
		
	}
	
	// REMOVE CONTACTO
	/**
	 * Este método se utiliza para eliminar un contacto de la lista de contactos.
	 * Recorre la lista de contactos y elimina el contacto que coincide con el número de teléfono o móvil especificado.
	 * Luego guarda la lista de contactos actualizada en el almacenamiento.
	 * @param num El número de teléfono o móvil del contacto que se va a eliminar.
	 */
	public static void removeContacto(int num) {
	    for (int i = listaContactos.size() - 1; i >= 0; i--) {
	        if (listaContactos.get(i).getTelefono().compareTo(Integer.toString(num))==0 
	        	|| listaContactos.get(i).getMovil().compareTo(Integer.toString(num))==0) {
		        	listaContactos.remove(i);
		            saveContacto();
		            System.out.println("Contacto eliminado con éxito.\n");
	        }
	    }
	    	
	}
	
	//REMOVE EVENTO
	/**
	 * Este método se utiliza para eliminar un evento de la lista de eventos.
	 * Recorre la lista de eventos y elimina el evento que coincide con el ID especificado.
	 * Luego guarda la lista de eventos actualizada en el almacenamiento.
	 * @param num El ID del evento que se va a eliminar.
	 */
	public static void removeEvento(int num) {
		for(int i = listaEventos.size() -1; i >= 0;i--) {
			if(listaEventos.get(i).getId()==num) {
				listaEventos.remove(i);
				saveEvento();
				System.out.println("Contacto eliminado con éxito.\n");
			}
		}
	}
	
	// CHECK CONTACTO
	/**
	 * Este método se utiliza para verificar si un contacto ya existe en la lista de contactos.
	 * Compara el número de teléfono y el número de móvil del contacto con los contactos existentes en la lista.
	 * Si encuentra un contacto que coincide, devuelve el contacto encontrado. Si no encuentra ninguna coincidencia, devuelve null.
	 * @param telefono El número de teléfono del contacto.
	 * @param movil El número de móvil del contacto.
	 * @return El contacto encontrado si existe en la lista, o null si no existe.
	 */
	public static Contacto contactoExiste(String telefono,String movil) {

		for(int i = 0; i < listaContactos.size(); i ++) {
			if(listaContactos.get(i).getTelefono().compareTo(telefono)==0 || listaContactos.get(i).getMovil().compareTo(movil)==0) {
				return listaContactos.get(i);
			}
		}
		return null;		
	}
	// CHECK EVENTO
	/**
	 * Este método se utiliza para verificar si un evento ya existe en la lista de eventos.
	 * Compara el evento dado con los eventos existentes en la lista.
	 * Si encuentra un evento que coincide, devuelve true. Si no encuentra ninguna coincidencia, devuelve false.
	 * @param evento El evento que se va a verificar.
	 * @return true si el evento existe en la lista, o false si no existe.
	 */
	private static boolean eventoExiste(Evento evento) {
		for(Evento eventos : listaEventos) {		
			if(Objects.equals(eventos, evento)) {
				return true;
			}
		}
		return false;
	}
	
	public static void setListaContactos(ArrayList<Contacto> listaContactos) {
		DBAgenda.listaContactos = listaContactos;
	}

	public static ArrayList<Contacto> getListaContactos() {
		return listaContactos;
	}
	public static ArrayList<Evento> getListaEventos() {
		return listaEventos;
	}

	public static void setListaEventos(ArrayList<Evento> listaEventos) {
		DBAgenda.listaEventos = listaEventos;
	}
}
