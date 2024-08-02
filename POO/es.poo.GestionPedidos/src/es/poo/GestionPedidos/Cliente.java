package es.poo.GestionPedidos;

import java.util.ArrayList;
import java.util.Date;

public class Cliente {
	private String nombre;
	private String apellidos;
	private Date fechaAlta;
	private int telefono;
	private String direccion;
	private ArrayList<String> historial=null;
	
	
	public Cliente() {
		
	}


	public Cliente(String nombre, String apellidos, Date fechaAlta, int telefono, String direccion) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaAlta = fechaAlta;
		this.telefono = telefono;
		this.direccion = direccion;
		this.historial = new ArrayList<>();
	}


	public void addHistorial(String codigo) {
		this.historial.add(codigo);
	}
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public Date getFechaAlta() {
		return fechaAlta;
	}


	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getHistorial() {
		StringBuilder sb = new StringBuilder();
		for(String hs : historial) {
			sb.append("-").append(hs+"\n");
		}
		return sb.toString();
	}


	public void setHistorial(String historial) {
		this.historial.add(historial);
	}


	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", apellidos=" + apellidos + ", fechaAlta=" + fechaAlta + ", telefono="
				+ telefono + ", direccion=" + direccion + ", historial=" + historial + "]";
	}
		

}
