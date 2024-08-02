package es.poo.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fonts.Fuente;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Cursor;
import javax.swing.text.MaskFormatter;

import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import es.poo.controller.ContactoController;
import es.poo.controller.VentanasController;
import es.poo.database.DBAgenda;
import es.poo.model.Contacto;
import es.poo.model.ContactoPersonal;
import es.poo.model.ContactoProfesional;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class VentanaEditarContacto extends JFrame {

	//ContactoController controllerContacto = new ContactoController();
	private  String numViejo;


	private JPanel contentPane;
	private JLabel lblNombre,
					lblApellidos,
					lblTelefono,
					lblMovil,
					lblFechaNac,
					lblEmail;
	
	private JButton btnActualizarContacto,btnVolverContacto;
	

	private JFormattedTextField txtFieldTelefono,
								 txtFieldMovil;
	
	private JTextField txtFieldNombre,
						 txtFieldApellidos,
						 txtFieldAliasEmpresa,
						 txtFieldEmail;

	Fuente tipoFuente;
	private JComboBox<String> comboBox;
	@SuppressWarnings("unused")
	private String opcionComboBox;
	private JDateChooser inputFechaNac;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VentanaEditarContacto(VentanasController controllerVentanas) {
		ContactoController controllerContacto = new ContactoController(this,controllerVentanas);
		tipoFuente = new Fuente();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		comboBox.setBorder(null);
		comboBox.setForeground(new Color(51, 51, 51));
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_REGULAR, 1, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ALIAS", "EMPRESA"}));
		comboBox.setBounds(245, 210, 85, 22);
		contentPane.add(comboBox);
		
		inputFechaNac = new JDateChooser();
		inputFechaNac.setBounds(437, 351, 100, 20);
		contentPane.add(inputFechaNac);
		
		txtFieldNombre = new JTextField();
		txtFieldNombre.setBounds(354, 140, 153, 20);
		contentPane.add(txtFieldNombre);
		txtFieldNombre.setColumns(10);
		
		txtFieldApellidos = new JTextField();
		txtFieldApellidos.setBounds(354, 175, 153, 20);
		contentPane.add(txtFieldApellidos);
		txtFieldApellidos.setColumns(10);
		
		txtFieldAliasEmpresa = new JTextField();
		txtFieldAliasEmpresa.setBounds(354, 210, 153, 20);
		contentPane.add(txtFieldAliasEmpresa);
		txtFieldAliasEmpresa.setColumns(10);
		

		try {
			MaskFormatter formatter = new MaskFormatter("###-###-###");
			txtFieldTelefono = new JFormattedTextField(formatter);
			txtFieldTelefono.setBounds(354, 250, 153, 20);
			contentPane.add(txtFieldTelefono);
			txtFieldTelefono.setColumns(10);
		}   catch (Exception e) {
            e.printStackTrace();
        }
		
		try {
            MaskFormatter formatter = new MaskFormatter("###-##-##-##");
            txtFieldMovil = new JFormattedTextField(formatter);
			txtFieldMovil.setBounds(354, 285, 153, 20);
			contentPane.add(txtFieldMovil);
			txtFieldMovil.setColumns(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
	
		txtFieldEmail = new JTextField();
		txtFieldEmail.setBounds(354, 320, 153, 20);
		contentPane.add(txtFieldEmail);
		txtFieldEmail.setColumns(10);
		
		lblNombre = new JLabel("NOMBRE :");
		lblNombre.setForeground(new Color(238, 238, 238));
		lblNombre.setBounds(245, 140, 75, 20);
		lblNombre.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		contentPane.add(lblNombre);
		
		lblApellidos = new JLabel("APELLIDOS :");
		lblApellidos.setForeground(new Color(238, 238, 238));
		lblApellidos.setBounds(245, 175, 99, 20);
		lblApellidos.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		contentPane.add(lblApellidos);

		lblTelefono = new JLabel("TELEFONO :");
		lblTelefono.setForeground(new Color(238, 238, 238));
		lblTelefono.setBounds(245, 250, 85, 20);
		lblTelefono.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		contentPane.add(lblTelefono);
		
		lblMovil = new JLabel("MOVIL :");
		lblMovil.setForeground(new Color(238, 238, 238));
		lblMovil.setBounds(245, 285, 85, 20);
		lblMovil.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		contentPane.add(lblMovil);
		
		lblFechaNac = new JLabel("FECHA DE\r\n NACIMIENTO :");
		lblFechaNac.setForeground(new Color(238, 238, 238));
		lblFechaNac.setBounds(245, 352, 180, 20);
		lblFechaNac.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		contentPane.add(lblFechaNac);

		
		lblEmail = new JLabel("EMAIL :");
		lblEmail.setForeground(new Color(238, 238, 238));
		lblEmail.setBounds(245, 320, 130, 20);
		lblEmail.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 13));
		contentPane.add(lblEmail);
		
		btnActualizarContacto = new JButton("GUARDAR");
		btnActualizarContacto.setOpaque(true);
		btnActualizarContacto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnActualizarContacto.setBorder(null);
		btnActualizarContacto.setBackground(new Color(0, 51, 0));
		btnActualizarContacto.setForeground(new Color(255, 255, 255));
		btnActualizarContacto.setHorizontalAlignment(SwingConstants.CENTER);
		btnActualizarContacto.setBounds(401, 465, 70, 25);
		btnActualizarContacto.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT, 0, 14));
		btnActualizarContacto.addActionListener(controllerContacto);
		contentPane.add(btnActualizarContacto);
		
		btnVolverContacto = new JButton("VOLVER");
		btnVolverContacto.addActionListener(controllerVentanas);
		btnVolverContacto.setBorder(null);
		btnVolverContacto.setOpaque(true);
		btnVolverContacto.setBackground(new Color(199, 199, 199));
		btnVolverContacto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVolverContacto.setHorizontalAlignment(SwingConstants.CENTER);
		btnVolverContacto.setForeground(new Color(255, 255, 255));
		btnVolverContacto.setBounds(320, 465, 65, 25);
		btnVolverContacto.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT, 0, 14));
		contentPane.add(btnVolverContacto);
		
		JLabel lblTitulo = new JLabel("ACTUALIZAR CONTACTO");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setBounds(10, 11, 764, 66);
		lblTitulo.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT_BOLD, 1, 44));
		contentPane.add(lblTitulo);
		
		JLabel bgForm = new JLabel("");
		bgForm.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bgForm.setOpaque(true);
		bgForm.setBackground(Color.DARK_GRAY);
		bgForm.setBounds(160, 110, 453, 300);
		contentPane.add(bgForm);
		
		JLabel lblBgAddContacto = new JLabel("");
		lblBgAddContacto.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblBgAddContacto.setIcon(new ImageIcon(VentanaAddContacto.class.getResource("/img/BaseCE.png")));
		lblBgAddContacto.setBounds(0, 0, 784, 560);
		
		contentPane.add(lblBgAddContacto);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		rigidArea.setBounds(0, 0, 784, 561);
		contentPane.add(rigidArea);
	}
	
	/**
	 * Este método se encarga de inicializar los datos de un contacto a partir de un número de teléfono móvil.
	 * @param numTelefonoMovil El número de teléfono móvil del contacto.
	 */
	public void inicDatos(String numTelefonoMovil) {
		String [] obtMovil = numTelefonoMovil.split("/");
		String movil = obtMovil[1].trim();
        for (Contacto contacto : DBAgenda.getListaContactos()) {
            if (contacto.getMovil().equals(movil)) {
        
                String selectedModel = getOpcionComboBox();
                // Verificar qué modelo está seleccionado y cambiarlo si es necesario
                if (selectedModel.equals("ALIAS")) {
                    comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"ALIAS", "EMPRESA"}));
                } else if (selectedModel.equals("EMPRESA")) {
                    comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"EMPRESA", "ALIAS"}));
                }
                
            	if(contacto instanceof ContactoPersonal) {
            		ContactoPersonal cp = (ContactoPersonal) contacto;
            		setAliasEmpresa(cp.getAlias()); 
            	}
            	else if(contacto instanceof ContactoProfesional) {
            		ContactoProfesional cpr = (ContactoProfesional) contacto;
            		setAliasEmpresa(cpr.getEmpresa());
            	}
            	setNombre(contacto.getNombre());
            	setApellidos(contacto.getApellidos());
            	setTelefono(contacto.getTelefono());
            	setMovil(contacto.getMovil());
            	setNumViejo(contacto.getMovil());
            	setEmail(contacto.getEmail());
            	setFechaNac(contacto.getFechaNac());
                break; // Si solo quieres el primer resultado encontrado, puedes salir del bucle.
            }
        }
	}
	
	public JButton getBtnVolverContacto() {
		return btnVolverContacto;
	}
	public JButton getBtnActualizarrContacto() {
		return btnActualizarContacto;
	}
	public JFormattedTextField getTxtFieldTelefono() {
		return txtFieldTelefono;
	}
	public void setTelefono(String telefono) {
		this.txtFieldTelefono.setText(telefono);
		
	}
	public JFormattedTextField getTxtFieldMovil() {
		return txtFieldMovil;
	}
	public void setMovil(String movil) {
		this.txtFieldMovil.setText(movil);
	}
	public JTextField getTxtFieldNombre() {
		return txtFieldNombre;
	}
	public void setNombre(String nombre) {
		this.txtFieldNombre.setText(nombre);;
	}
	public JTextField getTxtFieldApellidos() {
		return txtFieldApellidos;
	}
	public void setApellidos(String apellido) {
		this.txtFieldApellidos.setText(apellido);
	}

	public JTextField getTxtFieldAliasEmpresa() {
		return txtFieldAliasEmpresa;
	}
	public void setAliasEmpresa(String aliasOempresa) {
		this.txtFieldAliasEmpresa.setText(aliasOempresa);
	}
	public JTextField getTxtFieldEmail() {
		return txtFieldEmail;
	}
	public void setEmail(String email) {
		this.txtFieldEmail.setText(email);
	}
	public JDateChooser getInputFechaNac() {
		return inputFechaNac;
	}
	public void setFechaNac(String fechaNac) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date fecha = formatter.parse(fechaNac);
            this.inputFechaNac.setDate(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	public JComboBox<String> getComboBox() {
		return comboBox;
	}
	public String getOpcionComboBox() {
		return (String) getComboBox().getSelectedItem();
	}

	public String getNumViejo() {
		return numViejo;
	}
	public void setNumViejo(String numViejo) {
		this.numViejo = numViejo;
	}
}
