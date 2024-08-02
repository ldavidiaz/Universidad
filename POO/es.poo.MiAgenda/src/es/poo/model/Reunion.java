package es.poo.model;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Reunion extends Evento {
	@SerializedName("Tipo de reunión")
    private String tipoReunion;		
	//contactos
	@SerializedName("Lista de contactos")
    private ArrayList<String> listaContactos;
    // Ubicación para reuniones presenciales
	@SerializedName("Ubicación")
    private String ubicacion;
    // Sala de reuniones para reuniones presenciales
	@SerializedName("Sala")
    private String salaReunion;
    // Teléfono corto para llamadas telefónicas cortas (por ejemplo, 28006)
	@SerializedName("Telefono corto")
	private String telefonoCorto;
    // Teléfono largo para llamadas telefónicas largas (por ejemplo, 966 99 99 99)
	@SerializedName("Telefono largo")
	private String telefonoLargo;
    // Clave de acceso para llamadas telefónicas con clave (por ejemplo, 99999#)
	@SerializedName("Clave de acceso")
	private String claveAcceso;
    // Tipo de videoconferencia
	@SerializedName("Tipo de videoconferencia")
	private String tipoVideoconferencia;
	// En caso de seleccionar OTRO, especificar cuál
	@SerializedName("Otro tipo de videoconferencia")
    private String otroTipo;
    
    //crear constructor con la superclase

	public Reunion(Evento evento,String tipoReunion,ArrayList<String> listaContactos, String ubicacion,String sala, String telCorto, String telLargo, String claveAcceso,String tipoVideo, String otroTipo) {
    	super(evento.getId(),evento.getTitulo(),evento.getDescripcion(),evento.getFechaEvento(),evento.getHoraDesde(),evento.getFinFechaEvento(),evento.getHoraHasta(),evento.getNotificar(),evento.getNotificaciones());
    	this.tipoReunion = tipoReunion;
    	this.listaContactos = listaContactos;
    	this.ubicacion = ubicacion;
    	this.salaReunion = sala;
    	this.telefonoCorto = telCorto;
    	this.telefonoLargo = telLargo;
    	this.tipoVideoconferencia = tipoVideo;
    	this.claveAcceso = claveAcceso;
    }
    public Reunion() {
    	super();
    }
    @Override 
    public String toString() {
    	return super.toString() + 
    			"  | Tipo de reunion: " + this.tipoReunion +
    			"  | Contactos de la reunion: " + this.listaContactos +
    			"  | Ubicación: " + this.ubicacion +
    			"  | Sala de reunión: " + this.salaReunion + 
    			"  | Teléfono corto: " + this.telefonoCorto +
    			"  | Teléfono largo: " + this.telefonoLargo +
    			"  | Clave de acceso: " + this.claveAcceso +
    			"  | Tipo de videoconferencia: " + this.tipoVideoconferencia +
    			"  | Otro tipo de videoconferencia: " + this.otroTipo;
    }

    /*
	enum TipoReunion {
        PRESENCIAL,
        LLAMADA_TELEFONICA,
        VIDEOCONFERENCIA
    }
	enum TipoVideoconferencia {
	    GOOGLE_MEET,
	    MICROSOFT_TEAMS,
	    ZOOM,
	    SKYPE,
	    OTRO // En este caso especificar cuál en el campo 'otroTipo'
	}
	
	// Enumeración para las salas de reuniones
	enum SalaReunion {
	    SALA_A,
	    SALA_B,
	    SALA_C,
	    OTRA // En este caso especificar el nombre en el campo 'otraSala'
	}
	*/
    public ArrayList<String> getListaContactos() {
		return listaContactos;
	}

    public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getTelefonoCorto() {
		return telefonoCorto;
	}

	public void setTelefonoCorto(String telefonoCorto) {
		this.telefonoCorto = telefonoCorto;
	}

	public String getTelefonoLargo() {
		return telefonoLargo;
	}

	public void setTelefonoLargo(String telefonoLargo) {
		this.telefonoLargo = telefonoLargo;
	}

	public String getClaveAcceso() {
		return claveAcceso;
	}

	public void setClaveAcceso(String claveAcceso) {
		this.claveAcceso = claveAcceso;
	}

	public String getOtroTipo() {
		return otroTipo;
	}

	public void setOtroTipo(String otroTipo) {
		this.otroTipo = otroTipo;
	}
	/*
	@SuppressWarnings("exports")
	public TipoReunion getTipoReunion() {
		return tipoReunion;
	}
	@SuppressWarnings("exports")
	public void setTipoReunion(TipoReunion tipoReunion) {
		this.tipoReunion = tipoReunion;
	}
	@SuppressWarnings("exports")
	public void setSalaReunion(SalaReunion salaReunion) {
		this.salaReunion = salaReunion;
	}
	@SuppressWarnings("exports")
	public SalaReunion getSalaReunion() {
		return salaReunion;
	}
	
	@SuppressWarnings("exports")
	public TipoVideoconferencia getTipoVideoconferencia() {
		return tipoVideoconferencia;
	}
    @SuppressWarnings("exports")
	public void setTipoVideoconferencia(TipoVideoconferencia tipoVideoconferencia) {
		this.tipoVideoconferencia = tipoVideoconferencia;
	}
	*/
	public String getTipoReunion() {
		return tipoReunion;
	}
	public void setTipoReunion(String tipoReunion) {
		this.tipoReunion = tipoReunion;
	}
	public String getSalaReunion() {
		return salaReunion;
	}
	public void setSalaReunion(String salaReunion) {
		this.salaReunion = salaReunion;
	}
	public String getTipoVideoconferencia() {
		return tipoVideoconferencia;
	}
	public void setTipoVideoconferencia(String tipoVideoconferencia) {
		this.tipoVideoconferencia = tipoVideoconferencia;
	}
}
