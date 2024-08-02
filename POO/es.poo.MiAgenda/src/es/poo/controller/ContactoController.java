package es.poo.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import es.poo.database.DBAgenda;
import es.poo.model.Contacto;
import es.poo.model.ContactoPersonal;
import es.poo.model.ContactoProfesional;
import es.poo.view.VentanaAddContacto;
import es.poo.view.VentanaContacto;
import es.poo.view.VentanaEditarContacto;
//ESTA CLASE SE ENCARGA DE LA LOGICA DE TODAS LAS ACCIONES "CRUD" QUE OCURRAN EN LA PARTE DE CONTACTOS
public class ContactoController implements ActionListener,IVistasContactoEvento {
	
	private VentanaContacto ventanaContacto;
	private VentanaAddContacto ventanaAddContacto;
	private VentanaEditarContacto ventanaEditarContacto;
	private VentanasController vc;
	private Contacto contacto;
	private ContactoPersonal cp;
	private ContactoProfesional cpr;
	private String opcFiltro;
	
	private static ArrayList<Contacto> listaAux = DBAgenda.getListaContactos();
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//CAMBIAR VISTA DE REGISTROS CADA VEZ QUE SE CAMBIA EL FILTRO
		if(ventanaContacto !=null && (
				e.getSource() == ventanaContacto.getRdbtnOpcTodos() 
				|| e.getSource() == ventanaContacto.getRdbtnOpc2()
				|| e.getSource() == ventanaContacto.getRdbtnOpc3()))
		{//vemos tabla segun la opcion del filtro
			opcFiltro = (e.getSource()==ventanaContacto.getRdbtnOpcTodos()) ? "TODOS" 
					: (e.getSource()==ventanaContacto.getRdbtnOpc2()) ? "PERSONALES" 
					: (e.getSource()==ventanaContacto.getRdbtnOpc3()) ? "PROFESIONALES" 
					: "";

			if(opcFiltro!="") {
				if(ventanaContacto.getBtnResetBuscador().isVisible()) {
					ventanaContacto.getTxtBuscador().setText("");
					ventanaContacto.getBtnResetBuscador().setVisible(false);
				}
				limpiarTabla();
				verTabla(opcFiltro);
			}

		}
		//ELIMINAR CONTACTO/FILA
		else if(ventanaContacto !=null && e.getSource() == ventanaContacto.getBtnEliminar()) {
			eliminarFila();
		}
		//VER RESULTADO BUSQUEDA
		else if(ventanaContacto !=null && e.getSource() == ventanaContacto.getBtnBuscar()) {
			resultadoBusqueda();
		}
		//REESTABLECER TABLA DESPUES DE BUSQUEDA
		else if(ventanaContacto !=null && e.getSource() == ventanaContacto.getBtnResetBuscador()) {
			ventanaContacto.getBtnResetBuscador().setVisible(false);
			ventanaContacto.getTxtBuscador().setText("");
			limpiarTabla();
			verTabla("TODOS");
		}
		//EDITAR
		else if(ventanaEditarContacto !=null && e.getSource() == ventanaEditarContacto.getBtnActualizarrContacto()) {
			verificarDatosEditar();
			vc.cerrarVentanaEditarContacto();
			vc.mostrarVentanaContacto();			
			verTabla("TODOS");
		}
		//AGREGAR CONTACTO
		else if(ventanaAddContacto !=null && e.getSource() == ventanaAddContacto.getBtnAñadirContacto()) {
			if(verificarDatos()) {
		        vc.cerrarVentanaAddContacto();
				vc.mostrarVentanaContacto();
				verTabla("TODOS");
			}
		}
	}
	
	/*
	 * Constructor de la clase ContactoController para instanciarse desde
	 * la clase VentanaContacto. Recibe un objeto de la clase 
	 * VentanaContacto y otro de la clase VentanasController.
	 * Con estos objetos instanciamos los atributos de clase que 
	 * corresponden con el tipo de clase de estos objetos que se pasan 
	 * como parámetros. Una vez instanciados podemos acceder a los métodos 
	 * y atributos de estos objetos.
	 */
	public ContactoController(VentanaContacto ventanaContacto, VentanasController controller) {		
		this.ventanaContacto = ventanaContacto;
		this.vc = controller;
	}
	
	/*
	 * Constructor de la clase ContactoController para instanciarse desde
	 * la clase VentanaAddContacto. Recibe un objeto de la clase 
	 * VentanaAddContacto y otro de la clase VentanasController.
	 * Con estos objetos instanciamos los atributos de clase que 
	 * corresponden con el tipo de clase de estos objetos que se pasan 
	 * como parámetros. Una vez instanciados podemos acceder a los métodos 
	 * y atributos de estos objetos.
	 */
	public ContactoController(VentanaAddContacto objAc,VentanasController vc) {		
		ventanaAddContacto = objAc;
		this.vc = vc;
	}
	
	/*
	 * Constructor de la clase ContactoController para instanciarse desde
	 * la clase VentanaEditarContacto. Recibe un objeto de la clase 
	 * VentanaEditarContacto y otro de la clase VentanasController.
	 * Con estos objetos instanciamos los atributos de clase que 
	 * corresponden con el tipo de clase de estos objetos que se pasan 
	 * como parámetros. Una vez instanciados podemos acceder a los métodos 
	 * y atributos de estos objetos.
	 */
	public ContactoController(VentanaEditarContacto objEc, VentanasController vec) {
		ventanaEditarContacto = objEc;
		this.vc = vec;
	}

	//VERIFICAR DATOS DE ENTRADA DE NUEVO CONTACTO
	@Override
	public boolean verificarDatos() {
		//VERIFICAR DATOS Y AGREGAR CONTACTO A LA DB  	
        // Comprobar campos obligatorios {nombre, telefono o movil}
		try {
	        String nombre = ventanaAddContacto.getTxtFieldNombre().getText().trim();
	        String apellidos = ventanaAddContacto.getTxtFieldApellidos().getText().trim();
	        String aliasOempresa = ventanaAddContacto.getTxtFieldAliasEmpresa().getText().trim();
	        String lblAliasEmpresa = ventanaAddContacto.getOpcionComboBox().trim();
	        String telefonoStr = ventanaAddContacto.getTxtFieldTelefono().getText().trim();
	        String movilStr = ventanaAddContacto.getTxtFieldMovil().getText().trim();
	        String email = ventanaAddContacto.getTxtFieldEmail().getText().trim();
	
	        if (nombre.isBlank() &&  movilStr.isBlank()) {
	            // Mostrar mensaje de campos obligatorios en una pestaña flotante
	            JOptionPane.showMessageDialog(null, "Por favor, complete los campos obligatorios.", "Campos obligatorios: Nombre, Móvil ", JOptionPane.WARNING_MESSAGE);
	            return false;
	        }
	        if(telefonoStr.isBlank()) {
	        	telefonoStr= "null";
	        }
	        
	        // Parsear los campos de telefono y movil a enteros si no están vacíos
	        if (!telefonoStr.equals("null")) {
	            try {
	            	// Eliminar caracteres no numéricos del texto formateado
	            	String numeros = telefonoStr.replaceAll("[^0-9]", "");
	
	            	// Convertir el texto con los dígitos en un valor entero
	            	telefonoStr = numeros;
	            } catch (NumberFormatException ex) {
	                // Manejar la excepción si el campo de teléfono no es un número válido
	                ex.printStackTrace();
	                return false;
	            }
	        }
	
	        if (!movilStr.isBlank()) {
	            try {
	            	// Eliminar caracteres no numéricos del texto formateado
	            	String numeros = movilStr.replaceAll("[^0-9]", "");
	
	            	// Convertir el texto con los dígitos en un valor entero
	            	movilStr = numeros;
	            } catch (NumberFormatException ex) {
	                // Manejar la excepción si el campo de móvil no es un número válido
	                ex.printStackTrace();
	                return false;
	            }
	        }
	        
	        //TODO Controlar que la fecha no esté vacia y si lo está asignar a null
	        // Obtener la fecha seleccionada del JDateChooser
	        Date fechaSeleccionada = ventanaAddContacto.getInputFechaNac().getDate();
	
	        // Crear un objeto SimpleDateFormat para el formato "dd-MM-yyyy"
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	        // Convertir la fecha a String con el formato deseado
	        String fechaFormateada = sdf.format(fechaSeleccionada);
	
	        // Crear el objeto de contacto y acceder a los metodos del objeto
	        contacto = new Contacto(nombre.toUpperCase(), apellidos.toUpperCase(), telefonoStr, movilStr, fechaFormateada, email);  
	        if(!aliasOempresa.isBlank()) {
	        	if(lblAliasEmpresa.compareTo("ALIAS")==0) {
	        		cp = new ContactoPersonal(contacto,aliasOempresa);
	        		DBAgenda.addUser(cp);
	        	}
	        	else if(lblAliasEmpresa.compareTo("EMPRESA")==0) {
	        		cpr = new ContactoProfesional(contacto,aliasOempresa);
	        		DBAgenda.addUser(cpr);
	        	}
	        }
	        else {
	        	DBAgenda.addUser(contacto);
	        }
		}
		catch(Exception e) {
		    // Manejo de excepciones
			JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos obligatorios (*)", "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
		}
        return true;
	}
	
	@Override
	public boolean verificarDatosEditar() {

		String numViejo = ventanaEditarContacto.getNumViejo();
		
        String nombre = ventanaEditarContacto.getTxtFieldNombre().getText().trim();
        String apellidos = ventanaEditarContacto.getTxtFieldApellidos().getText().trim();
        String aliasOempresa = ventanaEditarContacto.getTxtFieldAliasEmpresa().getText().trim();
        String comboBoxAliasEmpresa = ventanaEditarContacto.getOpcionComboBox().trim();
        String telefonoStr = ventanaEditarContacto.getTxtFieldTelefono().getText().trim();
        String movilStr = ventanaEditarContacto.getTxtFieldMovil().getText().trim();
        String email = ventanaEditarContacto.getTxtFieldEmail().getText().trim();
        
        if (nombre.isBlank() &&  movilStr.isBlank()) {
            // Mostrar mensaje de campos obligatorios en una pestaña flotante
            JOptionPane.showMessageDialog(null, "Por favor, complete los campos obligatorios.", "Campos obligatorios: Nombre, Móvil ", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(telefonoStr.isBlank()) {
        	telefonoStr= "null";
        }
        
        // Parsear los campos de telefono y movil a enteros si no están vacíos
        if (!telefonoStr.equals("null")) {
            try {
            	// Eliminar caracteres no numéricos del texto formateado
            	String numeros = telefonoStr.replaceAll("[^0-9]", "");

            	// Convertir el texto con los dígitos en un valor entero
            	telefonoStr = numeros;
            } catch (NumberFormatException ex) {
                // Manejar la excepción si el campo de teléfono no es un número válido
                ex.printStackTrace();
                return false;
            }
        }

        if (!movilStr.isBlank()) {
            try {
            	// Eliminar caracteres no numéricos del texto formateado
            	String numeros = movilStr.replaceAll("[^0-9]", "");

            	// Convertir el texto con los dígitos en un valor entero
            	movilStr = numeros;
            } catch (NumberFormatException ex) {
                // Manejar la excepción si el campo de móvil no es un número válido
                ex.printStackTrace();
                return false;
            }
        }
        // Obtener la fecha seleccionada del JDateChooser
        Date fechaSeleccionada = ventanaEditarContacto.getInputFechaNac().getDate();

        // Crear un objeto SimpleDateFormat para el formato "dd-MM-yyyy"
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        // Convertir la fecha a String con el formato deseado
        String fechaFormateada = sdf.format(fechaSeleccionada);
        
        contacto = new Contacto(nombre, apellidos, telefonoStr, movilStr, fechaFormateada, email);  
		ContactoPersonal cp=null;
		ContactoProfesional cpr=null;

		int contadorCambios = 0;
		if(numViejo.equals(movilStr)) {
	        for (Contacto contactoAux : DBAgenda.getListaContactos()) {
	            if (contactoAux.getMovil().equals(numViejo)) {
	                
	            	if(contactoAux instanceof ContactoPersonal) {
	            		cp = (ContactoPersonal) contactoAux;
	            		if(!(cp.getNombre().equals(nombre)
	            			&& cp.getApellidos().equals(apellidos)
	            			&& cp.getAlias().equals(aliasOempresa)
	            			&& cp.getTelefono().equals(telefonoStr)
	            			&& cp.getMovil().equals(movilStr)
	            			&& cp.getEmail().equals(email)
	            			&& cp.getFechaNac().equals(fechaFormateada))) 
	            		{
	            			cp.setNombre(nombre);
	            			cp.setApellidos(apellidos);
	            			cp.setAlias(aliasOempresa);
	            			cp.setTelefono(telefonoStr);
	            			cp.setMovil(movilStr);
	            			cp.setEmail(email);
	            			cp.setFechaNac(fechaFormateada);
	            			contadorCambios++;
	            		}
	            	}
	            	else if(contacto instanceof ContactoProfesional) {
	            		cpr = (ContactoProfesional) contactoAux;
	            		if(!(cpr.getNombre().equals(nombre)
		            			&& cpr.getApellidos().equals(apellidos)
		            			&& cpr.getEmpresa().equals(aliasOempresa)
		            			&& cpr.getTelefono().equals(telefonoStr)
		            			&& cpr.getMovil().equals(movilStr)
		            			&& cpr.getEmail().equals(email)
		            			&& cpr.getFechaNac().equals(fechaFormateada))) 
	            			{
		            			cpr.setNombre(nombre);
		            			cpr.setApellidos(apellidos);
		            			cpr.setEmpresa(aliasOempresa);
		            			cpr.setTelefono(telefonoStr);
		            			cpr.setMovil(movilStr);
		            			cpr.setEmail(email);
		            			cpr.setFechaNac(fechaFormateada);
		            			contadorCambios++;
		            		}
	            	}
	                break; 
	            }
	        }
		}
		else {
			contadorCambios++;
		}


		if(contadorCambios>0 ) {
			DBAgenda.removeContacto(Integer.parseInt(numViejo));
	        if(!aliasOempresa.isBlank()) {
	        	if(comboBoxAliasEmpresa.equals("ALIAS")) {
	        		//cp = new ContactoPersonal(contacto,aliasOempresa);
	        		DBAgenda.addUser(cp);
	        	}
	        	else if(comboBoxAliasEmpresa.equals("EMPRESA")) {
	        		//cpr = new ContactoProfesional(contacto,aliasOempresa);
	        		DBAgenda.addUser(cpr);
	        	}
	        }
	        else {
	        	DBAgenda.addUser(contacto);
	        } 
	        return true;
		}
		return false;
	}
	
	//LIMPIAR TABLA REGISTROS
	@Override
	public void limpiarTabla() {
		Component[] componentes = ventanaContacto.getPanelRegistros().getComponents();
		for (int i = 6; i < componentes.length; i++) {
			//System.out.println(componentes[i]);
			ventanaContacto.getPanelRegistros().remove(componentes[i]);
		}
		ventanaContacto.getPanelRegistros().revalidate();
		ventanaContacto.getPanelRegistros().repaint();
	}
	//ORDENAR TABLA AUX
	@Override
	public void ordenarListaAux() {
		//Ordena la lista de contactos por nombre
        Collections.sort(listaAux, new Comparator<Contacto>() {
            @Override
            public int compare(Contacto contacto1, Contacto contacto2) {
                return contacto1.getNombre().compareTo(contacto2.getNombre());
            }
        });
	}
	//ACTUALIZA TABLA REGISTROS
	@Override
	public void verTabla(String opc) {
		
		ordenarListaAux();
        //comprobamos que la lista no esta vacia
        if(listaAux.size()>0) {
            //Crear bucle for y asignar cada valor requerido a un jcheckbox/jlabel/jbutton
        	int numFila=1;
            for (int i = 0; i < listaAux.size(); i++) {
                     	
                Contacto contacto = listaAux.get(i);
            	JCheckBox checkBox = new JCheckBox();
                JLabel nombre = new JLabel();
                JLabel alias = new JLabel("");
                JLabel empresa = new JLabel("");
                JLabel telOmovil = new JLabel("null");
                //JLabel email = new JLabel();
                JButton editar = new JButton("Editar");
                editar.setName(contacto.getTelefono());
                editar.addActionListener(this);
                
                //inicializar campos comunes
                checkBox.setName(contacto.getMovil().trim());
                checkBox.setOpaque(false);
                checkBox.setContentAreaFilled(false);
                nombre.setText(contacto.getNombre());
                String telefono = (contacto.getTelefono().isBlank()) ? "null" : contacto.getTelefono();
                telOmovil.setText(telefono+" / "+contacto.getMovil());
                //email.setText(contacto.getEmail());
                
                // Verifica el tipo de objeto y accede a los atributos específicos
                if (contacto instanceof ContactoPersonal) {
                    ContactoPersonal contactoPersonal = (ContactoPersonal) contacto;
                    // Accede a los atributos específicos de ContactoPersonal
                    alias.setText(contactoPersonal.getAlias());                
                   
                } else if (contacto instanceof ContactoProfesional) {
                    ContactoProfesional contactoProfesional = (ContactoProfesional) contacto;
                 // Accede a los atributos específicos de ContactoProfesional
                    empresa.setText(contactoProfesional.getEmpresa());
                    
                }             
                //llamamos a la funcion y le pasamos los campos
                if(opc.equals("PERSONALES") && !(alias.getText().isBlank())) {
                	VentanaContacto.pintarRegistrosContacto(numFila, checkBox, nombre, alias, empresa, telOmovil,editar);
                	numFila++;
                }
                else if(opc.equals("PROFESIONALES") && !(empresa.getText().isBlank())) {
                	VentanaContacto.pintarRegistrosContacto(numFila, checkBox, nombre, alias, empresa, telOmovil,editar);
                	numFila++;
                }
                else if(opc.equals("TODOS")) {
                	VentanaContacto.pintarRegistrosContacto(numFila, checkBox, nombre, alias, empresa, telOmovil,editar);
                	numFila++;
                }       
            }
        }
	}

	
	//OBTENER RESULTADOS DE BUSQUEDA
	@Override
	public void resultadoBusqueda() {
		//obtenemos el nombre del buscador y lo pasamos a mayuscula
		String nombreBuscado = ventanaContacto.getTxtBuscador().getText().toUpperCase().trim();
		//verificamos que existen registros con ese nombre
		if(listaAux.size()>0) {
			int cleanRequired = 0;
			int numFila=1;
			for (Contacto contacto : DBAgenda.getListaContactos()) {
				if(contacto.getNombre().equals(nombreBuscado)) {
				 	cleanRequired++;
				 	if(cleanRequired==1) {
				 		limpiarTabla();
				 		ventanaContacto.getBtnResetBuscador().setVisible(true);
				 		ventanaContacto.getRdbtnOpcTodos().setSelected(true);
				 	}
		        	JCheckBox checkBox = new JCheckBox();
		            JLabel nombre = new JLabel();
		            JLabel alias = new JLabel("");
		            JLabel empresa = new JLabel("");
			        JLabel telOmovil = new JLabel("null");
			        //JLabel email = new JLabel();
			        JButton editar = new JButton("Editar");
			        editar.setName(contacto.getTelefono());
			        editar.addActionListener(this);
			        
			        //inicializar campos comunes
			        checkBox.setName(contacto.getMovil().trim());
			        checkBox.setOpaque(false);
			        checkBox.setContentAreaFilled(false);
			        nombre.setText(contacto.getNombre());
			        String telefono = (contacto.getTelefono().isBlank()) ? "null" : contacto.getTelefono();
			        telOmovil.setText(telefono+" / "+contacto.getMovil());
			        
			        // Verifica el tipo de objeto y accede a los atributos específicos
			        if (contacto instanceof ContactoPersonal) {
			            ContactoPersonal contactoPersonal = (ContactoPersonal) contacto;
			            // Accede a los atributos específicos de ContactoPersonal
			            alias.setText(contactoPersonal.getAlias());                
			           
			        } 
			        else if (contacto instanceof ContactoProfesional) {
			            ContactoProfesional contactoProfesional = (ContactoProfesional) contacto;
			         // Accede a los atributos específicos de ContactoProfesional
			                empresa.setText(contactoProfesional.getEmpresa());
			                
			        } 		            
		        	VentanaContacto.pintarRegistrosContacto(numFila, checkBox, nombre, alias, empresa, telOmovil,editar);
		        	numFila++;            
				}
			 }
		}
		
	}
	//ELIMINAR
	@Override
	public void eliminarFila() {
		Component[] componentes = ventanaContacto.getPanelRegistros().getComponents();
		int contador=0;
		for (int i = 6; i < componentes.length; i+=6) {
			if(componentes[i] instanceof JCheckBox) {
				JCheckBox cb = (JCheckBox) componentes[i];
				if(cb.isSelected()) {
					contador++;
					for(int j=i;j<i+6;j++) {
						//LLAMAR AL METODO "BORAR_REGISTRO" DE DBAgenda pasandole el numero de movil
						DBAgenda.removeContacto(Integer.parseInt(cb.getName()));
						//BORRAMOS COMPONENTES DE LA TABLA
						ventanaContacto.getPanelRegistros().remove(componentes[j]);
					}
				}
			}
		}
		if(contador==0) {
			JOptionPane.showMessageDialog(null, "NO HAY CONTACTOS SELECCIONADOS.\nSELECCIONE UNO O VARIOS CONTACTOS PARA ELIMINAR", "AVISO: ELIMINAR", JOptionPane.WARNING_MESSAGE);
            return;
		}
		else {
			ventanaContacto.getPanelRegistros().revalidate();
			ventanaContacto.getPanelRegistros().repaint();
		}
	}
}