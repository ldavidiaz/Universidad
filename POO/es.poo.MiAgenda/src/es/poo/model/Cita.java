package es.poo.model;

import com.google.gson.annotations.SerializedName;

public class Cita extends Evento{
	@SerializedName("Tipo de cita")
    private String tipoCita;
	@SerializedName("Contacto de la cita")
    private String contactoPersonal;
	@SerializedName("Fecha de aniversario del contacto")
    private String fechaAniversario;
	@SerializedName("Ubicación de la cita médica")
    private String ubicacion; // Ubicación para citas médicas
    //private String especialidad; // Especialidad para citas médicas
	@SerializedName("Especialidad médica")
    private String especialidadMedica;
	
    public Cita() {
    	super();
    }
	public Cita(Evento evento,String tipo,String cp, String fechaAniversario, String ubicacion,String especialidad) {
    	super(evento.getId(),evento.getTitulo(),evento.getDescripcion(),evento.getFechaEvento(),evento.getHoraDesde(),evento.getFinFechaEvento(),evento.getHoraHasta(),evento.getNotificar(),evento.getNotificaciones());
    	this.setTipoCita(tipo);
    	this.contactoPersonal = cp;
    	this.fechaAniversario = fechaAniversario;
    	this.ubicacion = ubicacion;
    	this.setEspecialidadMedica(especialidad);
    	//this.especialidad = especialidad;
    }
	@Override
	public String toString() {
		return super.toString() +
				"  | Tipo cita: " + this.tipoCita +
				"  | Contacto personal: " + this.contactoPersonal +
				"  | Fecha de aniversario: " + this.fechaAniversario +
				"  | Ubicación: " + this.ubicacion +
				"  | Especialidad médica: " + this.especialidadMedica;
	}
	/*
	public enum TipoCita {
	    CUMPLEAÑOS,
	    ANIVERSARIO,
	    MEDICO
	}
	// Enumeración para las especialidades médicas
	public enum EspecialidadMedica {
	    DENTISTA,
	    FISIOTERAPEUTA,
	    OFTALMOLOGO,
	    DERMATOLOGO,
	    OTORRINOLARINGOLOGO,
	    // Agrega más especialidades según sea necesario
	}
	*/
	public String getContactoPersonal() {
		return contactoPersonal;
	}
	public void setContactoPersonal(String contactoPersonal) {
		this.contactoPersonal = contactoPersonal;
	}

	public String getFechaAniversario() {
		return fechaAniversario;
	}
	public void setFechaAniversario(String fechaAniversario) {
		this.fechaAniversario = fechaAniversario;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}/*
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}*/
	/*
	public TipoCita getTipoCita() {
		return tipoCita;
	}

	public void setTipoCita(TipoCita tipoCita) {
		this.tipoCita = tipoCita;
	}

	public EspecialidadMedica getEspecialidadMedica() {
		return especialidadMedica;
	}

	public void setEspecialidadMedica(EspecialidadMedica especialidadMedica) {
		this.especialidadMedica = especialidadMedica;
	}*/
	public String getTipoCita() {
		return tipoCita;
	}
	public void setTipoCita(String tipoCita) {
		this.tipoCita = tipoCita;
	}
	public String getEspecialidadMedica() {
		return especialidadMedica;
	}
	public void setEspecialidadMedica(String especialidadMedica) {
		this.especialidadMedica = especialidadMedica;
	}
}
