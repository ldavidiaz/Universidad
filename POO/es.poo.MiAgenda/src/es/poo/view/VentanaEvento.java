package es.poo.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import es.poo.controller.EventoController;
import es.poo.controller.VentanasController;

@SuppressWarnings("serial")
public class VentanaEvento extends VentanaBaseCE{
	
	private EventoController controllerEvento;
	private static ArrayList<JButton>  editarBtn = new ArrayList<>();
    
	public VentanaEvento(VentanasController vc) {
        super(); // Llamar al constructor de la clase padre
        
        controller = vc;
        controllerEvento = new EventoController(this,controller);
        lblTitulo.setText("Mis Eventos");
        txtBuscador.setVisible(false);
        btnBuscar.setVisible(false);
        rdbtnOpc2.setText("CITA");
        rdbtnOpc2.setSize(80,rdbtnOpc2.getHeight());
        rdbtnOpc3.setText("REUNION");
        rdbtnOpc3.setLocation(300, 41);
        agregarCampoCabecera("Seleccionar");
		agregarCampoCabecera("Titulo");
		agregarCampoCabecera("Tipo");
		agregarCampoCabecera("Notificar");
		agregarCampoCabecera("Fecha/Hora");
		agregarCampoCabecera("Editar");
		pintarCamposCabecera();
		
		getRdbtnOpcTodos().addActionListener(controllerEvento);
        getRdbtnOpc2().addActionListener(controllerEvento);
        getRdbtnOpc3().addActionListener(controllerEvento);
        getBtnEliminar().addActionListener(controllerEvento);
		btnNuevo.addActionListener(controller);
		btnVolverPrincipal.addActionListener(controller);
		controllerEvento.verTabla("TODOS");
    }

	/**
	 * Este método se encarga de establecer la apariencia de los registros de eventos en el panel de registros.
	 * Configura la fuente, color y estilo de los componentes, así como su posición en el panel.
	 * @param gridY La posición vertical del registro en el panel.
	 * @param cb El checkbox asociado al registro.
	 * @param titulo La etiqueta del título del evento.
	 * @param tipo La etiqueta del tipo de evento.
	 * @param notificar La etiqueta de la opción de notificación del evento.
	 * @param fechaHora La etiqueta de la fecha y hora del evento.
	 * @param editar El botón de edición del evento.
	 */
    public static void pintaRegistrosEvento(int gridY,JCheckBox cb,JLabel titulo,JLabel tipo,JLabel notificar,JLabel fechaHora,JButton editar) {
		titulo.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR,0,13));
		titulo.setForeground(new Color(255, 255, 255));
		tipo.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR,0,13));
		tipo.setForeground(new Color(255, 255, 255));
		notificar.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR,0,13));
		notificar.setForeground(new Color(255, 255, 255));
		fechaHora.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR,0,13));
		fechaHora.setForeground(new Color(255, 255, 255));
		editar.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT,0,13));
		editar.setBorder(new LineBorder(null, 3, true));
		editar.setBorderPainted(false);
		editar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		editar.setName(cb.getName());
		//System.out.println(editar.getName());
		editar.addActionListener(controller);
		editarBtn.add(editar);
		
    	cb.setOpaque(false);
    	cb.setContentAreaFilled(false);
    	GridBagConstraints gbc_cb = new GridBagConstraints();
    	gbc_cb.insets = new Insets(0,0,5,5);
    	gbc_cb.gridx = 0;
    	gbc_cb.gridy = gridY;
    	panelRegistros.add(cb,gbc_cb);
    	
    	GridBagConstraints gbc_titulo = new GridBagConstraints();
    	gbc_titulo.insets = new Insets(0,0,5,5);
    	gbc_titulo.gridx = 1;
    	gbc_titulo.gridy = gridY;
    	panelRegistros.add(titulo,gbc_titulo);
    	
    	GridBagConstraints gbc_tipo = new GridBagConstraints();
    	gbc_tipo.insets = new Insets(0,0,5,5);
    	gbc_tipo.gridx = 2;
    	gbc_tipo.gridy = gridY;
    	panelRegistros.add(tipo,gbc_tipo);    
    	
    	GridBagConstraints gbc_notificar = new GridBagConstraints();
    	gbc_notificar.insets = new Insets(0,0,5,5);
    	gbc_notificar.gridx = 3;
    	gbc_notificar.gridy = gridY;
    	panelRegistros.add(notificar,gbc_notificar);
    	
    	GridBagConstraints gbc_fechaHora = new GridBagConstraints();
    	gbc_fechaHora.insets = new Insets(0,0,5,5);
    	gbc_fechaHora.gridx = 4;
    	gbc_fechaHora.gridy = gridY;
    	panelRegistros.add(fechaHora,gbc_fechaHora);
    	
    	GridBagConstraints gbc_editar = new GridBagConstraints();
    	gbc_editar.insets = new Insets(0,0,5,5);
    	gbc_editar.gridx = 5;
    	gbc_editar.gridy = gridY;
    	panelRegistros.add(editar,gbc_editar);   
    }
    @Override
    public JButton getBtnNuevo() {
        return btnNuevo;
    }
    @Override
	public JRadioButton getRdbtnOpcTodos() {
		return rdbtnOpcTodos;
	}
    @Override
	public JRadioButton getRdbtnOpc2() {
		return rdbtnOpc2;
	}
    @Override
	public JRadioButton getRdbtnOpc3() {
		return rdbtnOpc3;
	}
    @Override
	public JButton getBtnEliminar() {
		return btnEliminar;
	}
    @Override 
	public JPanel getPanelRegistros() {
		return panelRegistros;
	}
    @Override 
    public JButton getBtnVolverPrincipal() {
    	return btnVolverPrincipal;
    }
	public ArrayList<JButton> getEditarBtn() {
		return editarBtn;
	}
}
