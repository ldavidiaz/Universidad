package es.poo.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;

import java.awt.Cursor;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import es.poo.controller.VentanasController;
import es.poo.database.DBAgenda;
import es.poo.model.Contacto;
import es.poo.model.ContactoPersonal;
import es.poo.model.ContactoProfesional;
import es.poo.persistencia.PersistenciaContacto;
import fonts.Fuente;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.awt.ComponentOrientation;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.SystemColor;


@SuppressWarnings({ "unused", "serial" })
public abstract class VentanaBaseCE extends JFrame{

	private JPanel contentPane;
	protected JFormattedTextField txtBuscador;


	protected JLabel lblTitulo;
	protected JButton btnNuevo,btnEliminar,btnBuscar,btnResetBuscador,btnVolverPrincipal;
	//protected Map<JButton, String> buttonDataMap;
	



	static VentanasController controller;

	static Fuente tipoFuente;
	protected static ButtonGroup buttonGroup;


	protected JRadioButton rdbtnOpcTodos;
	protected JRadioButton rdbtnOpc2;
	protected JRadioButton rdbtnOpc3;
	protected JRadioButton rdbtnOpc4;
	
	protected static ArrayList<JLabel> camposCabecera;
	protected static JLabel lblCampo;
	private JPanel panelTabla;
	protected static JPanel panelRegistros;

	private JScrollPane scrollPane;
	//protected JSeparator separadorTabla;
	static GridBagLayout gbl_panelRegistros;
	private int numFilasRegistros = 1;//cada vez que se agregue una fila se actualiza, idem para eliminar

	protected static int numFila = DBAgenda.getListaContactos().size();//para el grid de registros. Actualizar cada vez q se habra esta ventana DBAgenda.size();
	protected static int numRegistros = DBAgenda.getListaContactos().size();
	private JTable table;
	
	public VentanaBaseCE() {
		
		
		tipoFuente = new Fuente();
		
		setResizable(false);
		setTitle("MI AGENDA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setPreferredSize(new Dimension(800, 600));
		setContentPane(contentPane);
		/*
		separadorTabla = new JSeparator();
		separadorTabla.setBounds(10, 155, 764, 8);
		contentPane.add(separadorTabla);
        */
		lblTitulo = new JLabel("MI AGENDA");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setBounds(211, 11, 361, 42);
		lblTitulo.setVerticalAlignment(SwingConstants.TOP);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD,1,44));
		lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitulo.setAlignmentY(Component.TOP_ALIGNMENT);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBackground(new Color(0, 102, 0));
		btnNuevo.setForeground(new Color(204, 204, 204));
		btnNuevo.setBounds(320, 500, 65, 25);
		btnNuevo.setBorder(new LineBorder(null, 5, true));
		btnNuevo.setBorderPainted(false);
		btnNuevo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//btnNuevo.addActionListener(controller);
		btnNuevo.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT,0,14));
		
		
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(new Color(204, 204, 204));
		btnEliminar.setBounds(401, 500, 85, 25);
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.setBorderPainted(false);
		btnEliminar.setBorder(new LineBorder(null, 5, true));
		btnEliminar.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT,0,14));
		btnEliminar.setBackground(new Color(153, 0, 0));
		//btnEliminar.addActionListener(controller);
		contentPane.setLayout(null);
		
		btnVolverPrincipal = new JButton("VOLVER");
		btnVolverPrincipal.setForeground(new Color(204, 204, 204));
		btnVolverPrincipal.setFont(new Font("Minecraft Ten", Font.PLAIN, 14));
		btnVolverPrincipal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVolverPrincipal.setBorderPainted(false);
		btnVolverPrincipal.setBorder(new LineBorder(null, 5, true));
		btnVolverPrincipal.setBackground(new Color(128, 128, 128));
		btnVolverPrincipal.setBounds(20, 501, 65, 25);
		contentPane.add(btnVolverPrincipal);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(51, 51, 51));
		separator.setForeground(new Color(51, 51, 51));
		separator.setBounds(0, 115, 784, 8);
		contentPane.add(separator);
		
		JPanel panelBuscador = new JPanel();
		panelBuscador.setOpaque(false);
		panelBuscador.setBounds(0, 54, 784, 60);
		contentPane.add(panelBuscador);
		panelBuscador.setLayout(null);
		
        try {
            MaskFormatter alphabeticFormatter = new MaskFormatter("U*********************");
            alphabeticFormatter.setInvalidCharacters("0123456789");
            alphabeticFormatter.setAllowsInvalid(false);
            alphabeticFormatter.setPlaceholderCharacter(' '); // Espacio en blanco para caracteres faltantes
            txtBuscador = new JFormattedTextField(alphabeticFormatter);
            txtBuscador.setName("buscador");
    		txtBuscador.setBounds(240, 8, 220, 25);
    		txtBuscador.setPreferredSize(new Dimension(220, 20));
    		txtBuscador.setAlignmentY(Component.TOP_ALIGNMENT);
    		txtBuscador.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR,0,14));
    		panelBuscador.add(txtBuscador);

        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setBackground(new Color(229, 229, 229));
		btnBuscar.setBorder(null);
		btnBuscar.setBounds(480, 8, 85, 25);
		btnBuscar.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT,0,14));
		panelBuscador.add(btnBuscar);
		
        btnResetBuscador = new JButton("RESTABLECER");
        btnResetBuscador.setForeground(new Color(255, 255, 255));
        btnResetBuscador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnResetBuscador.setVisible(false);
        btnResetBuscador.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT,0,14));
        btnResetBuscador.setBorder(null);
        btnResetBuscador.setBackground(new Color(0, 0, 204));
        btnResetBuscador.setBounds(575, 8, 109, 25);
        panelBuscador.add(btnResetBuscador);
		
		JLabel lblNewLabel = new JLabel("Filtros:");
		lblNewLabel.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR,1,16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(45, 42, 69, 20);
		panelBuscador.add(lblNewLabel);
		
		rdbtnOpcTodos = new JRadioButton("TODOS");
		rdbtnOpcTodos.setVerticalTextPosition(SwingConstants.TOP);
		rdbtnOpcTodos.setForeground(new Color(255, 255, 255));
		rdbtnOpcTodos.setOpaque(false);
		rdbtnOpcTodos.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR,1,14));
		rdbtnOpcTodos.setSelected(true);
		rdbtnOpcTodos.setBounds(120, 41, 79, 23);
		rdbtnOpcTodos.addActionListener(controller);
		panelBuscador.add(rdbtnOpcTodos);
		
		rdbtnOpc2 = new JRadioButton("PERSONALES");
		rdbtnOpc2.setForeground(new Color(255, 255, 255));
		rdbtnOpc2.setVerticalTextPosition(SwingConstants.TOP);
		rdbtnOpc2.setOpaque(false);
		rdbtnOpc2.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR,1,14));
		rdbtnOpc2.setBounds(212, 41, 109, 23);
		panelBuscador.add(rdbtnOpc2);
		
		rdbtnOpc3 = new JRadioButton("PROFESIONALES");
		rdbtnOpc3.setForeground(new Color(255, 255, 255));
		rdbtnOpc3.setVerticalTextPosition(SwingConstants.TOP);
		rdbtnOpc3.setOpaque(false);
		rdbtnOpc3.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR,1,14));
		rdbtnOpc3.setBounds(334, 41, 139, 23);
		panelBuscador.add(rdbtnOpc3);
		
		rdbtnOpc4 = new JRadioButton("X");
		rdbtnOpc4.setForeground(new Color(255, 255, 255));
		rdbtnOpc4.setVerticalTextPosition(SwingConstants.TOP);
		rdbtnOpc4.setOpaque(false);
		rdbtnOpc4.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR,1,14));
		rdbtnOpc4.setBounds(485, 41, 109, 23);
		rdbtnOpc4.setVisible(false);
		panelBuscador.add(rdbtnOpc4);
		
		// Crear el ButtonGroup
        buttonGroup = new ButtonGroup();
        
        // Agregar los JRadioButton al ButtonGroup
        buttonGroup.add(rdbtnOpcTodos);
        buttonGroup.add(rdbtnOpc2);
        buttonGroup.add(rdbtnOpc3);
        buttonGroup.add(rdbtnOpc4);		
        		
		contentPane.add(btnNuevo);
		contentPane.add(btnEliminar);
		contentPane.add(lblTitulo);
		
		//Tabla
		panelTabla = new JPanel();
		panelTabla.setAlignmentY(Component.TOP_ALIGNMENT);
		panelTabla.setBackground(Color.DARK_GRAY);

		contentPane.add(panelTabla);

		scrollPane = new JScrollPane(panelTabla);
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setBounds(10, 125, 764, 365);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane);
		
		GridBagLayout gbl_panelTabla = new GridBagLayout();
		gbl_panelTabla.columnWidths = new int[]{762, 0};
		gbl_panelTabla.rowHeights = new int[]{363, 0};
		gbl_panelTabla.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelTabla.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelTabla.setLayout(gbl_panelTabla);
		
		panelRegistros = new JPanel();
		panelRegistros.setAlignmentY(Component.TOP_ALIGNMENT);
		panelRegistros.setBackground(Color.DARK_GRAY);
		//panelRegistros.setOpaque(false);
		GridBagConstraints gbc_panelRegistros = new GridBagConstraints();
		gbc_panelRegistros.insets = new Insets(0, 0, 0, 0);
		gbc_panelRegistros.anchor = GridBagConstraints.NORTHWEST;
		gbc_panelRegistros.gridx = 0;
		gbc_panelRegistros.gridy = 0;
		panelTabla.add(panelRegistros, gbc_panelRegistros);
		

		gbl_panelRegistros = new GridBagLayout();
		gbl_panelRegistros.columnWidths = new int[] { 110, 150, 100, 100, 205, 70 };
		gbl_panelRegistros.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_panelRegistros.rowWeights = new double[] { }; // Restricción de relleno vertical
		// Configurar el relleno del GridBagLayout para eliminar el espacio entre los elementos
		gbl_panelRegistros.setConstraints(panelRegistros, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
		GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
		panelRegistros.setLayout(gbl_panelRegistros);
		
		// Agregar los campos de cabecera por defecto
		camposCabecera = new ArrayList<JLabel>();
		
		JLabel lblBgBaseCE = new JLabel("");
		lblBgBaseCE.setMinimumSize(new Dimension(800, 600));
		lblBgBaseCE.setMaximumSize(new Dimension(800, 600));
		lblBgBaseCE.setHorizontalAlignment(SwingConstants.LEFT);
		lblBgBaseCE.setHorizontalTextPosition(SwingConstants.CENTER);
		lblBgBaseCE.setOpaque(true);
		lblBgBaseCE.setIcon(new ImageIcon(VentanaBaseCE.class.getResource("/img/BaseCE.png")));
		lblBgBaseCE.setBounds(0,0, 784, 561);
		contentPane.add(lblBgBaseCE);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(386, 0, 12, 561);
		contentPane.add(verticalStrut);
	}
	/***************FIN DE VENTANA****************/

	/**
	 * Este método se encarga de agregar un campo a la cabecera de la tabla.
	 * Crea una etiqueta con el nombre del campo y configura su apariencia.
	 * @param nombreCampo El nombre del campo a agregar a la cabecera.
	 */
    protected void agregarCampoCabecera(String nombreCampo) {
        lblCampo = new JLabel(nombreCampo);
        lblCampo.setAlignmentY(Component.TOP_ALIGNMENT);
        lblCampo.setVerticalAlignment(SwingConstants.TOP);
        lblCampo.setHorizontalAlignment(SwingConstants.CENTER);
        lblCampo.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR,1,14));
        lblCampo.setForeground(new Color(255, 255, 255));
        lblCampo.setBorder(new EmptyBorder(10, 0, 0, 0));
        camposCabecera.add(lblCampo);
        //panelTabla.add(lblCampo);
    }
    /**
     * Este método se encarga de pintar los campos de la cabecera en el panel de registros.
     * Configura la posición de los campos en la cabecera utilizando el GridBagConstraints.
     * Los campos se pintan en una fila en el panel de registros.
     */
    protected void pintarCamposCabecera() {
    	int i=0;
		for (JLabel campo : camposCabecera) {
			
			GridBagConstraints gbc_cabecera = new GridBagConstraints();
	    	gbc_cabecera.insets = new Insets(0,0,10,5);
	    	gbc_cabecera.gridx = i;
	    	gbc_cabecera.gridy = 0;
	    	panelRegistros.add(campo,gbc_cabecera);
	    	i+=1;
		}
    }
    

	public int getNumFilasRegistros() {
		return numFilasRegistros;
	}

	public void setNumFilasRegistros(int numFilasRegistros) {
		this.numFilasRegistros = numFilasRegistros;
	}
	
	public JButton getBtnNuevo() {
		//btnNuevo.addActionListener(controller);
		return btnNuevo;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}
	
	public static  ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public void setButtonGroup(ButtonGroup buttonGroup) {
		VentanaBaseCE.buttonGroup = buttonGroup;
	}
	public JRadioButton getRdbtnOpcTodos() {
		return rdbtnOpcTodos;
	}

	public void setRdbtnOpcTodos(JRadioButton rdbtnOpcTodos) {
		this.rdbtnOpcTodos = rdbtnOpcTodos;
	}

	public JRadioButton getRdbtnOpc2() {
		return rdbtnOpc2;
	}

	public void setRdbtnOpc2(JRadioButton rdbtnOpc2) {
		this.rdbtnOpc2 = rdbtnOpc2;
	}

	public JRadioButton getRdbtnOpc3() {
		return rdbtnOpc3;
	}

	public void setRdbtnOpc3(JRadioButton rdbtnOpc3) {
		this.rdbtnOpc3 = rdbtnOpc3;
	}
	public JRadioButton getRdbtnOpc4() {
		return rdbtnOpc4;
	}

	public void setRdbtnOpc4(JRadioButton rdbtnOpc4) {
		this.rdbtnOpc4 = rdbtnOpc4;
	}
	public JButton getBtnBuscar() {
		return btnBuscar;
	}
	public JFormattedTextField getTxtBuscador() {
		return txtBuscador;
	}
	public JButton getBtnResetBuscador() {
		return btnResetBuscador;
	}
	public void setBtnResetBuscador(JButton btnResetBuscador) {
		this.btnResetBuscador = btnResetBuscador;
	}
	public JPanel getPanelRegistros() {
		return panelRegistros;
	}
	public JButton getBtnVolverPrincipal() {
		return btnVolverPrincipal;
	}
}
