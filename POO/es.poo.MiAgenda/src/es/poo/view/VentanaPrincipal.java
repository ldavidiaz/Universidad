package es.poo.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JSeparator;

import java.io.IOException;
import java.io.File;
import java.awt.Panel;
import java.awt.Label;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import es.poo.controller.VentanasController;

import java.awt.Cursor;

import fonts.Fuente;

@SuppressWarnings({ "unused", "serial" })
public class VentanaPrincipal extends JFrame {
	
	private JPanel contentPane;
	private JButton botonMostrarContacto;
    private JButton botonMostrarEvento;
	Fuente tipoFuente;
	
	public VentanaPrincipal(VentanasController controller){
		setResizable(false);
		tipoFuente = new Fuente();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		//contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		botonMostrarContacto = new JButton("CONTACTOS");
		botonMostrarContacto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonMostrarContacto.setBorderPainted(false);
		botonMostrarContacto.setBorder(new LineBorder(null, 4, true));
		botonMostrarContacto.setBackground(new Color(153, 153, 153));
		botonMostrarContacto.setForeground(new Color(255, 255, 255));
		botonMostrarContacto.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT,1,18));
		botonMostrarContacto.setBounds(317, 252, 150, 40);
		botonMostrarContacto.addActionListener(controller);
		
		botonMostrarEvento = new JButton("EVENTOS");
		botonMostrarEvento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonMostrarEvento.setBorderPainted(false);
		botonMostrarEvento.setBorder(new LineBorder(null, 4, true));
		botonMostrarEvento.setBackground(new Color(153, 153, 153));
		botonMostrarEvento.setForeground(new Color(255, 255, 255));
		botonMostrarEvento.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT,1,18));
		botonMostrarEvento.setBounds(317, 309, 150, 40);
		botonMostrarEvento.addActionListener(controller);
		contentPane.add(botonMostrarEvento);
		contentPane.add(botonMostrarContacto);
		
		JLabel lblFechaApp = new JLabel("2023");
		lblFechaApp.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaApp.setForeground(new Color(255, 255, 240));
		lblFechaApp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaApp.setBounds(70, 480, 644, 20);
		contentPane.add(lblFechaApp);
		
		JLabel lblTituloApp = new JLabel("MI AGENDA");
		lblTituloApp.setForeground(new Color(255, 255, 255));
		lblTituloApp.setFont(tipoFuente.fuente(tipoFuente.MINECRAFT,1,72));
		lblTituloApp.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloApp.setBounds(175, 92, 433, 79);
		contentPane.add(lblTituloApp);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 511, 764, 8);
		contentPane.add(separator);
		
		JLabel lblAutorApp = new JLabel("Luis David Diaz Mesa |  -Q");
		lblAutorApp.setForeground(new Color(255, 255, 240));
		lblAutorApp.setBounds(10, 530, 348, 20);
		contentPane.add(lblAutorApp);
		
		JLabel lblBgInicio = new JLabel("");
		lblBgInicio.setVerticalAlignment(SwingConstants.TOP);
		lblBgInicio.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Welcome.png")));
		lblBgInicio.setBounds(-1, 0, 785, 561);
		contentPane.add(lblBgInicio);
	}

    public JButton getBotonMostrarContacto() {
        return botonMostrarContacto;
    }

    public JButton getBotonMostrarEvento() {
        return botonMostrarEvento;
    }
}
