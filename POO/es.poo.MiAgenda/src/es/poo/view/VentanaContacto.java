package es.poo.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import es.poo.controller.ContactoController;
import es.poo.controller.VentanasController;
import es.poo.database.DBAgenda;
import es.poo.model.Contacto;
import es.poo.model.ContactoPersonal;
import es.poo.model.ContactoProfesional;

@SuppressWarnings({"serial", "unused" })
public class VentanaContacto extends VentanaBaseCE{
	
	private ContactoController controllerContacto;
	private static ArrayList<JButton>  editarBtn = new ArrayList<>();


	public VentanaContacto(VentanasController ventanaController) {
        super();
        controller = ventanaController;
        controllerContacto = new ContactoController(this,controller);
        lblTitulo.setText("Mis Contactos");
        agregarCampoCabecera("Seleccionar");
		agregarCampoCabecera("Nombre");
		agregarCampoCabecera("Alias");
		agregarCampoCabecera("Empresa");
		agregarCampoCabecera("Teléfono/Móvil");
		agregarCampoCabecera("Editar");
		pintarCamposCabecera();
        controllerContacto.verTabla("TODOS");
        
        btnNuevo.addActionListener(controller);
        btnVolverPrincipal.addActionListener(controller);
        getRdbtnOpcTodos().addActionListener(controllerContacto);
        getRdbtnOpc2().addActionListener(controllerContacto);
        getRdbtnOpc3().addActionListener(controllerContacto);
        getBtnEliminar().addActionListener(controllerContacto);
        getBtnBuscar().addActionListener(controllerContacto);
        getBtnResetBuscador().addActionListener(controllerContacto);
    }
	/**
	 * Este método se encarga de establecer la apariencia de los registros de contactos en el panel de registros.
	 * Configura la fuente, color y estilo de los componentes, así como su posición en el panel.
	 * @param gridY La posición vertical del registro en el panel.
	 * @param cb El checkbox asociado al registro.
	 * @param nombre La etiqueta del nombre del contacto.
	 * @param alias La etiqueta del alias del contacto.
	 * @param empresa La etiqueta de la empresa del contacto.
	 * @param telOmovil La etiqueta del teléfono o móvil del contacto.
	 * @param editar El botón de edición del contacto.
	 */
	public static void pintarRegistrosContacto(int gridY,JCheckBox cb,JLabel nombre,JLabel alias,JLabel empresa,JLabel telOmovil,JButton editar) {
    		
    		nombre.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR,0,13));
    		nombre.setForeground(new Color(255, 255, 255));
    		alias.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR,0,13));
    		alias.setForeground(new Color(255, 255, 255));
    		empresa.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR,0,13));
    		empresa.setForeground(new Color(255, 255, 255));
    		telOmovil.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR,0,13));
    		telOmovil.setForeground(new Color(255, 255, 255));
    		editar.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT,0,13));
    		//editar.setForeground(new Color(204, 204, 204));
    		editar.setBorder(new LineBorder(null, 3, true));
    		editar.setBorderPainted(false);
    		editar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    		editar.setName(telOmovil.getText());
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
	    	
	    	GridBagConstraints gbc_nombre = new GridBagConstraints();
	    	gbc_nombre.insets = new Insets(0,0,5,5);
	    	gbc_nombre.gridx = 1;
	    	gbc_nombre.gridy = gridY;
	    	panelRegistros.add(nombre,gbc_nombre);
	    	
	    	GridBagConstraints gbc_alias = new GridBagConstraints();
	    	gbc_alias.insets = new Insets(0,0,5,5);
	    	gbc_alias.gridx = 2;
	    	gbc_alias.gridy = gridY;
	    	panelRegistros.add(alias,gbc_alias);
	    	
	    	GridBagConstraints gbc_empresa = new GridBagConstraints();
	    	gbc_empresa.insets = new Insets(0,0,5,5);
	    	gbc_empresa.gridx = 3;
	    	gbc_empresa.gridy = gridY;
	    	panelRegistros.add(empresa,gbc_empresa);
	    	
	    	GridBagConstraints gbc_telMovil = new GridBagConstraints();
	    	gbc_telMovil.insets = new Insets(0,0,5,5);
	    	gbc_telMovil.gridx = 4;
	    	gbc_telMovil.gridy = gridY;
	    	panelRegistros.add(telOmovil,gbc_telMovil);
	    	
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
	public JButton getBtnBuscar() {
		return btnBuscar;
	}
    @Override
	public JButton getBtnResetBuscador() {
		return btnResetBuscador;
	}
    @Override
	public JFormattedTextField getTxtBuscador() {
		return txtBuscador;
	}
    @Override 
    public JButton getBtnVolverPrincipal() {
    	return btnVolverPrincipal;
    }
	public ArrayList<JButton> getEditarBtn() {
		return editarBtn;
	}

}
