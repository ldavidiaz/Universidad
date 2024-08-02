package es.poo.model;

public class ContactoPersonal extends Contacto {
    private String alias;
    public ContactoPersonal() {
    	super();
    }
    public ContactoPersonal(Contacto contacto,String alias) {
        super(contacto.getNombre(),contacto.getApellidos(),contacto.getTelefono(),contacto.getMovil(),contacto.getFechaNac(),contacto.getEmail());
    	this.alias = alias;
    	setAlias(alias);
    	
    }

    @Override 
    public String toString() {
        return "Nombre y apellidos: " + super.getNombre() + " "+ super.getApellidos() + " - Alias=" + this.alias; 
    }
    
    // Getter y setter
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
}
