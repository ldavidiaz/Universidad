package es.poo.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import es.poo.database.DBAgenda;
import es.poo.model.Cita;
import es.poo.model.Contacto;
import es.poo.model.ContactoPersonal;
import es.poo.model.Evento;
import es.poo.model.Notificacion;
import es.poo.model.Notificacion.TipoNotificacion;
import es.poo.model.Notificacion.TipoTiempo;
import es.poo.model.Reunion;
import es.poo.view.VentanaAddEvento;
import es.poo.view.VentanaEditarEvento;
import es.poo.view.VentanaEvento;

public class EventoController implements ActionListener,IVistasContactoEvento {
	
	private VentanaEvento ventanaEvento;
	private VentanaAddEvento ventanaAddEvento;
	private VentanaEditarEvento ventanaEditarEvento;
	private VentanasController vc;
	
	private String opcFiltro;
	
	private static ArrayList<Evento> listaAux = DBAgenda.getListaEventos();
	private static ArrayList<String> listaContactosP = new ArrayList<>();
	@SuppressWarnings("unused")
	private static ArrayList<Notificacion> listaNotificaciones = new ArrayList<>();
	private static int sglContadorListaCP=0;

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//CAMBIAR VISTA DE REGISTROS CADA VEZ QUE SE CAMBIA EL FILTRO
		if(ventanaEvento !=null && (
				e.getSource() == ventanaEvento.getRdbtnOpcTodos() 
				|| e.getSource() == ventanaEvento.getRdbtnOpc2()
				|| e.getSource() == ventanaEvento.getRdbtnOpc3()))
		{//vemos tabla segun la opcion del filtro
			opcFiltro = (e.getSource()==ventanaEvento.getRdbtnOpcTodos()) ? "TODOS" 
					: (e.getSource()==ventanaEvento.getRdbtnOpc2()) ? "CITA" 
					: (e.getSource()==ventanaEvento.getRdbtnOpc3()) ? "REUNION" 
					: "";
			if(opcFiltro!="") {
				if(ventanaEvento.getBtnResetBuscador().isVisible()) {
					ventanaEvento.getTxtBuscador().setText("");
					ventanaEvento.getBtnResetBuscador().setVisible(false);
				}
				limpiarTabla();
				verTabla(opcFiltro);
			}

		}
		//ELIMINAR EVENTO/FILA
		else if(ventanaEvento !=null && e.getSource() == ventanaEvento.getBtnEliminar()) {
			eliminarFila();
		}
		//VERIFICAR ADD DATOS Y GUARDAR
		else if(ventanaAddEvento !=null && e.getSource() == ventanaAddEvento.getBtnAñadirEvento()) {
			if(verificarDatos()) {
		        vc.cerrarVentanaAddEvento();
				vc.mostrarVentanaEvento();
				verTabla("TODOS");
			}
		}
		//VENTANA AÑADIR
		//ACTIVAR/DESACTIVAR PANEL NOTIFICACION
		else if(ventanaAddEvento !=null && e.getSource() == ventanaAddEvento.getCbNotificarSiNo()) {
			if(ventanaAddEvento.getOpcNotificarSiNo().equals("SI")) {
				ventanaAddEvento.getBtnAddNotificacion().setVisible(true);
				if(!ventanaAddEvento.getObjNotificacion().isEmpty()) {
					ventanaAddEvento.getBtnVerNotificaciones().setVisible(true);
				}
			}
			else if(ventanaAddEvento.getOpcNotificarSiNo().equals("NO")) {
				ventanaAddEvento.getBtnAddNotificacion().setVisible(false);
				ventanaAddEvento.getBtnVerNotificaciones().setVisible(false);
			}
			
		}
		else if(ventanaAddEvento !=null && e.getSource() == ventanaAddEvento.getBtnAddNotificacion()) {
			ventanaAddEvento.ventanaAddNotificacion();
		}
		//GUARDAR NOTIFICACION
		else if(ventanaAddEvento !=null && e.getSource() == ventanaAddEvento.getBtnGuardarNotificacion()) {
			guardarNotificacion("AÑADIR");
		}
		else if(ventanaAddEvento !=null && e.getSource() == ventanaAddEvento.getBtnVerNotificaciones()) {
			ventanaAddEvento.ventanaVerNotificacion();
		}
		else if(ventanaAddEvento !=null && e.getSource() == ventanaAddEvento.getBtnEliminarNotificacion()) {
			borrarNotificacion("AÑADIR");
		}
		//TIPO EVENTO
		else if(ventanaAddEvento !=null && e.getSource() == ventanaAddEvento.getBtnAgregarTipoEvento()) {
			ventanaAddEvento.getBtnAgregarTipoEvento().setVisible(false);
			ventanaAddEvento.getPanelTipoEvento().setVisible(true);
			ventanaAddEvento.getBtnBorrarTipoEvento().setVisible(true);
			
		}
		else if(ventanaAddEvento !=null && e.getSource() == ventanaAddEvento.getCbTipoEvento()) {
			if(ventanaAddEvento.getOpcTipoEvento().equals("REUNION")) {
				cerrarPaneles("tipoEvento", "reunion","AÑADIR");
				ventanaAddEvento.getPanelTipoReunion().setVisible(true);
			}
			else if(ventanaAddEvento.getOpcTipoEvento().equals("CITA")) {
				cerrarPaneles("tipoEvento", "cita","AÑADIR");
				cargarContactosPersonales();
				ventanaAddEvento.getPanelTipoCita().setVisible(true);
			}
		}
		//PANELES CITA
		else if(ventanaAddEvento !=null && e.getSource() == ventanaAddEvento.getCbTipoCita()) {
			if(ventanaAddEvento.getOpcTipoCita().equals("CUMPLEAÑOS")) {
				cerrarPaneles("tipoCita","cumple","AÑADIR");
				ventanaAddEvento.getPanelCitaCumple().setVisible(true);
				setContactosPersonales("AÑADIR","CUMPLE");
			}
			else if(ventanaAddEvento.getOpcTipoCita().equals("ANIVERSARIO")) {
				cerrarPaneles("tipoCita","aniversario","AÑADIR");
				ventanaAddEvento.getPanelCitaAniversario().setVisible(true);
				setContactosPersonales("AÑADIR","ANIVERSARIO");
			}
			else if(ventanaAddEvento.getOpcTipoCita().equals("MEDICO")) {
				cerrarPaneles("tipoCita","medico","AÑADIR");
				ventanaAddEvento.getPanelCitaMedico().setVisible(true);
			}
		}
		//PANELES REUNION
		else if(ventanaAddEvento !=null && e.getSource() == ventanaAddEvento.getCbTipoReunion()) {
			if(ventanaAddEvento.getOpcTipoReunion().equals("PRESENCIAL")) {
				cerrarPaneles("tipoReunion","presencial","AÑADIR");
				ventanaAddEvento.getPanelReunionContactos().setVisible(true);
				ventanaAddEvento.getPanelReunionPresencial().setVisible(true);
			}
			else if(ventanaAddEvento.getOpcTipoReunion().equals("LLAMADA")) {
				cerrarPaneles("tipoReunion","llamada","AÑADIR");
				ventanaAddEvento.getPanelReunionContactos().setVisible(true);
				ventanaAddEvento.getPanelReunionLlamada().setVisible(true);
			}
			else if(ventanaAddEvento.getOpcTipoReunion().equals("VIDEOCONFERENCIA")) {
				cerrarPaneles("tipoReunion","video","AÑADIR");
				ventanaAddEvento.getPanelReunionContactos().setVisible(true);
				ventanaAddEvento.getPanelReunionVideo().setVisible(true);
			}
		}
		else if(ventanaAddEvento !=null && e.getSource() == ventanaAddEvento.getCbTipoVideoConferencia()) {
			if(ventanaAddEvento.getOpcTipoVideoConferencia().equals("Otra: Especificar")) {

				ventanaAddEvento.getTxtOtraVideoConferencia().setVisible(true);
			}
			if(ventanaAddEvento.getOpcTipoVideoConferencia().equals("Google Meet")
				|| ventanaAddEvento.getOpcTipoVideoConferencia().equals("Microsoft Teams")
				|| ventanaAddEvento.getOpcTipoVideoConferencia().equals("Zoom")
				|| ventanaAddEvento.getOpcTipoVideoConferencia().equals("Skype")) {
				
				if(ventanaAddEvento.getTxtOtraVideoConferencia().isVisible()==true) {
					ventanaAddEvento.getTxtOtraVideoConferencia().setVisible(false);
				}			
			}
		}
		//BOTON BORRAR DETALLES EVENTO/PANELES
		else if(ventanaAddEvento !=null && e.getSource() == ventanaAddEvento.getBtnBorrarTipoEvento()) {
			cerrarPaneles("todos","null","AÑADIR");
		}
		else if(ventanaAddEvento !=null && e.getSource() == ventanaAddEvento.getBtnAddContactoReunion()) {
			cargarContactosReunion("AÑADIR");
			ventanaAddEvento.ventanaAddContactos();
		}
		else if(ventanaAddEvento !=null && e.getSource() == ventanaAddEvento.getBtnAñadirCP()) {
			aÑadirContactosReunion("AÑADIR");
		}
		else if(ventanaAddEvento !=null && e.getSource() == ventanaAddEvento.getBtnVerContactoReunion()) {
			ventanaAddEvento.ventanaVerContactosReunion();
		}
		else if(ventanaAddEvento !=null && e.getSource() == ventanaAddEvento.getBtnEliminarCP()) {
			borrarContactosReunion("AÑADIR");
		}/**********/
		//VENTANA EDITAR -- COMPROBAR BOTONES Y REUTILIZAR METODOS
		else if(ventanaEditarEvento !=null && e.getSource() == ventanaEditarEvento.getBtnActualizarEvento()) {
			if(verificarDatosEditar()) {
				vc.cerrarVentanaEditarEvento();
				vc.mostrarVentanaEvento();
				verTabla("TODOS");
			}
		}
		//ACTIVAR/DESACTIVAR PANEL NOTIFICACION
		else if(ventanaEditarEvento !=null && e.getSource() == ventanaEditarEvento.getCbNotificarSiNo()) {
			if(ventanaEditarEvento.getOpcNotificarSiNo().equals("SI")) {
				ventanaEditarEvento.getBtnAddNotificacion().setVisible(true);
				if(ventanaEditarEvento.getObjNotificacion()!=null) {
					ventanaEditarEvento.getBtnVerNotificaciones().setVisible(true);
				}
			}
			else if(ventanaEditarEvento.getOpcNotificarSiNo().equals("NO")) {
				ventanaEditarEvento.getBtnAddNotificacion().setVisible(false);
				ventanaEditarEvento.getBtnVerNotificaciones().setVisible(false);
			}
			
		}
		else if(ventanaEditarEvento !=null && e.getSource() == ventanaEditarEvento.getBtnAddNotificacion()) {
			ventanaEditarEvento.ventanaAddNotificacion();
		}
		//GUARDAR NOTIFICACION
		else if(ventanaEditarEvento !=null && e.getSource() == ventanaEditarEvento.getBtnGuardarNotificacion()) {
			guardarNotificacion("EDITAR");
		}
		else if(ventanaEditarEvento !=null && e.getSource() == ventanaEditarEvento.getBtnVerNotificaciones()) {
			ventanaEditarEvento.ventanaVerNotificacion();
		}
		else if(ventanaEditarEvento !=null && e.getSource() == ventanaEditarEvento.getBtnEliminarNotificacion()) {
			borrarNotificacion("EDITAR");
		}
		//TIPO EVENTO
		else if(ventanaEditarEvento !=null && e.getSource() == ventanaEditarEvento.getBtnAgregarTipoEvento()) {
			ventanaEditarEvento.getBtnAgregarTipoEvento().setVisible(false);
			ventanaEditarEvento.getPanelTipoEvento().setVisible(true);
			ventanaEditarEvento.getBtnBorrarTipoEvento().setVisible(true);
			
		}
		else if(ventanaEditarEvento !=null && e.getSource() == ventanaEditarEvento.getCbTipoEvento()) {
			if(ventanaEditarEvento.getOpcTipoEvento().equals("REUNION")) {
				cerrarPaneles("tipoEvento", "reunion","EDITAR");
				ventanaEditarEvento.getPanelTipoReunion().setVisible(true);
			}
			else if(ventanaEditarEvento.getOpcTipoEvento().equals("CITA")) {
				cerrarPaneles("tipoEvento", "cita","EDITAR");
				cargarContactosPersonales();
				ventanaEditarEvento.getPanelTipoCita().setVisible(true);
			}
		}
		//PANELES CITA
		else if(ventanaEditarEvento !=null && e.getSource() == ventanaEditarEvento.getCbTipoCita()) {
			if(ventanaEditarEvento.getOpcTipoCita().equals("CUMPLEAÑOS")) {
				cerrarPaneles("tipoCita","cumple","EDITAR");
				ventanaEditarEvento.getPanelCitaCumple().setVisible(true);
				setContactosPersonales("EDITAR","CUMPLE");
			}
			else if(ventanaEditarEvento.getOpcTipoCita().equals("ANIVERSARIO")) {
				cerrarPaneles("tipoCita","aniversario","EDITAR");
				ventanaEditarEvento.getPanelCitaAniversario().setVisible(true);
				setContactosPersonales("EDITAR","ANIVERSARIO");
			}
			else if(ventanaEditarEvento.getOpcTipoCita().equals("MEDICO")) {
				cerrarPaneles("tipoCita","medico","EDITAR");
				ventanaEditarEvento.getPanelCitaMedico().setVisible(true);
			}
		}
		//PANELES REUNION
		else if(ventanaEditarEvento !=null && e.getSource() == ventanaEditarEvento.getCbTipoReunion()) {
			if(ventanaEditarEvento.getOpcTipoReunion().equals("PRESENCIAL")) {
				cerrarPaneles("tipoReunion","presencial","EDITAR");
				ventanaEditarEvento.getPanelReunionContactos().setVisible(true);
				ventanaEditarEvento.getPanelReunionPresencial().setVisible(true);
			}
			else if(ventanaEditarEvento.getOpcTipoReunion().equals("LLAMADA")) {
				cerrarPaneles("tipoReunion","llamada","EDITAR");
				ventanaEditarEvento.getPanelReunionContactos().setVisible(true);
				ventanaEditarEvento.getPanelReunionLlamada().setVisible(true);
			}
			else if(ventanaEditarEvento.getOpcTipoReunion().equals("VIDEOCONFERENCIA")) {
				cerrarPaneles("tipoReunion","video","EDITAR");
				ventanaEditarEvento.getPanelReunionContactos().setVisible(true);
				ventanaEditarEvento.getPanelReunionVideo().setVisible(true);
			}
		}
		else if(ventanaEditarEvento !=null && e.getSource() == ventanaEditarEvento.getCbTipoVideoConferencia()) {
			if(ventanaEditarEvento.getOpcTipoVideoConferencia().equals("Otra: Especificar")) {

				ventanaEditarEvento.getTxtOtraVideoConferencia().setVisible(true);
			}
			if(ventanaEditarEvento.getOpcTipoVideoConferencia().equals("Google Meet")
				|| ventanaEditarEvento.getOpcTipoVideoConferencia().equals("Microsoft Teams")
				|| ventanaEditarEvento.getOpcTipoVideoConferencia().equals("Zoom")
				|| ventanaEditarEvento.getOpcTipoVideoConferencia().equals("Skype")) {
				
				if(ventanaEditarEvento.getTxtOtraVideoConferencia().isVisible()==true) {
					ventanaEditarEvento.getTxtOtraVideoConferencia().setVisible(false);
				}			
			}
		}
		//BOTON BORRAR DETALLES EVENTO/PANELES
		else if(ventanaEditarEvento !=null && e.getSource() == ventanaEditarEvento.getBtnBorrarTipoEvento()) {
			cerrarPaneles("todos","null","EDITAR");
		}
		else if(ventanaEditarEvento !=null && e.getSource() == ventanaEditarEvento.getBtnAddContactoReunion()) {
			cargarContactosReunion("EDITAR");
			ventanaEditarEvento.ventanaAddContactos();
		}
		else if(ventanaEditarEvento !=null && e.getSource() == ventanaEditarEvento.getBtnAñadirCP()) {
			aÑadirContactosReunion("EDITAR");
		}
		else if(ventanaEditarEvento !=null && e.getSource() == ventanaEditarEvento.getBtnVerContactoReunion()) {
			ventanaEditarEvento.ventanaVerContactosReunion();
		}
		else if(ventanaEditarEvento !=null && e.getSource() == ventanaEditarEvento.getBtnEliminarCP()) {
			borrarContactosReunion("EDITAR");
		}
	}
	
	//METODOS PARA LA VENTANA ADD EVENTOS
	/*
	 * Este método se encarga de eliminar las notificaciones guardardadas
	 * en el formurlario de agregado o edición de un evento.
	 * El tipo de formulario se obtiene a través del parámetro <b> ventana </b>.
	 * Los datos se eliminan tanto de un DefaultListModel<String> como de un
	 * ArrayList<Notificacion>.
	 * @param ventana. Puede ser: "AÑADIR" o "EDITAR" 
	 */
	private void borrarNotificacion(String ventana) {
	    JList<String> listaNotificaciones;
	    DefaultListModel<String> listModelNotificacion;
	    JButton btnVerNotificaciones;
	    JDialog ventanaVerNotificacion;

	    if (ventana.equals("AÑADIR")) {
	        listaNotificaciones = ventanaAddEvento.getListaNotificaciones();
	        listModelNotificacion = ventanaAddEvento.getListModelNotificacion();
	        btnVerNotificaciones = ventanaAddEvento.getBtnVerNotificaciones();
	        ventanaVerNotificacion = ventanaAddEvento.getVentanaVerNotificacion();
	    } else if (ventana.equals("EDITAR")) {
	        listaNotificaciones = ventanaEditarEvento.getListaNotificaciones();
	        listModelNotificacion = ventanaEditarEvento.getListModelNotificacion();
	        btnVerNotificaciones = ventanaEditarEvento.getBtnVerNotificaciones();
	        ventanaVerNotificacion = ventanaEditarEvento.getVentanaVerNotificacion();
	    } else {
	        return; // Manejar el caso en que la ventana no coincida con ninguna opción
	    }

	    int[] indiceSeleccionado = listaNotificaciones.getSelectedIndices();

	    if (indiceSeleccionado.length == 0) {
	        JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un elemento", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    } else {
	        for (int i = indiceSeleccionado.length - 1; i >= 0; i--) {
	            int indice = indiceSeleccionado[i];
	            listModelNotificacion.remove(indice);
	            if(ventana.equals("AÑADIR")) {
	            	ventanaAddEvento.getObjNotificacion().remove(indiceSeleccionado[i]);
	            }
	            else if(ventana.equals("EDITAR")) {
	            	ventanaEditarEvento.getObjNotificacion().remove(indiceSeleccionado[i]);
	            }
	            
	        }
	    }

	    if (listModelNotificacion.isEmpty()) {
	        btnVerNotificaciones.setVisible(false);
	        ventanaVerNotificacion.dispose();
	    }
	}
	/*
	 * Este método obtiene y verifica los datos del formulario de notificación tanto para agregar un evento
	 * como para editarlo. El tipo de formulario se obtiene a través del parámetros <b> ventana </b>.
	 * En caso de ser correctos crea un objeto de tipo Notificacion, le pasa los datos
	 * obtenidos del formulario y lo aÑade a un ArrayList<Notificacion>
	 * @param ventana. Puede ser: "AÑADIR" o "EDITAR" 
	 */
	private void guardarNotificacion(String ventana) {
	    JSpinner spinnerNumero;
	    JComboBox<String> comboUnidad;
	    JComboBox<String> comboTipoNotificacion;

	    if (ventana.equals("AÑADIR")) {
	        spinnerNumero = ventanaAddEvento.getSpinnerNumero();
	        comboUnidad = ventanaAddEvento.getComboUnidad();
	        comboTipoNotificacion = ventanaAddEvento.getComboTipoNotificacion();
	    } else if (ventana.equals("EDITAR")) {
	    	spinnerNumero = ventanaEditarEvento.getSpinnerNumero();
	        comboUnidad = ventanaEditarEvento.getComboUnidad();
	        comboTipoNotificacion = ventanaEditarEvento.getComboTipoNotificacion();
	    } else {
	        return; // Manejar el caso en que la ventana no coincida con ninguna opción
	    }

	    if ((int) spinnerNumero.getValue() > 0) {
	        String tiempoSeleccionado = spinnerNumero.getValue().toString();
	        String unidadSeleccionada = comboUnidad.getSelectedItem().toString().toUpperCase();
	        String tipoNotificacionSeleccionada = comboTipoNotificacion.getSelectedItem().toString().toUpperCase();

	        Notificacion objNotificacion = new Notificacion();
	        objNotificacion.setTiempoAntesEvento(tiempoSeleccionado);

	        switch (unidadSeleccionada) {
	            case "MINUTOS":
	                objNotificacion.setTipoTiempo(TipoTiempo.MINUTOS);
	                break;
	            case "HORAS":
	                objNotificacion.setTipoTiempo(TipoTiempo.HORAS);
	                break;
	            case "DIAS":
	                objNotificacion.setTipoTiempo(TipoTiempo.DIAS);
	                break;
	            case "SEMANAS":
	                objNotificacion.setTipoTiempo(TipoTiempo.SEMANAS);
	                break;
	        }

	        switch (tipoNotificacionSeleccionada) {
	            case "POP UP":
	                objNotificacion.setTipoNotificacion(TipoNotificacion.POPUP);
	                break;
	            case "SMS":
	                objNotificacion.setTipoNotificacion(TipoNotificacion.SMS);
	                break;
	            case "EMAIL":
	                objNotificacion.setTipoNotificacion(TipoNotificacion.CORREO);
	                break;
	        }

	        if(ventana.equals("AÑADIR")) {
	        	ventanaAddEvento.getObjNotificacion().add(objNotificacion);
		        ventanaAddEvento.getVentanaAddNotificacion().dispose();
		        if (ventanaAddEvento.getBtnVerNotificaciones()!=null) {
		            ventanaAddEvento.getBtnVerNotificaciones().setVisible(true);
		        }
	        }
	        else if(ventana.equals("EDITAR")) {
	        	ventanaEditarEvento.getObjNotificacion().add(objNotificacion);
		        ventanaEditarEvento.getVentanaAddNotificacion().dispose();
		        if(!ventanaEditarEvento.getBtnVerNotificaciones().isVisible()) {
		            ventanaEditarEvento.getBtnVerNotificaciones().setVisible(true);
		        }
	        }
	        


	    } else {
	        JOptionPane.showMessageDialog(null, "El número de MINUTOS/HORAS/DIAS/SEMANAS debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	/*
	 * Este método obtiene el nombre y apellidos de los objetos de tipo ContactoPersonal 
	 * almacenados en la clase DBAgenda  que se guardan ambos en un String, 
	 * para agregarlo a un ArrayList de tipo String, del cual obtendremos los nombre y apellidos
	 * de los contactos personales para usarlos en otros métodos del controlador.
	 */
	public void cargarContactosPersonales() {
		if(sglContadorListaCP<1) {
			for(Contacto contacto : DBAgenda.getListaContactos()) {
				if(contacto instanceof ContactoPersonal) {
					String nombre = contacto.getNombre();
					String apellido = contacto.getApellidos();
					String nomYape = nombre + " " + apellido;
					listaContactosP.add(nomYape);
				}
			}
			sglContadorListaCP++;
		}	
	}
	
	/*
	 * Iniciliza las opciones de contactos personales en los formularios de agregar o editar evento. 
	 * El tipoEl tipo de formulario se obtiene a través del parámetros <b> ventana </b>.
	 * Los datos se obtienen a partir del ArrayList del controlador donde se han guardado los nombres 
	 * y apellidos de los contactos personales.
	 * @param ventana. Puede ser: "AÑADIR" o "EDITAR" 
	 */
	public void setContactosPersonales(String ventana, String opc) {
		if(ventana.equals("AÑADIR")) {
			DefaultComboBoxModel<String> modeloComboBox = new DefaultComboBoxModel<>(listaContactosP.toArray(new String[0]));
			if(opc.equals("CUMPLE")) {
				ventanaAddEvento.getCbContactoCumple().setModel(modeloComboBox);
			}
			else if(opc.equals("ANIVERSARIO")) {
				ventanaAddEvento.getCbContactoAniversario().setModel(modeloComboBox);
			}
		}
		else if(ventana.equals("EDITAR")) {
			DefaultComboBoxModel<String> modeloComboBox = new DefaultComboBoxModel<>(listaContactosP.toArray(new String[0]));
			if(opc.equals("CUMPLE")) {
				ventanaEditarEvento.getCbContactoCumple().setModel(modeloComboBox);
			}
			else if(opc.equals("ANIVERSARIO")) {
				ventanaEditarEvento.getCbContactoAniversario().setModel(modeloComboBox);
			}
		}
	}
	/*
	 * Iniciliza las opciones de contactos de reunion en los formularios de agregar o editar evento. 
	 * El tipo de formulario se obtiene a través del parámetros <b> ventana </b>.
	 * Los datos se obtienen a partir de los atributos nombre y apellido de los objetos de tipo Contacto
	 * almacenados en la clase DBAgenda, que se guardan como un único String para aÑadirlo al
	 * modelo de lista del formulario.
	 * @param ventana. Puede ser: "AÑADIR" o "EDITAR" 
	 */
	public void cargarContactosReunion(String ventana) {
	    DefaultListModel<String> listaModel;

	    if (ventana.equals("AÑADIR")) {
	        listaModel = ventanaAddEvento.getListModel();
	    } else if (ventana.equals("EDITAR")) {
	        listaModel = ventanaEditarEvento.getListModel();
	    } else {
	        return; // Manejar el caso en que la ventana no coincida con ninguna opción
	    }

	    if (listaModel.isEmpty()) {
	        for (Contacto contacto : DBAgenda.getListaContactos()) {
	            String nombre = contacto.getNombre();
	            String apellido = contacto.getApellidos();
	            String nomYape = nombre + " " + apellido;
	            listaModel.addElement(nomYape);
	        }
	    }
	}
	/*
	 * Este método se encarga de comprobar que contactos han sido seleccionados en la ventana de
	 * diálogo del formulario de agregado o edición, y agregarlos a otro arraylist.
	 * El tipo de formulario se obtiene a través del parámetros <b> ventana </b>. 
	 * En caso de que no se seleccione ningún contacto, se le mostrará una ventana emergente 
	 * al usuario con un aviso o error.
	 * @param ventana. Puede ser: "AÑADIR" o "EDITAR" 
	 */
	private void aÑadirContactosReunion(String ventana) {
	    JList<String> listaCP;
	    DefaultListModel<String> listModelContactosReunion;
	    JButton btnVerContactoReunion;
	    JDialog ventanaAddCP;

	    if (ventana.equals("AÑADIR")) {
	        listaCP = ventanaAddEvento.getListaCP();
	        listModelContactosReunion = ventanaAddEvento.getListModelContactosReunion();
	        btnVerContactoReunion = ventanaAddEvento.getBtnVerContactoReunion();
	        ventanaAddCP = ventanaAddEvento.getVentanaAddCP();
	    } else if (ventana.equals("EDITAR")) {
	        listaCP = ventanaEditarEvento.getListaCP();
	        listModelContactosReunion = ventanaEditarEvento.getListModelContactosReunion();
	        btnVerContactoReunion = ventanaEditarEvento.getBtnVerContactoReunion();
	        ventanaAddCP = ventanaEditarEvento.getVentanaAddCP();
	    } else {
	        return; // Manejar el caso en que la ventana no coincida con ninguna opción
	    }

	    String[] seleccionados = listaCP.getSelectedValuesList().toArray(new String[0]);

	    if (seleccionados.length == 0) {
	        JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un elemento", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    } else {
	        for (String seleccionado : seleccionados) {
	            listModelContactosReunion.addElement(seleccionado);
	        }
	        ventanaAddCP.dispose();
	        if (!btnVerContactoReunion.isVisible()) {
	            btnVerContactoReunion.setVisible(true);
	        }
	    }
	}
	/**
	 * Este método se encarga de borrar los contactos seleccionados en la ventana de diálogo 
	 * del formulario de agregado o edición.
	 * El tipo de formulario se obtiene a través del parámetro <b>ventana</b>.
	 * En caso de que no se seleccione ningún contacto, se mostrará una ventana emergente 
	 * al usuario con un aviso o error.
	 * @param ventana Puede ser: "AÑADIR" o "EDITAR"
	 */
	private void borrarContactosReunion(String ventana) {
	    JList<String> listaContactosReunion;
	    DefaultListModel<String> listModelContactosReunion;
	    JButton btnVerContactoReunion;
	    JDialog ventanaVerCP;

	    if (ventana.equals("AÑADIR")) {
	        listaContactosReunion = ventanaAddEvento.getListaContactosReunion();
	        listModelContactosReunion = ventanaAddEvento.getListModelContactosReunion();
	        btnVerContactoReunion = ventanaAddEvento.getBtnVerContactoReunion();
	        ventanaVerCP = ventanaAddEvento.getVentanaVerCP();
	    } else if (ventana.equals("EDITAR")) {
	        listaContactosReunion = ventanaEditarEvento.getListaContactosReunion();
	        listModelContactosReunion = ventanaEditarEvento.getListModelContactosReunion();
	        btnVerContactoReunion = ventanaEditarEvento.getBtnVerContactoReunion();
	        ventanaVerCP = ventanaEditarEvento.getVentanaVerCP();
	    } else {
	        return; // Manejar el caso en que la ventana no coincida con ninguna opción
	    }

	    int[] indicesSeleccionados = listaContactosReunion.getSelectedIndices();

	    if (indicesSeleccionados.length == 0) {
	        JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un elemento", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    } else {
	        for (int i = indicesSeleccionados.length - 1; i >= 0; i--) {
	            listModelContactosReunion.remove(indicesSeleccionados[i]);
	        }
	    }

	    if (listModelContactosReunion.isEmpty()) {
	        btnVerContactoReunion.setVisible(false);
	        ventanaVerCP.dispose();
	    }
	}
	/**
	 * Este método se encarga de cerrar los paneles correspondientes en función del panel actual
	 *  y la opción actual en la ventana de diálogo del formulario de agregado o edición.
	 * El tipo de formulario se obtiene a través del parámetro <b>ventana</b>.
	 * @param panelActual. El panel actual que se va a cerrar. Puede ser: "tipoEvento", "tipoCita", "tipoReunion" o "todos".
	 * @param opcActual. La opción actual seleccionada. Puede ser: "reunion", "cita", "cumple", "aniversario", "medico", "presencial", "llamada" o "video".
	 * @param ventana. Puede ser: "AÑADIR" o "EDITAR".
	 */
	public void cerrarPaneles(String panelActual, String opcActual, String ventana) {
	    JPanel panelTipoEvento;
	    JPanel panelTipoCita;
	    JPanel panelTipoReunion;
	    JPanel panelCitaCumple;
	    JPanel panelCitaAniversario;
	    JPanel panelCitaMedico;
	    JPanel panelReunionPresencial;
	    JPanel panelReunionLlamada;
	    JPanel panelReunionVideo;
	    JTextField txtOtraVideoConferencia;
	    JPanel panelReunionContactos;
	    JButton btnBorrarTipoEvento;
	    JButton btnAgregarTipoEvento;

	    if (ventana.equals("AÑADIR")) {
	        panelTipoEvento = ventanaAddEvento.getPanelTipoEvento();
	        panelTipoCita = ventanaAddEvento.getPanelTipoCita();
	        panelTipoReunion = ventanaAddEvento.getPanelTipoReunion();
	        panelCitaCumple = ventanaAddEvento.getPanelCitaCumple();
	        panelCitaAniversario = ventanaAddEvento.getPanelCitaAniversario();
	        panelCitaMedico = ventanaAddEvento.getPanelCitaMedico();
	        panelReunionPresencial = ventanaAddEvento.getPanelReunionPresencial();
	        panelReunionLlamada = ventanaAddEvento.getPanelReunionLlamada();
	        panelReunionVideo = ventanaAddEvento.getPanelReunionVideo();
	        txtOtraVideoConferencia = ventanaAddEvento.getTxtOtraVideoConferencia();
	        panelReunionContactos = ventanaAddEvento.getPanelReunionContactos();
	        btnBorrarTipoEvento = ventanaAddEvento.getBtnBorrarTipoEvento();
	        btnAgregarTipoEvento = ventanaAddEvento.getBtnAgregarTipoEvento();
	    } else if (ventana.equals("EDITAR")) {
	    	panelTipoEvento = ventanaEditarEvento.getPanelTipoEvento();
	        panelTipoCita = ventanaEditarEvento.getPanelTipoCita();
	        panelTipoReunion = ventanaEditarEvento.getPanelTipoReunion();
	        panelCitaCumple = ventanaEditarEvento.getPanelCitaCumple();
	        panelCitaAniversario = ventanaEditarEvento.getPanelCitaAniversario();
	        panelCitaMedico = ventanaEditarEvento.getPanelCitaMedico();
	        panelReunionPresencial = ventanaEditarEvento.getPanelReunionPresencial();
	        panelReunionLlamada = ventanaEditarEvento.getPanelReunionLlamada();
	        panelReunionVideo = ventanaEditarEvento.getPanelReunionVideo();
	        txtOtraVideoConferencia = ventanaEditarEvento.getTxtOtraVideoConferencia();
	        panelReunionContactos = ventanaEditarEvento.getPanelReunionContactos();
	        btnBorrarTipoEvento = ventanaEditarEvento.getBtnBorrarTipoEvento();
	        btnAgregarTipoEvento = ventanaEditarEvento.getBtnAgregarTipoEvento();
	    } else {
	        return; // Manejar el caso en que la ventana no coincida con ninguna opción
	    }

	    if (panelActual.equals("tipoEvento")) {
	        if (opcActual.equals("reunion")) {
	            panelCitaCumple.setVisible(false);
	            panelCitaAniversario.setVisible(false);
	            panelCitaMedico.setVisible(false);
	            panelTipoCita.setVisible(false);
	        } else if (opcActual.equals("cita")) {
	            panelReunionLlamada.setVisible(false);
	            panelReunionPresencial.setVisible(false);
	            panelReunionVideo.setVisible(false);
	            panelReunionContactos.setVisible(false);
	            txtOtraVideoConferencia.setVisible(false);
	            panelTipoReunion.setVisible(false);
	        }
	    } else if (panelActual.equals("tipoCita")) {
	        if (opcActual.equals("cumple")) {
	            panelCitaAniversario.setVisible(false);
	            panelCitaMedico.setVisible(false);
	        } else if (opcActual.equals("aniversario")) {
	            panelCitaCumple.setVisible(false);
	            panelCitaMedico.setVisible(false);
	        } else if (opcActual.equals("medico")) {
	            panelCitaCumple.setVisible(false);
	            panelCitaAniversario.setVisible(false);
	        }
	    } else if (panelActual.equals("tipoReunion")) {
	        if (opcActual.equals("presencial")) {
	            panelReunionVideo.setVisible(false);
	            panelReunionLlamada.setVisible(false);
	        } else if (opcActual.equals("llamada")) {
	            panelReunionPresencial.setVisible(false);
	            panelReunionVideo.setVisible(false);
	        } else if (opcActual.equals("video")) {
	            panelReunionLlamada.setVisible(false);
	            panelReunionPresencial.setVisible(false);
	        }
	    } else if (panelActual.equals("todos")) {
	        panelTipoEvento.setVisible(false);
	        panelTipoCita.setVisible(false);
	        panelTipoReunion.setVisible(false);
	        panelCitaCumple.setVisible(false);
	        panelCitaAniversario.setVisible(false);
	        panelCitaMedico.setVisible(false);
	        panelReunionPresencial.setVisible(false);
	        panelReunionLlamada.setVisible(false);
	        panelReunionVideo.setVisible(false);
	        txtOtraVideoConferencia.setVisible(false);
	        panelReunionContactos.setVisible(false);
	        btnBorrarTipoEvento.setVisible(false);
	        btnAgregarTipoEvento.setVisible(true);
	    }
	}

	//constructores para ventanas evento
	/**
	 * Constructor de la clase EventoController que recibe una instancia de VentanaEvento y VentanasController.
	 * @param ve La instancia de VentanaEvento.
	 * @param vc La instancia de VentanasController.
	 */
	public EventoController(VentanaEvento ve, VentanasController vc) {
		this.ventanaEvento = ve;
		this.vc = vc;
	}
	/**
	 * Constructor de la clase EventoController que recibe una instancia de VentanaAddEvento y VentanasController.
	 * @param vae La instancia de VentanaAddEvento.
	 * @param vc La instancia de VentanasController.
	 */
	public EventoController(VentanaAddEvento vae, VentanasController vc) {
		ventanaAddEvento = vae;
		this.vc = vc;
	}
	/**
	 * Constructor de la clase EventoController que recibe una instancia de VentanaEditarEvento y VentanasController.
	 * @param vev La instancia de VentanaEditarEvento.
	 * @param vc La instancia de VentanasController.
	 */
	public EventoController(VentanaEditarEvento vev, VentanasController vc) {
		ventanaEditarEvento = vev;
		this.vc = vc;
	}
	//METODOS PARA LA VENTANA EVENTOS
 	public void limpiarTabla() {
 	
		Component[] componentes = ventanaEvento.getPanelRegistros().getComponents();
		for (int i = 6; i < componentes.length; i++) {
			//System.out.println(componentes[i]);
			ventanaEvento.getPanelRegistros().remove(componentes[i]);
		}
		ventanaEvento.getPanelRegistros().revalidate();
		ventanaEvento.getPanelRegistros().repaint();
	}


	public void eliminarFila() {
		Component[] componentes = ventanaEvento.getPanelRegistros().getComponents();
		int contador=0;
		for (int i = 6; i < componentes.length; i+=6) {
			if(componentes[i] instanceof JCheckBox) {
				JCheckBox cb = (JCheckBox) componentes[i];
				if(cb.isSelected()) {
					contador++;
					for(int j=i;j<i+6;j++) {
						//LLAMAR AL METODO "BORAR_REGISTRO" DE DBAgenda pasandole el numero de movil
						DBAgenda.removeEvento(Integer.parseInt(cb.getName()));
						//BORRAMOS COMPONENTES DE LA TABLA
						ventanaEvento.getPanelRegistros().remove(componentes[j]);
					}
				}
			}
		}
		if(contador==0) {
			JOptionPane.showMessageDialog(null, "NO HAY CONTACTOS SELECCIONADOS.\nSELECCIONE UNO O VARIOS CONTACTOS PARA ELIMINAR", "AVISO: ELIMINAR", JOptionPane.WARNING_MESSAGE);
            return;
		}
		else {
			ventanaEvento.getPanelRegistros().revalidate();
			ventanaEvento.getPanelRegistros().repaint();
		}
	}
	
	@Override
	public void resultadoBusqueda() {	
		//NO SE IMPLEMENTA EN ESTA VENTANA
	}
	@Override
	public void ordenarListaAux() {
		//Ordena la lista de contactos por nombre
        Collections.sort(listaAux, new Comparator<Evento>() {
            @Override
            public int compare(Evento evento1, Evento evento2) {
                return evento1.getId()>evento2.getId() ? 1 
                		: evento1.getId()<evento2.getId() ? -1 : 0;
            }
        });
	}
	@Override
	public void verTabla(String opc) {
		ordenarListaAux();
		
		if(listaAux.size()>0) {
			int numFila=1;
            for (int i = 0; i < listaAux.size(); i++) {
                     	
                Evento evento = listaAux.get(i);
               
            	JCheckBox checkBox = new JCheckBox();
                JLabel titulo = new JLabel();
                JLabel tipo = new JLabel("");
                JLabel fechaHora = new JLabel("");
                JLabel notificar = new JLabel("null");
                JButton editar = new JButton("Editar");
                editar.setName(Integer.toString(evento.getId()));
                editar.addActionListener(this);
                //inicializar campos comunes
                checkBox.setName(Integer.toString(evento.getId()));
                checkBox.setOpaque(false);
                checkBox.setContentAreaFilled(false);
                titulo.setText(evento.getTitulo());
               
                String fecha = evento.getFechaEvento();
                String horaDesde = evento.getHoraDesde();
                fechaHora.setText(fecha+" / "+horaDesde);
                notificar.setText(evento.getNotificar());
                // Verifica el tipo de objeto y accede a los atributos específicos
                if (evento instanceof Cita) {
                    // Accede a los atributos específicos de ContactoPersonal
                    tipo.setText("CITA");                
                   
                } else if (evento instanceof Reunion) {
                 // Accede a los atributos específicos de ContactoProfesional
                    tipo.setText("REUNION");
                } 
                else {
                	tipo.setText("EVENTO");
                }
                //llamamos a la funcion y le pasamos los campos
                if(opc.equals("CITA") && !(tipo.getText().isBlank()) && tipo.getText().toUpperCase().equals("CITA")) {
                	VentanaEvento.pintaRegistrosEvento(numFila, checkBox, titulo, tipo, notificar, fechaHora,editar);
                	numFila++;
                }
                else if(opc.equals("REUNION") && !(tipo.getText().isBlank()) && tipo.getText().toUpperCase().equals("REUNION")) {
                	VentanaEvento.pintaRegistrosEvento(numFila, checkBox, titulo, tipo, notificar, fechaHora,editar);
                	numFila++;
                }
                else if(opc.equals("TODOS")) {
                	VentanaEvento.pintaRegistrosEvento(numFila, checkBox, titulo, tipo, notificar, fechaHora,editar);
                	numFila++;
                }       
            }

		}
	}
	@Override
	public boolean verificarDatos() {	
		
		
		if(ventanaAddEvento.getBtnAddNotificacion().isVisible() && ventanaAddEvento.getObjNotificacion().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Notificaciones no aÑadidas", "Error", JOptionPane.ERROR_MESSAGE);
		        return false;
		}
		else {
			try {

			    // Código para verificar datos genéricos
				//DATOS QUE NECESITAN SER CONVERTIDOS A STRING
			    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
			    
			    // FECHA INICIO EVENTO
			    Date fechaInicSeleccionada = ventanaAddEvento.getInputFechaDesde().getDate();
			    String fechaInicFormateada = sdf.format(fechaInicSeleccionada);
			    
			    // FECHA FIN EVENTO
			    Date fechaFinSeleccionada = ventanaAddEvento.getInputFechaHasta().getDate();
			    String fechaFinFormateada = sdf.format(fechaFinSeleccionada);
			    
			    // DESDE HORA
			    SpinnerDateModel tModelDesdeHora = (SpinnerDateModel) ventanaAddEvento.getInputDesdeHora().getModel();
			    Date selectedDesdeHora = tModelDesdeHora.getDate();
			    String horaDesde = dateFormat.format(selectedDesdeHora);
			    
			    // HASTA HORA
			    SpinnerDateModel tModelHastaHora = (SpinnerDateModel) ventanaAddEvento.getInputHastaHora().getModel();
			    Date selectedHastaHora = tModelHastaHora.getDate();
			    String horaHasta = dateFormat.format(selectedHastaHora);

			    String titulo = ventanaAddEvento.getTxtFieldTituloEvento().getText().trim();
			    String descripcion = ventanaAddEvento.getTxtAreaDescripcion().getText().trim();
			    ArrayList<Notificacion> notificaciones = null;

			    String notificar = (String) ventanaAddEvento.getCbNotificarSiNo().getSelectedItem();
			    if (notificar.equals("SI")) {
			        notificaciones = ventanaAddEvento.getObjNotificacion();
			    }
			    int id = DBAgenda.getListaEventos().size() + 1;
			    Evento evento = new Evento(id,titulo,descripcion,fechaInicFormateada,horaDesde,fechaFinFormateada,
			    							horaHasta,notificar,notificaciones);
			    
				//DETALLES EVENTO
				if(ventanaAddEvento.getPanelTipoEvento().isVisible()) {
					if(ventanaAddEvento.getPanelTipoCita().isVisible()) {
						Cita eventoCita;
						String tipoCita = ventanaAddEvento.getOpcTipoCita();	
						String fechaFormateada = null;
						String cp = null;
						String ubi = null;
						String tipoEspecialidad = null;
						if(ventanaAddEvento.getPanelCitaCumple().isVisible()) {		
							cp = ventanaAddEvento.getOpcContactoCumple();
							eventoCita = new Cita(evento,tipoCita,cp,fechaFormateada,ubi,tipoEspecialidad);				
							DBAgenda.addEvento(eventoCita);
						}
						else if(ventanaAddEvento.getPanelCitaAniversario().isVisible()) {
							cp = ventanaAddEvento.getOpcContactoAniversario();
						    Date fechaCumpleSeleccionada = ventanaAddEvento.getInputCitaFechaAniversario().getDate();
						    fechaFormateada = sdf.format(fechaCumpleSeleccionada);
							eventoCita = new Cita(evento,tipoCita,cp,fechaFormateada,ubi,tipoEspecialidad);		
							DBAgenda.addEvento(eventoCita);
						}
						else if(ventanaAddEvento.getPanelCitaMedico().isVisible()) {
							ubi = ventanaAddEvento.getTxtUbicacionMed().getText().trim();
							tipoEspecialidad = ventanaAddEvento.getOpcEspecialidadMed();
							eventoCita = new Cita(evento,tipoCita,cp,fechaFormateada,ubi,tipoEspecialidad);		
							DBAgenda.addEvento(eventoCita);
						}
						else {//ningun panel esta visible, mandar mensaje de advertencia
							JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos del tipo de evento", "Error", JOptionPane.ERROR_MESSAGE);
					        return false;
						}
					}
					else if(ventanaAddEvento.getPanelTipoReunion().isVisible()) {
						Reunion eventoReunion;
						String tipoReunion = ventanaAddEvento.getOpcTipoReunion();
						ArrayList<String> listaContactos = new ArrayList<>();
						for (int i=0;i<ventanaAddEvento.getListModelContactosReunion().getSize();i++) {
							String contacto = ventanaAddEvento.getListModelContactosReunion().getElementAt(i);
							listaContactos.add(contacto);
						}		
						String ubi = null;
						String sala = null;
						String telCorto = null;
						String telLargo = null;
						String clave = null;
						String tipoVideo = null;
						String otroTipo = null;
						if(ventanaAddEvento.getPanelReunionPresencial().isVisible()) {
							ubi = ventanaAddEvento.getTxtUbicacionReunion().getText().trim();
							sala = ventanaAddEvento.getOpcSalaReunion();
							eventoReunion = new Reunion(evento,tipoReunion,listaContactos,ubi,sala,telCorto,telLargo,clave,tipoVideo,otroTipo);
							DBAgenda.addEvento(eventoReunion);
						}
						else if(ventanaAddEvento.getPanelReunionLlamada().isVisible()) {
							telCorto = ventanaAddEvento.getTxtTelefonoCorto().getText().trim();
							telLargo = ventanaAddEvento.getTxtTelefonoLargo().getText().trim();
							clave = ventanaAddEvento.getTxtClaveAcceso().getText().trim();
							eventoReunion = new Reunion(evento,tipoReunion,listaContactos,ubi,sala,telCorto,telLargo,clave,tipoVideo,otroTipo);
							DBAgenda.addEvento(eventoReunion);
						}
						else if(ventanaAddEvento.getPanelReunionVideo().isVisible()) {
							if(ventanaAddEvento.getOpcTipoVideoConferencia().equals("Otra: Especificar")) {
								if(ventanaAddEvento.getTxtOtraVideoConferencia().getText().isEmpty()) {
									 JOptionPane.showMessageDialog(null, "Debe especificar el otro tipo de videoconferencia", "Error", JOptionPane.ERROR_MESSAGE);
							         return false;
								}
								else {
									otroTipo = ventanaAddEvento.getTxtOtraVideoConferencia().getText().trim();
								}
								
							}
							else {
								tipoVideo = ventanaAddEvento.getOpcTipoVideoConferencia().trim();
							}
							eventoReunion = new Reunion(evento,tipoReunion,listaContactos,ubi,sala,telCorto,telLargo,clave,tipoVideo,otroTipo);
							DBAgenda.addEvento(eventoReunion);
						}
						else {//opcional
							JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos del tipo de evento", "Error", JOptionPane.ERROR_MESSAGE);
					        return false;
						}
					}
					
				}else {
					DBAgenda.addEvento(evento);
				}
			} catch (Exception e) {
			    // Manejo de excepciones
				JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos obligatorios (*)", "Error", JOptionPane.ERROR_MESSAGE);
		        return false;
			    // Aquí puedes realizar alguna acción en caso de que se produzca una excepción.
			    // Por ejemplo, mostrar un mensaje de error o registrar el evento en un log.
			}	
		}
		return true;
	}
	
	@Override
	public boolean verificarDatosEditar() {

		if(ventanaEditarEvento.getBtnAddNotificacion().isVisible() && ventanaEditarEvento.getObjNotificacion().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Notificaciones no aÑadidas", "Error", JOptionPane.ERROR_MESSAGE);
		        return false;
		}
		else {
			try {
				int id = Integer.parseInt(ventanaEditarEvento.getBtnActualizarEvento().getName());
				
			    // Código para verificar datos genéricos
				//DATOS QUE NECESITAN SER CONVERTIDOS A STRING
			    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
			    
			    // FECHA INICIO EVENTO
			    Date fechaInicSeleccionada = ventanaEditarEvento.getInputFechaDesde().getDate();
			    String fechaInicFormateada = sdf.format(fechaInicSeleccionada);
			    
			    // FECHA FIN EVENTO
			    Date fechaFinSeleccionada = ventanaEditarEvento.getInputFechaHasta().getDate();
			    String fechaFinFormateada = sdf.format(fechaFinSeleccionada);
			    
			    // DESDE HORA
			    SpinnerDateModel tModelDesdeHora = (SpinnerDateModel) ventanaEditarEvento.getInputDesdeHora().getModel();
			    Date selectedDesdeHora = tModelDesdeHora.getDate();
			    String horaDesde = dateFormat.format(selectedDesdeHora);
			    
			    // HASTA HORA
			    SpinnerDateModel tModelHastaHora = (SpinnerDateModel) ventanaEditarEvento.getInputHastaHora().getModel();
			    Date selectedHastaHora = tModelHastaHora.getDate();
			    String horaHasta = dateFormat.format(selectedHastaHora);

			    String titulo = ventanaEditarEvento.getTxtFieldTituloEvento().getText().trim();
			    String descripcion = ventanaEditarEvento.getTxtAreaDescripcion().getText().trim();
			    ArrayList<Notificacion> notificaciones = null;

			    String notificar = (String) ventanaEditarEvento.getCbNotificarSiNo().getSelectedItem();
			    if (notificar.equals("SI")) {
			        notificaciones = ventanaEditarEvento.getObjNotificacion();
			    }
			    //DBAgenda.getListaEventos().size() + 1;
			    Evento evento = new Evento(id,titulo,descripcion,fechaInicFormateada,horaDesde,fechaFinFormateada,
			    							horaHasta,notificar,notificaciones);
			    
				//DETALLES EVENTO
				if(ventanaEditarEvento.getPanelTipoEvento().isVisible()) {
					if(ventanaEditarEvento.getPanelTipoCita().isVisible()) {
						Cita eventoCita;
						String tipoCita = ventanaEditarEvento.getOpcTipoCita();	
						String fechaFormateada = null;
						String cp = null;
						String ubi = null;
						String tipoEspecialidad = null;
						if(ventanaEditarEvento.getPanelCitaCumple().isVisible()) {		
							cp = ventanaEditarEvento.getOpcContactoCumple();
							eventoCita = new Cita(evento,tipoCita,cp,fechaFormateada,ubi,tipoEspecialidad);	
							DBAgenda.removeEvento(id);
							DBAgenda.addEvento(eventoCita);
						}
						else if(ventanaEditarEvento.getPanelCitaAniversario().isVisible()) {
							cp = ventanaEditarEvento.getOpcContactoAniversario();
						    Date fechaCumpleSeleccionada = ventanaEditarEvento.getInputCitaFechaAniversario().getDate();
						    fechaFormateada = sdf.format(fechaCumpleSeleccionada);
							eventoCita = new Cita(evento,tipoCita,cp,fechaFormateada,ubi,tipoEspecialidad);		
							DBAgenda.removeEvento(id);
							DBAgenda.addEvento(eventoCita);
						}
						else if(ventanaEditarEvento.getPanelCitaMedico().isVisible()) {
							ubi = ventanaEditarEvento.getTxtUbicacionMed().getText().trim();
							tipoEspecialidad = ventanaEditarEvento.getOpcEspecialidadMed();
							eventoCita = new Cita(evento,tipoCita,cp,fechaFormateada,ubi,tipoEspecialidad);	
							DBAgenda.removeEvento(id);
							DBAgenda.addEvento(eventoCita);
						}
						else {//ningun panel esta visible, mandar mensaje de advertencia
							JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos del tipo de evento", "Error", JOptionPane.ERROR_MESSAGE);
					        return false;
						}
					}
					else if(ventanaEditarEvento.getPanelTipoReunion().isVisible()) {
						Reunion eventoReunion;
						String tipoReunion = ventanaEditarEvento.getOpcTipoReunion();
						ArrayList<String> listaContactos = new ArrayList<>();
						for (int i=0;i<ventanaEditarEvento.getListModelContactosReunion().getSize();i++) {
							String contacto = ventanaEditarEvento.getListModelContactosReunion().getElementAt(i);
							listaContactos.add(contacto);
						}		
						String ubi = null;
						String sala = null;
						String telCorto = null;
						String telLargo = null;
						String clave = null;
						String tipoVideo = null;
						String otroTipo = null;
						if(ventanaEditarEvento.getPanelReunionPresencial().isVisible()) {
							ubi = ventanaEditarEvento.getTxtUbicacionReunion().getText().trim();
							sala = ventanaEditarEvento.getOpcSalaReunion();
							eventoReunion = new Reunion(evento,tipoReunion,listaContactos,ubi,sala,telCorto,telLargo,clave,tipoVideo,otroTipo);
							DBAgenda.removeEvento(id);
							DBAgenda.addEvento(eventoReunion);
						}
						else if(ventanaEditarEvento.getPanelReunionLlamada().isVisible()) {
							telCorto = ventanaEditarEvento.getTxtTelefonoCorto().getText().trim();
							telLargo = ventanaEditarEvento.getTxtTelefonoLargo().getText().trim();
							clave = ventanaEditarEvento.getTxtClaveAcceso().getText().trim();
							eventoReunion = new Reunion(evento,tipoReunion,listaContactos,ubi,sala,telCorto,telLargo,clave,tipoVideo,otroTipo);
							DBAgenda.removeEvento(id);
							DBAgenda.addEvento(eventoReunion);
						}
						else if(ventanaEditarEvento.getPanelReunionVideo().isVisible()) {
							if(ventanaEditarEvento.getOpcTipoVideoConferencia().equals("Otra: Especificar")) {
								if(ventanaEditarEvento.getTxtOtraVideoConferencia().getText().isEmpty()) {
									 JOptionPane.showMessageDialog(null, "Debe especificar el otro tipo de videoconferencia", "Error", JOptionPane.ERROR_MESSAGE);
							         return false;
								}
								else {
									otroTipo = ventanaEditarEvento.getTxtOtraVideoConferencia().getText().trim();
								}
								
							}
							else {
								tipoVideo = ventanaEditarEvento.getOpcTipoVideoConferencia().trim();
							}
							eventoReunion = new Reunion(evento,tipoReunion,listaContactos,ubi,sala,telCorto,telLargo,clave,tipoVideo,otroTipo);
							DBAgenda.removeEvento(id);
							DBAgenda.addEvento(eventoReunion);
						}
						else {//opcional
							JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos del tipo de evento", "Error", JOptionPane.ERROR_MESSAGE);
					        return false;
						}
					}
					
				}else {
					DBAgenda.removeEvento(id);
					DBAgenda.addEvento(evento);
				}
			} catch (Exception e) {
			    // Manejo de excepciones
				JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos obligatorios (*)", "Error", JOptionPane.ERROR_MESSAGE);
		        return false;
			    // Aquí puedes realizar alguna acción en caso de que se produzca una excepción.
			    // Por ejemplo, mostrar un mensaje de error o registrar el evento en un log.
			}	
		}
		return true;
		
	}
}