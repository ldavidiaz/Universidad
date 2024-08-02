package es.poo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JTextArea;

import com.toedter.calendar.JDateChooser;
import es.poo.controller.EventoController;
import es.poo.controller.VentanasController;
import es.poo.model.Notificacion;
import fonts.Fuente;

//TODO AGREGAR ACTIONLISTENER A LOS COMBOBOX
@SuppressWarnings({"serial" })
public class VentanaAddEvento extends JFrame {

	private JPanel contentPane;
	private JSpinner inputDesdeHora, inputHastaHora;
	private JTextField txtFieldTituloEvento;
	private JTextArea txtAreaDescripcion;	
	private JButton btnAñadirEvento,btnVolverEvento;
	private JDateChooser inputFechaDesde, inputFechaHasta;
	
	Fuente tipoFuente;
	private JLabel lblNotificar;
	private JComboBox <String> cbNotificarSiNo,cbNotificacion;
	private JButton btnAddNotificacion,btnVerNotificaciones;
	public JButton getBtnAddNotificacion() {
		return btnAddNotificacion;
	}

	private JSpinner tiempoRecordatorio;
	/********************************************************************/
	
	//ATRIBUTOS PANEL TIPO DE EVENTO
	private JPanel panelTipoEvento;
	private JLabel lblTipoDeEvento;
	private JComboBox <String> cbTipoEvento;
	private JButton btnAgregarTipoEvento;
	/********************************************************************/
	
	//ATRIBUTOS PANEL CITA
	private JPanel panelTipoCita,panelCitaCumple,panelCitaAniversario,panelCitaMedico;
	private JLabel lblTipoCita, lblContactoCumple, lblContactoAniversario, lblFechaAniversario,
					lblUbicacionCitaMed, lblEspecialidadMed;
	private JFormattedTextField txtUbicacionMed;
	private JDateChooser inputCitaFechaAniversario;
	private JComboBox <String> cbTipoCita, cbContactoCumple, cbContactoAniversario, cbEspecialidadMed;
	/********************************************************************/
	
	//ATRIBUTOS PANEL REUNION
	private ArrayList<String> items;
	private JPanel panelTipoReunion, panelReunionContactos, panelReunionPresencial, panelReunionLlamada, panelReunionVideo;
	private JLabel lblReunionContactos,lblTipoReunion, lblUbicacionReunion, lblSalaReunion, lblTelefonoCorto, 
					lblTelefonoLargo, lblClaveAcceso, lblTipoVideoConferencia;
	private JFormattedTextField txtUbicacionReunion, txtTelefonoCorto, txtTelefonoLargo, txtClaveAcceso;
	private JTextField txtOtraVideoConferencia;
	private JComboBox <String>  cbTipoReunion,cbSalaReunion,cbTipoVideoConferencia;
	private JButton btnBorrarTipoEvento, btnAddContactoReunion, btnVerContactoReunion;
	/********************************************************************/
	
	//VENTANA ADD CONTACTOS
	private DefaultListModel<String> listModel = new DefaultListModel<>();//aqui se agregan los datos
	private JList<String> listaCP;
	private JButton btnAñadirCP, btnSalirAddCP;
	private JDialog ventanaAddCP;
	/********************************************************************/
	
	//VENTANA VER CONTACTOS ANYADIDOS
	private DefaultListModel<String> listModelContactosReunion = new DefaultListModel<>();
	private JList<String> listaContactosReunion;
	private JDialog ventanaVerCP;
	private JButton btnEliminarCP, btnSalirVerCP;


	/********************************************************************/
	
	//VENTANA ADD NOTIFICACION
	private JDialog ventanaAddNotificacion;
	private JLabel lblNumero, lblUnidad, lblTipoNotificacion;
	private JSpinner spinnerNumero;
	private JComboBox <String> comboUnidad, comboTipoNotificacion;
	private JButton btnGuardarNotificacion, btnSalirAddNotificacion;
	/********************************************************************/
	
	//VENTANA VER NOTIFICACION / DATOS NOTIFICACION
	private ArrayList<Notificacion> objNotificacion = new ArrayList<>();
	private DefaultListModel<String> listModelNotificacion = new DefaultListModel<>();
	private JList<String> listaNotificaciones;
	private JDialog ventanaVerNotificacion;
	private JButton btnEliminarNotificacion, btnSalirVerNotificacion;
	
	/********************************************************************/
	private EventoController controllerEvento;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VentanaAddEvento(VentanasController controllerVentanas) {
		
		controllerEvento = new EventoController(this,controllerVentanas);
		tipoFuente = new Fuente();
		
		//THIS
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//ELEMENTOS COMUNES DE LOS EVENTOS
		JLabel lblTitulo = new JLabel("AÑADIR NUEVO EVENTO");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setBounds(10, 11, 764, 66);
		lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 44));
		contentPane.add(lblTitulo);
		
		JLabel lblFechaDesde = new JLabel("DESDE FECHA :");
		lblFechaDesde.setForeground(new Color(238, 238, 238));
		lblFechaDesde.setBounds(175, 78, 105, 20);
		lblFechaDesde.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		contentPane.add(lblFechaDesde);
		
		inputFechaDesde = new JDateChooser();
		inputFechaDesde.setBounds(290, 78, 100, 20);
		contentPane.add(inputFechaDesde);
		
		JLabel lblFechaHasta = new JLabel("HASTA FECHA :");
		lblFechaHasta.setVerticalAlignment(SwingConstants.BOTTOM);
		lblFechaHasta.setForeground(new Color(238, 238, 238));
		lblFechaHasta.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		lblFechaHasta.setBounds(175, 108, 110, 20);
		contentPane.add(lblFechaHasta);
		
		inputFechaHasta = new JDateChooser();
		inputFechaHasta.setBounds(290, 108, 100, 20);
		contentPane.add(inputFechaHasta);

		JLabel lblHoraDesde = new JLabel("DESDE HORA :");
		lblHoraDesde.setVerticalAlignment(SwingConstants.BOTTOM);
		lblHoraDesde.setForeground(new Color(238, 238, 238));
		lblHoraDesde.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		lblHoraDesde.setBounds(405, 78, 95, 20);
		contentPane.add(lblHoraDesde);	
		
		// Configurar el JSpinner para seleccionar la hora
        SpinnerDateModel timeModel = new SpinnerDateModel();        
        inputDesdeHora = new JSpinner(timeModel);
        JSpinner.DateEditor de_inputDesdeHora = new JSpinner.DateEditor(inputDesdeHora, "HH:mm");
        inputDesdeHora.setEditor(de_inputDesdeHora);
        inputDesdeHora.setBounds(505,78,55,20);
        contentPane.add(inputDesdeHora);

		JLabel lblHoraHasta = new JLabel("HASTA HORA :");
		lblHoraHasta.setVerticalAlignment(SwingConstants.BOTTOM);
		lblHoraHasta.setForeground(new Color(238, 238, 238));
		lblHoraHasta.setBounds(405, 108, 95, 20);
		lblHoraHasta.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		contentPane.add(lblHoraHasta);
		
		// Configurar el JSpinner para seleccionar la hora       
        SpinnerDateModel timeModel2 = new SpinnerDateModel();
        inputHastaHora = new JSpinner(timeModel2);
        JSpinner.DateEditor de_inputHastaHora = new JSpinner.DateEditor(inputHastaHora, "HH:mm");
        inputHastaHora.setEditor(de_inputHastaHora);
        inputHastaHora.setBounds(505, 108, 55, 20);
        contentPane.add(inputHastaHora);
				
		
        JLabel lblTituloEvento = new JLabel("TITULO :");
		lblTituloEvento.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTituloEvento.setForeground(new Color(238, 238, 238));
		lblTituloEvento.setBounds(175, 138, 61, 20);
		lblTituloEvento.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		contentPane.add(lblTituloEvento);

		
		txtFieldTituloEvento = new JTextField();
		txtFieldTituloEvento.setBounds(247, 138, 313, 20);
		contentPane.add(txtFieldTituloEvento);
		txtFieldTituloEvento.setColumns(10);

		JLabel lblDescripcion = new JLabel("DESCRIPCION :");
		lblDescripcion.setForeground(new Color(238, 238, 238));
		lblDescripcion.setBounds(175, 168, 105, 20);
		lblDescripcion.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		contentPane.add(lblDescripcion);
		
		txtAreaDescripcion = new JTextArea();
		txtAreaDescripcion.setBounds(290, 168, 270, 50);
		txtAreaDescripcion.setColumns(10);
		txtAreaDescripcion.setLineWrap(true); // Hace que el JTextArea haga salto de línea
		txtAreaDescripcion.setWrapStyleWord(true); // Hace que el JTextArea haga el salto de línea después de una palabra completa
		contentPane.add(txtAreaDescripcion);
		
		lblNotificar = new JLabel("NOTIFICAR :");
		lblNotificar.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNotificar.setForeground(new Color(238, 238, 238));
		lblNotificar.setBounds(175, 228, 90, 20);
		lblNotificar.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		contentPane.add(lblNotificar);
		
		
		cbNotificarSiNo = new JComboBox();
		cbNotificarSiNo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		cbNotificarSiNo.setBorder(null);
		cbNotificarSiNo.setForeground(new Color(51, 51, 51));
		cbNotificarSiNo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbNotificarSiNo.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR, 1, 14));
		cbNotificarSiNo.setModel(new DefaultComboBoxModel(new String[] {"NO", "SI"}));
		cbNotificarSiNo.setBounds(272, 228, 45, 22);
		//controllerEvento.setCbNotificarSiNo(cbNotificarSiNo);
		cbNotificarSiNo.addActionListener(controllerEvento);
		contentPane.add(cbNotificarSiNo);
		
		btnAddNotificacion = new JButton("AÑADIR NOTIFICACION");
		btnAddNotificacion.setBounds(343, 229, 180, 23);
		btnAddNotificacion.setVisible(false);
		btnAddNotificacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddNotificacion.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR, 0, 14));
		btnAddNotificacion.addActionListener(controllerEvento);
		contentPane.add(btnAddNotificacion);
		
		btnVerNotificaciones = new JButton("VER NOTIFICACIONES");
		btnVerNotificaciones.setVisible(false);
		btnVerNotificaciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVerNotificaciones.setBounds(343, 260, 180, 23);
		btnVerNotificaciones.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR, 0, 14));
		btnVerNotificaciones.addActionListener(controllerEvento);
		contentPane.add(btnVerNotificaciones);

		/********************************************************************************************/
		
		//BOTON PARA ANYADIR MÁS DETALLES DEL EVENTO
		btnAgregarTipoEvento = new JButton("AGREGAR TIPO DE EVENTO");
		btnAgregarTipoEvento.setVisible(true);
		btnAgregarTipoEvento.setOpaque(true);
		btnAgregarTipoEvento.setHorizontalAlignment(SwingConstants.CENTER);
		btnAgregarTipoEvento.setForeground(Color.WHITE);
		btnAgregarTipoEvento.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 0, 14));
		btnAgregarTipoEvento.setBorder(null);
		btnAgregarTipoEvento.setBackground(new Color(0, 102, 255));
		btnAgregarTipoEvento.setBounds(277, 300, 230, 25);
		btnAgregarTipoEvento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAgregarTipoEvento.addActionListener(controllerEvento);
		contentPane.add(btnAgregarTipoEvento);
		/****************************************************************************/
		
		//PANEL ELEGIR TIPO DE EVENTO
		panelTipoEvento = new JPanel();
		panelTipoEvento.setBorder(null);
		panelTipoEvento.setVisible(false);
		panelTipoEvento.setBackground(Color.DARK_GRAY);
		panelTipoEvento.setBounds(175, 294, 435, 30);
		contentPane.add(panelTipoEvento);
		panelTipoEvento.setLayout(null);
		
		lblTipoDeEvento = new JLabel("TIPO DE EVENTO :");
		lblTipoDeEvento.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTipoDeEvento.setForeground(new Color(238, 238, 238));
		lblTipoDeEvento.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		lblTipoDeEvento.setBounds(0, 0, 125, 20);
		panelTipoEvento.add(lblTipoDeEvento);
		
		cbTipoEvento = new JComboBox();
		cbTipoEvento.setModel(new DefaultComboBoxModel(new String[] {"CITA", "REUNION"}));
		cbTipoEvento.setForeground(new Color(51, 51, 51));
		cbTipoEvento.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR, 1, 14));
		cbTipoEvento.setBorder(null);
		cbTipoEvento.setAlignmentY(1.0f);
		cbTipoEvento.setBounds(135, 0, 90, 22);
		cbTipoEvento.addActionListener(controllerEvento);
		panelTipoEvento.add(cbTipoEvento);
		
		btnBorrarTipoEvento = new JButton("ELIMINAR DETALLES");
		btnBorrarTipoEvento.setVisible(false);
		btnBorrarTipoEvento.setForeground(Color.WHITE);
		btnBorrarTipoEvento.setBorder(null);
		btnBorrarTipoEvento.setBackground(new Color(204, 0, 0));
		btnBorrarTipoEvento.setBounds(300, 0, 135, 23);
		btnBorrarTipoEvento.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT, 0, 14));
		btnBorrarTipoEvento.addActionListener(controllerEvento);
		panelTipoEvento.add(btnBorrarTipoEvento);
		/*******************************************************************************************/
		
		//PANEL TIPO CITA
		panelTipoCita = new JPanel();
		panelTipoCita.setVisible(false);
		panelTipoCita.setBackground(Color.DARK_GRAY);
		panelTipoCita.setBounds(175, 340, 435, 24);
		panelTipoCita.setLayout(null);
		contentPane.add(panelTipoCita);
		
		lblTipoCita = new JLabel("TIPO DE CITA :");
		lblTipoCita.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTipoCita.setForeground(new Color(238, 238, 238));
		lblTipoCita.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		lblTipoCita.setBounds(0, 0, 110, 20);
		panelTipoCita.add(lblTipoCita);
		
		cbTipoCita = new JComboBox();
		cbTipoCita.setModel(new DefaultComboBoxModel(new String[] {"CUMPLEAÑOS", "ANIVERSARIO", "MEDICO"}));
		cbTipoCita.setForeground(new Color(51, 51, 51));
		cbTipoCita.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR, 1, 14));
		cbTipoCita.setBorder(null);
		cbTipoCita.setAlignmentY(1.0f);
		cbTipoCita.setBounds(120, 0, 130, 22);
		cbTipoCita.addActionListener(controllerEvento);
		panelTipoCita.add(cbTipoCita);
		
		//CITA-CUMPLEANYOS
		panelCitaCumple = new JPanel();
		panelCitaCumple.setVisible(false);
		panelCitaCumple.setBackground(Color.DARK_GRAY);
		panelCitaCumple.setBounds(175, 370, 435, 89);
		contentPane.add(panelCitaCumple);
		panelCitaCumple.setLayout(null);
		
		lblContactoCumple = new JLabel("CONTACTO :");
		lblContactoCumple.setVerticalAlignment(SwingConstants.BOTTOM);
		lblContactoCumple.setForeground(new Color(238, 238, 238));
		lblContactoCumple.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		lblContactoCumple.setBounds(0, 0, 85, 20);
		panelCitaCumple.add(lblContactoCumple);
		
		cbContactoCumple = new JComboBox();
		//SETEAR EL MODELO CUANDO SE HAYA ELEGIDO LA OPCION 'CUMPLEANYOS' DEL COMBOBOX
		cbContactoCumple.setMaximumRowCount(4);
		cbContactoCumple.setForeground(new Color(51, 51, 51));
		cbContactoCumple.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR, 1, 14));
		cbContactoCumple.setBorder(null);
		cbContactoCumple.setAlignmentY(1.0f);
		cbContactoCumple.setBounds(95, 0, 220, 22);
		panelCitaCumple.add(cbContactoCumple);
		
		//CITA-ANIVERSARIO
		panelCitaAniversario = new JPanel();
		panelCitaAniversario.setVisible(false);
		panelCitaAniversario.setBackground(Color.DARK_GRAY);
		panelCitaAniversario.setBounds(175, 370, 435, 70);
		contentPane.add(panelCitaAniversario);
		panelCitaAniversario.setLayout(null);
		
		lblContactoAniversario = new JLabel("CONTACTO :");
		lblContactoAniversario.setVerticalAlignment(SwingConstants.BOTTOM);
		lblContactoAniversario.setForeground(new Color(238, 238, 238));
		lblContactoAniversario.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		lblContactoAniversario.setBounds(0, 0, 85, 20);
		panelCitaAniversario.add(lblContactoAniversario);
		
		inputCitaFechaAniversario = new JDateChooser();
		inputCitaFechaAniversario.setBounds(170, 30, 100, 20);
		panelCitaAniversario.add(inputCitaFechaAniversario);
		
		cbContactoAniversario = new JComboBox();
		cbContactoAniversario.setMaximumRowCount(4);
		cbContactoAniversario.setForeground(new Color(51, 51, 51));
		cbContactoAniversario.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR, 1, 14));
		cbContactoAniversario.setBorder(null);
		cbContactoAniversario.setAlignmentY(1.0f);
		cbContactoAniversario.setBounds(95, 0, 220, 22);
		panelCitaAniversario.add(cbContactoAniversario);
		
		lblFechaAniversario = new JLabel("FECHA ANIVERSARIO :");
		lblFechaAniversario.setVerticalAlignment(SwingConstants.BOTTOM);
		lblFechaAniversario.setForeground(new Color(238, 238, 238));
		lblFechaAniversario.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		lblFechaAniversario.setBounds(0, 30, 154, 20);
		panelCitaAniversario.add(lblFechaAniversario);
		
		//CITA-MEDICO
		panelCitaMedico = new JPanel();
		panelCitaMedico.setVisible(false);
		panelCitaMedico.setBackground(Color.DARK_GRAY);
		panelCitaMedico.setBounds(175, 370, 435, 75);
		contentPane.add(panelCitaMedico);
		panelCitaMedico.setLayout(null);
		
		lblUbicacionCitaMed = new JLabel("UBICACION :");
		lblUbicacionCitaMed.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUbicacionCitaMed.setForeground(new Color(238, 238, 238));
		lblUbicacionCitaMed.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		lblUbicacionCitaMed.setBounds(0, 0, 90, 20);
		panelCitaMedico.add(lblUbicacionCitaMed);
		try {
			MaskFormatter formatter = new MaskFormatter("##º ##'´ ##.###'´´ / ##º ##'´ ##.###'´´");
			txtUbicacionMed = new JFormattedTextField(formatter);
			txtUbicacionMed.setBounds(105, 0, 180, 20);
			panelCitaMedico.add(txtUbicacionMed);
			txtUbicacionMed.setColumns(10);
		}   catch (Exception e) {
            e.printStackTrace();
        }
		lblEspecialidadMed = new JLabel("ESPECIALIDAD :");
		lblEspecialidadMed.setVerticalAlignment(SwingConstants.BOTTOM);
		lblEspecialidadMed.setForeground(new Color(238, 238, 238));
		lblEspecialidadMed.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		lblEspecialidadMed.setBounds(0, 30, 113, 20);
		panelCitaMedico.add(lblEspecialidadMed);
		
		cbEspecialidadMed = new JComboBox();
		cbEspecialidadMed.setModel(new DefaultComboBoxModel(new String[] {"DENTISTA", "FISIOTERAPEUTA", "OFTALMOLOGO", "DERMATOLOGO", "OTORRINOLARINGOLOGO"}));
		cbEspecialidadMed.setMaximumRowCount(4);
		cbEspecialidadMed.setForeground(new Color(51, 51, 51));
		cbEspecialidadMed.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR, 1, 14));
		cbEspecialidadMed.setBorder(null);
		cbEspecialidadMed.setAlignmentY(1.0f);
		cbEspecialidadMed.setBounds(123, 29, 150, 22);
		panelCitaMedico.add(cbEspecialidadMed);
		/****************************************************************************************************/
		
		//PANELES TIPO REUNION
		panelTipoReunion = new JPanel();
		panelTipoReunion.setVisible(false);
		panelTipoReunion.setBackground(Color.DARK_GRAY);
		panelTipoReunion.setBounds(175, 340, 435, 24);
		panelTipoReunion.setLayout(null);
		contentPane.add(panelTipoReunion);
		
		lblTipoReunion = new JLabel("TIPO DE REUNION :");
		lblTipoReunion.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTipoReunion.setForeground(new Color(238, 238, 238));
		lblTipoReunion.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		lblTipoReunion.setBounds(0, 0, 135, 20);
		panelTipoReunion.add(lblTipoReunion);
		
		cbTipoReunion = new JComboBox();
		cbTipoReunion.setModel(new DefaultComboBoxModel(new String[] {"PRESENCIAL", "LLAMADA", "VIDEOCONFERENCIA"}));
		cbTipoReunion.setForeground(new Color(51, 51, 51));
		cbTipoReunion.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR, 1, 14));
		cbTipoReunion.setBorder(null);
		cbTipoReunion.setAlignmentY(1.0f);
		cbTipoReunion.setBounds(145, 0, 130, 22);
		cbTipoReunion.addActionListener(controllerEvento);
		panelTipoReunion.add(cbTipoReunion);
		
		//PANEL ADD CONTACTOS
		panelReunionContactos = new JPanel();
		panelReunionContactos.setVisible(false);
		panelReunionContactos.setBackground(Color.DARK_GRAY);
		panelReunionContactos.setBounds(175, 366, 435, 25);
		contentPane.add(panelReunionContactos);
		panelReunionContactos.setLayout(null);
		
		lblReunionContactos = new JLabel("CONTACTOS :");
		lblReunionContactos.setVerticalAlignment(SwingConstants.BOTTOM);
		lblReunionContactos.setForeground(new Color(238, 238, 238));
		lblReunionContactos.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		lblReunionContactos.setBounds(0, 0, 96, 20);
		panelReunionContactos.add(lblReunionContactos);
		
		btnAddContactoReunion = new JButton("AÑADIR");
		btnAddContactoReunion.setBounds(90, 0, 90, 22);
		btnAddContactoReunion.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR, 0, 14));
		btnAddContactoReunion.addActionListener(controllerEvento);
		panelReunionContactos.add(btnAddContactoReunion);
		
		btnVerContactoReunion = new JButton("VER CONTACTOS AÑADIDOS");
		btnVerContactoReunion.setVisible(false);
		btnVerContactoReunion.setBounds(190, 0, 230, 22);
		btnVerContactoReunion.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR, 0, 14));
		btnVerContactoReunion.addActionListener(controllerEvento);
		panelReunionContactos.add(btnVerContactoReunion);
		

		//PRESENCIAL
		panelReunionPresencial = new JPanel();
		panelReunionPresencial.setVisible(false);
		panelReunionPresencial.setBackground(Color.DARK_GRAY);
		panelReunionPresencial.setBounds(175, 395, 435, 70);
		panelReunionPresencial.setLayout(null);
		contentPane.add(panelReunionPresencial);
				
		lblUbicacionReunion = new JLabel("UBICACION :");
		lblUbicacionReunion.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUbicacionReunion.setForeground(new Color(238, 238, 238));
		lblUbicacionReunion.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		lblUbicacionReunion.setBounds(0, 0, 90, 20);
		panelReunionPresencial.add(lblUbicacionReunion);

		try {
			MaskFormatter formatter = new MaskFormatter("##º ##'´ ##.###'´´ / ##º ##'´ ##.###'´´");
			txtUbicacionReunion = new JFormattedTextField(formatter);
			txtUbicacionReunion.setBounds(105, 0, 180, 20);
			panelReunionPresencial.add(txtUbicacionReunion);
			txtUbicacionReunion.setColumns(10);
		}   catch (Exception e) {
            e.printStackTrace();
        }
		lblSalaReunion = new JLabel("SALA :");
		lblSalaReunion.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSalaReunion.setForeground(new Color(238, 238, 238));
		lblSalaReunion.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		lblSalaReunion.setBounds(0, 30, 90, 20);
		panelReunionPresencial.add(lblSalaReunion);
		
		cbSalaReunion = new JComboBox();
		cbSalaReunion.setModel(new DefaultComboBoxModel(new String[] {"SALA A", "SALA B", "SALA C"}));
		cbSalaReunion.setMaximumRowCount(4);
		cbSalaReunion.setForeground(new Color(51, 51, 51));
		cbSalaReunion.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR, 1, 14));
		cbSalaReunion.setBorder(null);
		cbSalaReunion.setAlignmentY(1.0f);
		cbSalaReunion.setBounds(100, 29, 150, 22);
		cbSalaReunion.addActionListener(controllerEvento);
		panelReunionPresencial.add(cbSalaReunion);
		
		
		//LLAMADA
		panelReunionLlamada = new JPanel();
		panelReunionLlamada.setVisible(false);
		panelReunionLlamada.setBackground(Color.DARK_GRAY);
		panelReunionLlamada.setBounds(175, 395, 435, 83);
		contentPane.add(panelReunionLlamada);
		panelReunionLlamada.setLayout(null);
		
		lblTelefonoCorto = new JLabel("Telefono Corto :");
		lblTelefonoCorto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTelefonoCorto.setForeground(new Color(238, 238, 238));
		lblTelefonoCorto.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		lblTelefonoCorto.setBounds(0, 0, 130, 20);
		panelReunionLlamada.add(lblTelefonoCorto);

		try {
			MaskFormatter formatter = new MaskFormatter("#####");
			txtTelefonoCorto = new JFormattedTextField(formatter);
			txtTelefonoCorto.setBounds(141, 0, 90, 20);
			panelReunionLlamada.add(txtTelefonoCorto);
			txtTelefonoCorto.setColumns(10);
		}   catch (Exception e) {
            e.printStackTrace();
        }
		
		lblTelefonoLargo = new JLabel("Telefono Largo :");
		lblTelefonoLargo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTelefonoLargo.setForeground(new Color(238, 238, 238));
		lblTelefonoLargo.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		lblTelefonoLargo.setBounds(0, 30, 130, 20);
		panelReunionLlamada.add(lblTelefonoLargo);
		
		try {
			MaskFormatter formatter = new MaskFormatter("### ## ## ##");
			txtTelefonoLargo = new JFormattedTextField(formatter);
			txtTelefonoLargo.setBounds(141, 30, 90, 20);
			panelReunionLlamada.add(txtTelefonoLargo);
			txtTelefonoLargo.setColumns(10);
		}   catch (Exception e) {
            e.printStackTrace();
        }
		
		lblClaveAcceso = new JLabel("Clave de acceso :");
		lblClaveAcceso.setVerticalAlignment(SwingConstants.BOTTOM);
		lblClaveAcceso.setForeground(new Color(238, 238, 238));
		lblClaveAcceso.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		lblClaveAcceso.setBounds(0, 60, 140, 20);
		panelReunionLlamada.add(lblClaveAcceso);
		
		try {
			MaskFormatter formatter = new MaskFormatter("#####'#");
			txtClaveAcceso = new JFormattedTextField(formatter);
			txtClaveAcceso.setBounds(151, 60, 90, 20);
			panelReunionLlamada.add(txtClaveAcceso);
			txtClaveAcceso.setColumns(10);
		}   catch (Exception e) {
            e.printStackTrace();
        }
		
		//VIDEOCONFERENCIA
		panelReunionVideo = new JPanel();
		panelReunionVideo.setVisible(false);
		panelReunionVideo.setBackground(Color.DARK_GRAY);
		panelReunionVideo.setBounds(175, 395, 435, 70);
		panelReunionVideo.setLayout(null);
		contentPane.add(panelReunionVideo);
		
		lblTipoVideoConferencia = new JLabel("TIPO :");
		lblTipoVideoConferencia.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTipoVideoConferencia.setForeground(new Color(238, 238, 238));
		lblTipoVideoConferencia.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		lblTipoVideoConferencia.setBounds(0, 0, 50, 20);
		panelReunionVideo.add(lblTipoVideoConferencia);
		
		cbTipoVideoConferencia = new JComboBox();
		cbTipoVideoConferencia.setModel(new DefaultComboBoxModel(new String[] {"Google Meet", "Microsoft Teams", "Zoom", "Skype", "Otra: Especificar"}));
		cbTipoVideoConferencia.setMaximumRowCount(5);
		cbTipoVideoConferencia.setForeground(new Color(51, 51, 51));
		cbTipoVideoConferencia.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR, 1, 14));
		cbTipoVideoConferencia.setBorder(null);
		cbTipoVideoConferencia.setAlignmentY(1.0f);
		cbTipoVideoConferencia.setBounds(60, 0, 150, 22);
		cbTipoVideoConferencia.addActionListener(controllerEvento);
		panelReunionVideo.add(cbTipoVideoConferencia);
		
		txtOtraVideoConferencia = new JTextField();
		txtOtraVideoConferencia.setVisible(false);
		txtOtraVideoConferencia.setBounds(220, 0, 200, 20);
		txtOtraVideoConferencia.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR, 0, 13));
		panelReunionVideo.add(txtOtraVideoConferencia);
		txtOtraVideoConferencia.setColumns(10);
		/*************************************************************************************************************/		
		
		btnAñadirEvento = new JButton("GUARDAR");
		btnAñadirEvento.setOpaque(true);
		btnAñadirEvento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAñadirEvento.setBorder(null);
		btnAñadirEvento.setBackground(new Color(0, 51, 0));
		btnAñadirEvento.setForeground(new Color(255, 255, 255));
		btnAñadirEvento.setHorizontalAlignment(SwingConstants.CENTER);
		btnAñadirEvento.setBounds(400, 515, 90, 25);
		btnAñadirEvento.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT, 0, 14));
		btnAñadirEvento.addActionListener(controllerEvento);
		contentPane.add(btnAñadirEvento);
		
		btnVolverEvento = new JButton("VOLVER");
		btnVolverEvento.addActionListener(controllerVentanas);
		btnVolverEvento.setBorder(null);
		btnVolverEvento.setOpaque(true);
		btnVolverEvento.setBackground(new Color(199, 199, 199));
		btnVolverEvento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVolverEvento.setHorizontalAlignment(SwingConstants.CENTER);
		btnVolverEvento.setForeground(new Color(255, 255, 255));
		btnVolverEvento.setBounds(320, 515, 65, 25);
		btnVolverEvento.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT, 0, 14));		
		contentPane.add(btnVolverEvento);
	
		JLabel bgForm = new JLabel("");
		bgForm.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bgForm.setOpaque(true);
		bgForm.setBackground(Color.DARK_GRAY);
		bgForm.setBounds(160, 70, 465, 435);
		contentPane.add(bgForm);
		
		JLabel lblBgAddEvento = new JLabel("");
		lblBgAddEvento.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblBgAddEvento.setIcon(new ImageIcon(VentanaAddContacto.class.getResource("/img/BaseCE.png")));
		lblBgAddEvento.setBounds(0, 0, 784, 560);
		contentPane.add(lblBgAddEvento);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		rigidArea.setBounds(0, 0, 784, 561);
		contentPane.add(rigidArea);	
		

	}
	

	/**
	 * Este método muestra la ventana emergente para agregar una notificación.
	 */
	public void ventanaAddNotificacion() {
	    ventanaAddNotificacion = new JDialog(this, "Añadir Evento", true);
	    ventanaAddNotificacion.setSize(400, 200);
	    ventanaAddNotificacion.setLocationRelativeTo(this);

	    lblNumero = new JLabel("Número de horas/días/semanas:");
	    spinnerNumero = new JSpinner();
	    lblUnidad = new JLabel("Minutos/Horas/Días/Semanas:");
	    comboUnidad = new JComboBox<>(new String[]{"Minutos","Horas", "Días", "Semanas"});
	    lblTipoNotificacion = new JLabel("Tipo de notificación:");
	    comboTipoNotificacion = new JComboBox<String>();
	    comboTipoNotificacion.setModel(new DefaultComboBoxModel<String>(new String[] {"POP UP", "SMS", "EMAIL"}));
	    btnGuardarNotificacion = new JButton("Guardar");
	    btnGuardarNotificacion.addActionListener(getControllerEvento());
	    btnSalirAddNotificacion = new JButton("Salir");
	    btnSalirAddNotificacion.addActionListener(getControllerEvento());

	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(4, 2, 10, 10));
	    panel.add(lblNumero);
	    panel.add(spinnerNumero);
	    panel.add(lblUnidad);
	    panel.add(comboUnidad);
	    panel.add(lblTipoNotificacion);
	    panel.add(comboTipoNotificacion);
	    panel.add(btnGuardarNotificacion);
	    panel.add(btnSalirAddNotificacion);

	    ventanaAddNotificacion.getContentPane().add(panel);
	    ventanaAddNotificacion.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    ventanaAddNotificacion.setVisible(true);
	}
	/**
	 * Este método muestra la ventana emergente para ver las notificaciones.
	 */
    public void ventanaVerNotificacion() {
    
	    ventanaVerNotificacion = new JDialog(this, "Ver Notificacion. 'Ctrl'+'click' eleccion multiple", true);
	    ventanaVerNotificacion.setSize(400,300);
	    ventanaVerNotificacion.setLocationRelativeTo(this);
	    
        listModelNotificacion = new DefaultListModel<>();
        for (Notificacion notificacion : getObjNotificacion()) {
            listModelNotificacion.addElement(notificacion.toString());
        }
        listaNotificaciones = new JList<>(listModelNotificacion);
        btnEliminarNotificacion = new JButton("Eliminar");
        btnSalirVerNotificacion = new JButton("Salir");

        // Construir la lista de datos de las notificaciones


        // Configurar el ActionListener para el botón "Eliminar"
        btnEliminarNotificacion.addActionListener(getControllerEvento());

        // Configurar el ActionListener para el botón "Salir"
        btnSalirVerNotificacion.addActionListener(getControllerEvento());

        // Configurar el disenyo de la ventana
        getContentPane().setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(listaNotificaciones);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnEliminarNotificacion);
        panelBotones.add(btnSalirVerNotificacion);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);
        // Agregar componentes al diálogo
        ventanaVerNotificacion.getContentPane().setLayout(new BorderLayout());
        ventanaVerNotificacion.getContentPane().add(scrollPane, BorderLayout.CENTER);
        ventanaVerNotificacion.getContentPane().add(panelBotones, BorderLayout.SOUTH);
        // Configurar el cierre de la ventana emergente
        ventanaVerNotificacion.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        // Mostrar la ventana emergente
        ventanaVerNotificacion
        .setVisible(true);
    }

	/**
	 * Este método muestra la ventana emergente para agregar contactos.
	 */
	public void ventanaAddContactos() {
        // Crear ventana emergente (JDialog)
        ventanaAddCP = new JDialog(this, "AÑADIR CONTACTOS. 'Ctrl'+'click' eleccion multiple", true);
        ventanaAddCP.setSize(400, 300);
        ventanaAddCP.setLocationRelativeTo(this);

        // Crear JList y JScrollPane
        listaCP = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listaCP);

        // Crear botón de confirmar
        btnAñadirCP = new JButton("Añadir");
        btnAñadirCP.addActionListener(getControllerEvento());
        // Crear botón de salir
        btnSalirAddCP = new JButton("Salir");
        btnSalirAddCP.addActionListener(getControllerEvento());

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAñadirCP);
        panelBotones.add(btnSalirAddCP);

        // Agregar componentes al diálogo
        ventanaAddCP.getContentPane().setLayout(new BorderLayout());
        ventanaAddCP.getContentPane().add(scrollPane, BorderLayout.CENTER);
        ventanaAddCP.getContentPane().add(panelBotones, BorderLayout.SOUTH);
        // Configurar el cierre de la ventana emergente
        ventanaAddCP.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        // Mostrar la ventana emergente
        ventanaAddCP.setVisible(true);
    }
	/**
	 * Este método muestra la ventana emergente para ver los contactos de una reunión.
	 */
    public void ventanaVerContactosReunion() {
        // Crear ventana emergente (JDialog)
        ventanaVerCP = new JDialog(this, "CONTACTOS AÑADIDOS. 'Ctrl'+'click' eleccion multiple", true);
        ventanaVerCP.setSize(400, 300);
        ventanaVerCP.setLocationRelativeTo(this);

        // Crear JList y JScrollPane
        listaContactosReunion = new JList<>(listModelContactosReunion);
        JScrollPane scrollPane = new JScrollPane(listaContactosReunion);

        // Crear botón de confirmar
        btnEliminarCP = new JButton("ELIMINAR");
        btnEliminarCP.addActionListener(getControllerEvento());
        // Crear botón de salir
        btnSalirVerCP = new JButton("Salir");
        btnSalirVerCP.addActionListener(getControllerEvento());

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnEliminarCP);
        panelBotones.add(btnSalirVerCP);
        // Agregar componentes al diálogo
        ventanaVerCP.getContentPane().setLayout(new BorderLayout());
        ventanaVerCP.getContentPane().add(scrollPane, BorderLayout.CENTER);
        ventanaVerCP.getContentPane().add(panelBotones, BorderLayout.SOUTH);
        // Configurar el cierre de la ventana emergente
        ventanaVerCP.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        // Mostrar la ventana emergente
        ventanaVerCP.setVisible(true);
    }
	public JDialog getVentanaVerCP() {
		return ventanaVerCP;
	}

	public JButton getBtnVerContactoReunion() {
		return btnVerContactoReunion;
	}

	public JButton getBtnAddContactoReunion() {
		return btnAddContactoReunion;
	}

	public JPanel getPanelReunionContactos() {
		return panelReunionContactos;
	}

	public JButton getBtnBorrarTipoEvento() {
		return btnBorrarTipoEvento;
	}

	public JButton getBtnAgregarTipoEvento() {
		return btnAgregarTipoEvento;
	}
	public JButton getBtnVolverEvento() {
		return btnVolverEvento;
	}
	public JButton getBtnAñadirContacto() {
		return btnAñadirEvento;
	}
	public JButton getBtnAñadirEvento() {
		return btnAñadirEvento;
	}
	public JButton getBtnVerNotificaciones() {
		return btnVerNotificaciones;
	}
	public JDateChooser getInputFechaDesde() {
		return inputFechaDesde;
	}
	public JDateChooser getInputFechaHasta() {
		return inputFechaHasta;
	}
	public JDateChooser getInputCitaFechaAniversario() {
		return inputCitaFechaAniversario;
	}
	public JDateChooser getInputFechaNac() {
		return inputFechaDesde;
	}
	public JFormattedTextField getTxtUbicacionReunion() {
		return txtUbicacionReunion;
	}
	public JFormattedTextField getTxtTelefonoCorto() {
		return txtTelefonoCorto;
	}
	public JFormattedTextField getTxtTelefonoLargo() {
		return txtTelefonoLargo;
	}
	public JFormattedTextField getTxtClaveAcceso() {
		return txtClaveAcceso;
	}
	public JTextField getTxtOtraVideoConferencia() {
		return txtOtraVideoConferencia;
	}
	public JTextField getTxtFieldTituloEvento() {
		return txtFieldTituloEvento;
	}
	public JTextArea getTxtAreaDescripcion() {
		return txtAreaDescripcion;
	}
	public JSpinner getInputDesdeHora() {
		return inputDesdeHora;
	}
	public JSpinner getInputHastaHora() {
		return inputHastaHora;
	}
	public JSpinner getTiempoRecordatorio() {
		return tiempoRecordatorio;
	}
	public JPanel getPanelTipoEvento() {
		return panelTipoEvento;
	}
	public JPanel getPanelTipoCita() {
		return panelTipoCita;
	}
	public JPanel getPanelCitaCumple() {
		return panelCitaCumple;
	}
	public JPanel getPanelCitaAniversario() {
		return panelCitaAniversario;
	}
	public JPanel getPanelCitaMedico() {
		return panelCitaMedico;
	}
	public JPanel getPanelTipoReunion() {
		return panelTipoReunion;
	}
	public JPanel getPanelReunionPresencial() {
		return panelReunionPresencial;
	}
	public JPanel getPanelReunionLlamada() {
		return panelReunionLlamada;
	}
	public JPanel getPanelReunionVideo() {
		return panelReunionVideo;
	}
	public JFormattedTextField getTxtUbicacionMed() {
		return txtUbicacionMed;
	}
	public ArrayList<String> getItems() {
		return items;
	}
	public String getOpcNotificarSiNo() {
		return (String) cbNotificarSiNo.getSelectedItem();
	}
	public String getOpcNotificacion() {
		return (String) cbNotificacion.getSelectedItem();
	}
	public String getOpcTipoEvento() {
		return (String) cbTipoEvento.getSelectedItem();
	}
	public String getOpcTipoCita() {
		return (String) cbTipoCita.getSelectedItem();
	}
	public String getOpcContactoCumple() {
		return (String) cbContactoCumple.getSelectedItem();
	}
	public String getOpcContactoAniversario() {
		return (String) cbContactoAniversario.getSelectedItem();
	}
	public String getOpcEspecialidadMed() {
		return (String) cbEspecialidadMed.getSelectedItem();
	}
	public String getOpcTipoReunion() {
		return (String) cbTipoReunion.getSelectedItem();
	}
	public String getOpcSalaReunion() {
		return (String) cbSalaReunion.getSelectedItem();
	}
	public String getOpcTipoVideoConferencia() {
		return (String) cbTipoVideoConferencia.getSelectedItem();
	}
	public JComboBox<String> getCbSalaReunion() {
		return cbSalaReunion;
	}
    public JDialog getVentanaVerNotificacion() {
		return ventanaVerNotificacion;
	}
	public JButton getBtnEliminarNotificacion() {
		return btnEliminarNotificacion;
	}
	public JComboBox<String> getCbNotificarSiNo() {
		return cbNotificarSiNo;
	}
	public JComboBox<String> getCbTipoEvento() {
		return cbTipoEvento;
	}
	public JComboBox<String> getCbTipoCita() {
		return cbTipoCita;
	}
	public JComboBox<String> getCbContactoCumple() {
		return cbContactoCumple;
	}
	public JComboBox<String> getCbContactoAniversario() {
		return cbContactoAniversario;
	}
	public JComboBox<String> getCbTipoReunion() {
		return cbTipoReunion;
	}
	public JComboBox<String> getCbTipoVideoConferencia() {
		return cbTipoVideoConferencia;
	}
	public DefaultListModel<String> getListModel() {
		return listModel;
	}
	public JList<String> getListaCP() {
		return listaCP;
	}
	public JButton getBtnAñadirCP() {
		return btnAñadirCP;
	}
	public JButton getBtnSalirAddCP() {
		return btnSalirAddCP;
	}
	public JDialog getVentanaAddCP() {
		return ventanaAddCP;
	}
	public DefaultListModel<String> getListModelContactosReunion() {
		return listModelContactosReunion;
	}
	public JList<String> getListaContactosReunion() {
		return listaContactosReunion;
	}
	public JButton getBtnEliminarCP() {
		return btnEliminarCP;
	}
	public EventoController getControllerEvento() {
		return controllerEvento;
	}
	//GETTER VENTANA/S NOTIFICACION
	public JDialog getVentanaAddNotificacion() {
		return ventanaAddNotificacion;
	}

	public JSpinner getSpinnerNumero() {
		return spinnerNumero;
	}

	public JComboBox<String> getComboUnidad() {
		return comboUnidad;
	}

	public JComboBox<String> getComboTipoNotificacion() {
		return comboTipoNotificacion;
	}

	public JButton getBtnGuardarNotificacion() {
		return btnGuardarNotificacion;
	}

	public JButton getBtnSalirAddNotificacion() {
		return btnSalirAddNotificacion;
	}

	public ArrayList<Notificacion> getObjNotificacion() {
		return objNotificacion;
	}

	public DefaultListModel<String> getListModelNotificacion() {
		return listModelNotificacion;
	}

	public JList<String> getListaNotificaciones() {
		return listaNotificaciones;
	}
}
