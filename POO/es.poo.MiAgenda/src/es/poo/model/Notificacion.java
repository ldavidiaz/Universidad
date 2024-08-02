package es.poo.model;

import com.google.gson.annotations.SerializedName;

public class Notificacion {
	//private int idNotificacion;
	@SerializedName("Tiempo antes del evento")
    private String tiempoAntesEvento; // Cantidad de tiempo antes del evento para la notificación (minutos, horas, días, semanas, etc.)
	@SerializedName("MINUTOS/HORAS/DIAS/SEMANAS")
	private TipoTiempo tipoTiempo;
    @SerializedName("Tipo de notificacion:")
    private TipoNotificacion tipoNotificacion; // Tipo de notificación (popup, SMS, correo, otro)

    public enum TipoNotificacion {
        POPUP,
        SMS,
        CORREO
    }
    public enum TipoTiempo{
    	MINUTOS,
    	HORAS,
    	SEMANAS,
    	DIAS
    }
    // Constructor

	public Notificacion(String tiempoAntesEvento,TipoTiempo tipoTiempo, TipoNotificacion tipoNotificacion) {
        this.setTiempoAntesEvento(tiempoAntesEvento);
        this.setTipoNotificacion(tipoNotificacion);
    }
    
    public Notificacion() {
    	
    }
    @Override
    public String toString() {
        return "Tipo de notificación: "+this.tipoNotificacion+ "  |  Avisar: " + this.tiempoAntesEvento + "  " + this.tipoTiempo + "  ANTES";
    }

	public String getTiempoAntesEvento() {
		return tiempoAntesEvento;
	}
	public void setTiempoAntesEvento(String tiempoAntesEvento) {
		this.tiempoAntesEvento = tiempoAntesEvento;
	}

	public TipoNotificacion getTipoNotificacion() {
		return tipoNotificacion;
	}

	public void setTipoNotificacion(TipoNotificacion tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}

	public TipoTiempo getTipoTiempo() {
		return tipoTiempo;
	}

	public void setTipoTiempo(TipoTiempo tipoTiempo) {
		this.tipoTiempo = tipoTiempo;
	}
}
