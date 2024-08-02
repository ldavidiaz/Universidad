package es.poo.model;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Evento {
	@SerializedName("ID")
	private int id;
	@SerializedName("Titulo")
	private String titulo;
	@SerializedName("Descripcion")
	private String descripcion;
	@SerializedName("Fecha inicio del evento")
	private String fechaEvento;
	@SerializedName("Desde hora")
	private String horaDesde;
	@SerializedName("Fecha fin del evento")
	private String finFechaEvento;
	@SerializedName("Hasta hora")
	private String horaHasta;
	@SerializedName("Notificar")
	private String notificar;
	@SerializedName("Lista de notificaciones")
	private ArrayList<Notificacion> notificaciones;
	
	public Evento() {
	}
	public Evento(int id,String titulo, String descripcion, String fechaEvento, String horaDesde,String finFecha, String horaHasta, String notificar,ArrayList<Notificacion> notificaciones) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaEvento = fechaEvento;
		this.horaDesde = horaDesde;
		this.finFechaEvento = finFecha;
		this.horaHasta = horaHasta;
		this.notificar = notificar;
		this.notificaciones = notificaciones; 
	}
	@Override
	public String toString() {
		return "ID: " + this.id +
				"  | Titulo: " + this.titulo +
				"  | Descripcion: " + this.descripcion +
				"  | Fecha inicio del evento: " + this.fechaEvento + " / " + this.horaDesde +
				"  | Fecha fin del evento: " + this.finFechaEvento + " / " + this.horaHasta +
				"  | Notificar: " + this.notificar +
				"  | Notificaciones: " + this.notificaciones;			
	}
	
	public String getFechaEvento() {
		return fechaEvento;
	}
	public void setFechaEvento(String fechaEvento) {
		this.fechaEvento = fechaEvento;
	}
	public String getHoraDesde() {
		return horaDesde;
	}
	public void setHoraDesde(String horaDesde) {
		this.horaDesde = horaDesde;
	}
	public String getHoraHasta() {
		return horaHasta;
	}
	public void setHoraHasta(String horaHasta) {
		this.horaHasta = horaHasta;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNotificar() {
		return notificar;
	}
	public void setNotificar(String notificar) {
		this.notificar = notificar;
	}
	public ArrayList<Notificacion> getNotificaciones() {
		return notificaciones;
	}
	public void addNotificaciones(Notificacion notificacion) {
		this.notificaciones.add(notificacion);
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public String getFinFechaEvento() {
		return finFechaEvento;
	}
	public void setFinFechaEvento(String finFechaEvento) {
		this.finFechaEvento = finFechaEvento;
	}

}
