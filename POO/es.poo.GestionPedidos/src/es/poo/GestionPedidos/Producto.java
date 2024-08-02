package es.poo.GestionPedidos;

//import java.text.DecimalFormat;

public class Producto {
	//DecimalFormat df = new DecimalFormat("#.##");
	//String importeFormateado = df.format(importe);
	public String nombre;
	public float precio;
	public int cantidad;
	
	public Producto() {
		
	}

	public Producto(String nombre, float precio,int cantidad) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
	}
		
	
}
