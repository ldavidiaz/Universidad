package es.poo.controller;

public interface IVistasContactoEvento {
	/*
	 * Este método se encargar de ordenar una copia del ArrayList de 
	 * contactos o eventos de la Clase DBAgenda, mediante un bucle 
	 * for each, comparando algún atributo en específico
	 */
	void ordenarListaAux();
	
	/*
	 * Este método se encarga de obtener y pasar los datos necesarios 
	 * de los contactos o  eventos guardados al método correspondiente
	 * de la clase correspondiente que se obtiene con al comprobar el
	 * valor de <b> opcion </b>.
	 * @param opcion. Puede tomar los valores de: "AÑADIR" o "EDITAR"
	 */
	void verTabla(String opcion);
	
	/*
	 * Elimina todos los registros de la tabla de datos.
	 */
	void limpiarTabla();
	
	/*
	 * Elimina las filas de registros de la tabla de datos
	 * con el checkbox seleccionado.
	 */
	void eliminarFila();
	
	/*
	 * Verifica que los datos introducidos en los formularios de alta de
	 * registro sean correctos, en este caso se crea un objeto de la clase
	 * correspondiente y se manda al metodo de agregado correspondiente
	 * de la clase DBAgenda, en caso contrario, le mostrará al usuario
	 * una ventana emergente con un aviso o error al intentar guardar los
	 * datos.
	 */
	boolean verificarDatos();
	
	/*
	 * Verifica y comprueba los datos del formulario de editar.
	 * Si no hay errores elimina el registro anterior pasando su
	 * atributo identificar al metodo correspondiente de eliminado de 
	 * la clase DBAgenda,y pasa el nuevo objeto correspondiente al metodo
	 * de agregado correspondiente de la clase DBAgenda. En caso de error
	 * le mostrará al usuario una ventana emergente con un aviso o error
	 *  al intentar guardar los datos.
	 */
	boolean verificarDatosEditar();
	/*
	 * Este método obtiene el texto introducido del buscador y
	 * lo compara con el atributo correspondiente de los objetos 
	 * almacenados en la clase DBAgenda. En caso de que se los atributos
	 * comparados sean iguales, se obtienen los datos necesarios de este
	 * objeto y se pintan en la tabla en la clase correspondiente.
	 */
	void resultadoBusqueda();	
}