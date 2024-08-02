package es.poo.model;

import com.google.gson.annotations.SerializedName;

public class Contacto{

    
    private String nombre,apellidos,email,telefono,movil;
    @SerializedName("Fecha de nacimiento")
    private String fechaNac;
    
    
    public Contacto(String nombre,String apellidos,String telefono,String movil,String fechaNac, String email) {
    	this.nombre = nombre;
    	this.apellidos = apellidos;
        this.telefono = telefono;
        this.movil = movil ;
        this.fechaNac = fechaNac;
        this.email = email;        

    }

    public Contacto() {
		// TODO Auto-generated constructor stub
	}
    // Getters y setters

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}


}
