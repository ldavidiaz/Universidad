package es.poo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import es.poo.view.VentanaAddContacto;
import es.poo.view.VentanaAddEvento;
import es.poo.view.VentanaContacto;
import es.poo.view.VentanaEditarContacto;
import es.poo.view.VentanaEditarEvento;
import es.poo.view.VentanaEvento;
import es.poo.view.VentanaPrincipal;


/*
 * Esta clase controla el flujo entre ventanas
 */
public class VentanasController implements ActionListener {
    private VentanaPrincipal ventanaPrincipal;
    
    private VentanaContacto ventanaContacto;
    private VentanaEditarContacto ventanaEditarContacto;
    private VentanaAddContacto ventanaAddContacto;
    
    private VentanaEvento ventanaEvento;
    private VentanaAddEvento ventanaAddEvento;
    private VentanaEditarEvento ventanaEditarEvento;
    
    JButton botonClicado;
    ArrayList<JButton> botones;
	
    
    public VentanasController() {
        ventanaPrincipal = new VentanaPrincipal(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == ventanaPrincipal.getBotonMostrarContacto()) {
            mostrarVentanaContacto();
            botones = ventanaContacto.getEditarBtn();
            cerrarVentanaPrincipal();// Cerrar VentanaPrincipal
        } 
        else if (e.getSource() == ventanaPrincipal.getBotonMostrarEvento()) {
            mostrarVentanaEvento();
            //botones = ventanaEvento.getEditarBtn();
            cerrarVentanaPrincipal();
        }//CONTACTO
        else if(ventanaContacto != null && e.getSource() == ventanaContacto.getBtnNuevo()){
        	mostrarVentanaAddContacto();
        	cerrarVentanaContacto();
        }
        else if(ventanaContacto != null && e.getSource() == ventanaContacto.getBtnVolverPrincipal()) {
        	cerrarVentanaContacto();
        	mostrarVentanaPrincipal();
        }
        else if(ventanaEditarContacto !=null && e.getSource() == ventanaEditarContacto.getBtnVolverContacto()) {
        	mostrarVentanaContacto();
        	cerrarVentanaEditarContacto();
        }
        else if(ventanaAddContacto !=null && e.getSource() == ventanaAddContacto.getBtnVolverContacto()) {
        	mostrarVentanaContacto();
        	cerrarVentanaAddContacto();
        }
        else if(ventanaContacto !=null && (e.getSource() instanceof JButton)) {	
        	botonClicado = (JButton) e.getSource();
        	botones = ventanaContacto.getEditarBtn();
            if (botones.contains(botonClicado)) {
            	mostrarVentanaEditarContacto();
            	cerrarVentanaContacto();
            }
        }
        //EVENTO
        //NUEVO
        else if(ventanaEvento != null && e.getSource() == ventanaEvento.getBtnNuevo()) {
        	mostrarVentanaAddEvento();
        	ventanaEvento.dispose();
        }
        else if(ventanaEvento != null && e.getSource() == ventanaEvento.getBtnVolverPrincipal()) {
        	cerrarVentanaEvento();
        	mostrarVentanaPrincipal();
        }
        //EDITAR->VOLVER
        else if(ventanaEditarEvento !=null && e.getSource() == ventanaEditarEvento.getBtnVolverEvento()) {
        	mostrarVentanaEvento();
        	cerrarVentanaEditarEvento();
        }
        //AÑADIR-> VOLVER
        else if(ventanaAddEvento !=null && e.getSource() == ventanaAddEvento.getBtnVolverEvento()){
        	mostrarVentanaEvento();
        	cerrarVentanaAddEvento();
        }
        //EDITAR
        else if(ventanaEvento !=null && (e.getSource() instanceof JButton)) {	
        	botonClicado = (JButton) e.getSource();
        	botones = ventanaEvento.getEditarBtn();
            if (botones.contains(botonClicado)) {
            	mostrarVentanaEditarEvento();
            	cerrarVentanaEvento();
            }
        }
    }

    
	//PRINCIPAL
    /**
     * Este método se utiliza para mostrar la ventana principal de la aplicación.
     * Hace visible la ventana principal y la coloca en el centro de la pantalla.
     */
    protected void mostrarVentanaPrincipal() {
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.setLocationRelativeTo(null);
    }
    /**
     * Este método se utiliza para cerrar la ventana principal de la aplicación.
     * Llama al método dispose() para liberar los recursos de la ventana principal.
     */
    protected void cerrarVentanaPrincipal() {
    	ventanaPrincipal.dispose();
    	//ventanaPrincipal = null;
    }

    //CONTACTO
    /**
     * Este método se utiliza para mostrar la ventana de contactos.
     * Crea una nueva instancia de la ventana de contactos, la hace visible y la coloca en el centro de la pantalla.
     */
	protected void mostrarVentanaContacto() {
        ventanaContacto = new VentanaContacto(this);
        ventanaContacto.setVisible(true);
        ventanaContacto.setLocationRelativeTo(null);
    }
	/**
	 * Este método se utiliza para mostrar la ventana de agregar contacto.
	 * Crea una nueva instancia de la ventana de agregar contacto, la hace visible y la coloca en el centro de la pantalla.
	 */
    private void mostrarVentanaAddContacto() {
		ventanaAddContacto = new VentanaAddContacto(this);
		ventanaAddContacto.setVisible(true);
		ventanaAddContacto.setLocationRelativeTo(null);

	}
    /**
     * Este método se utiliza para mostrar la ventana de editar contacto.
     * Crea una nueva instancia de la ventana de editar contacto, inicializa los datos del contacto a editar
     * y la hace visible en el centro de la pantalla.
     */
    private void mostrarVentanaEditarContacto() {
    	ventanaEditarContacto = new VentanaEditarContacto(this);
    	ventanaEditarContacto.inicDatos(botonClicado.getName());
    	ventanaEditarContacto.setVisible(true);
    	ventanaEditarContacto.setLocationRelativeTo(null);
    }
    /**
     * Este método se utiliza para cerrar la ventana de contactos.
     * Llama al método dispose() para liberar los recursos de la ventana de contactos y establece su referencia a null.
     */
    protected void cerrarVentanaContacto() {
    	ventanaContacto.dispose();
    	ventanaContacto = null;
    }
    /**
     * Este método se utiliza para cerrar la ventana de agregar contacto.
     * Llama al método dispose() para liberar los recursos de la ventana de agregar contacto y establece su referencia a null.
     */

    protected void cerrarVentanaAddContacto() {
    	ventanaAddContacto.dispose();
    	ventanaAddContacto = null;
    }
    /**
     * Este método se utiliza para cerrar la ventana de editar contacto.
     * Llama al método dispose() para liberar los recursos de la ventana de editar contacto y establece su referencia a null.
     */
    protected void cerrarVentanaEditarContacto() {
    	ventanaEditarContacto.dispose();
    	ventanaEditarContacto = null;
    }
   
    //EVENTO
    /**
     * Este método se utiliza para mostrar la ventana de eventos.
     * Crea una nueva instancia de la ventana de eventos, la hace visible y la coloca en el centro de la pantalla.
     */
    protected void mostrarVentanaEvento() {
        ventanaEvento = new VentanaEvento(this);
        ventanaEvento.setVisible(true);
        ventanaEvento.setLocationRelativeTo(null);
    }
    /**
     * Este método se utiliza para mostrar la ventana de agregar evento.
     * Crea una nueva instancia de la ventana de agregar evento, la hace visible y la coloca en el centro de la pantalla.
     */

    private void mostrarVentanaAddEvento() {
    	ventanaAddEvento = new VentanaAddEvento(this);
    	ventanaAddEvento.setVisible(true);
    	ventanaAddEvento.setLocationRelativeTo(null);
    }
    /**
     * Este método se utiliza para cerrar la ventana de editar evento.
     * Llama al método dispose() para liberar los recursos de la ventana de editar evento y establece su referencia a null.
     */
	public void cerrarVentanaEditarEvento() {
		ventanaEditarEvento.dispose();
		ventanaEditarEvento = null;
		
	}
	/**
	 * Este método se utiliza para mostrar la ventana de editar evento.
	 * Crea una nueva instancia de la ventana de editar evento, inicializa los datos del evento a editar
	 * y la hace visible en el centro de la pantalla.
	 */
    private void mostrarVentanaEditarEvento() {
    	ventanaEditarEvento = new VentanaEditarEvento(this);
    	ventanaEditarEvento.inicDatos(botonClicado.getName());
    	ventanaEditarEvento.setVisible(true);
    	ventanaEditarEvento.setLocationRelativeTo(null);
		
	}
    /**
     * Este método se utiliza para cerrar la ventana de agregar evento.
     * Llama al método dispose() para liberar los recursos de la ventana de agregar evento y establece su referencia a null.
     */
	public void cerrarVentanaAddEvento() {
		ventanaAddEvento.dispose();
		ventanaAddEvento = null;
		
	}
	/**
	 * Este método se utiliza para cerrar la ventana de eventos.
	 * Llama al método dispose() para liberar los recursos de la ventana de eventos y establece su referencia a null.
	 */
	private void cerrarVentanaEvento() {
		ventanaEvento.dispose();
		ventanaEvento = null;
	}
}
