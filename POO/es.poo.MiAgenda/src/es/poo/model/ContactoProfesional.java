package es.poo.model;

public class ContactoProfesional extends Contacto {
    private String empresa;
    
    public ContactoProfesional() {
    	super();
    }
    public ContactoProfesional(Contacto contacto,String empresa) {
        super(contacto.getNombre(),contacto.getApellidos(),contacto.getTelefono(),contacto.getMovil(),contacto.getFechaNac(),contacto.getEmail());
    	this.empresa = empresa;
    }
    public String toString() {
        return super.toString() + " empresa=" + this.empresa; 
    }
    // Getter y setter de la empresa

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
