package es.poo.GestionPedidos;

public class Pedido {
	private Cliente cliente;
	private Producto producto1;
	private Producto producto2=null;
	private float importeTotal;
	private PasarelaPago pago;
	public Estado estado;
	
	public Pedido() {
		
	}
	public Pedido(Cliente cliente,Producto producto1,float importeTotal) {
		this.cliente = cliente;
		this.producto1 = producto1;
		this.importeTotal=importeTotal;
	}
	public Pedido(Cliente cliente,Producto producto1,Producto producto2,float importeTotal) {
		this.cliente = cliente;
		this.producto1 = producto1;
		this.producto2 = producto2;
		this.importeTotal=importeTotal;
	}
	public static enum Estado{
		PAGADO,//1
		PREPARANDO,//2
		LISTO,//3
		SERVIDO//4
	}
	
	public void Pagar(int tipoPago) {
		//1-Efectivo 2-Tarjeta 3-Cuenta
		//Crear el objeto pago,pasarle el importeTotal y segun tipoPago usar una funcion u otra
		
		pago = new PasarelaPago(importeTotal);
		switch(tipoPago) {
			case 1:
				pago.Efectivo();
				estado = Estado.PAGADO;
				cliente.addHistorial(String.valueOf(pago.getCodigoPago()));
				break;
			case 2:
				while(true) {
					Main.mensaje("Introduce el numero de tarjeta");
					String tarjeta = Main.sc.nextLine();
					if(pago.Tarjeta(tarjeta)!=0) {
						estado = Estado.PAGADO;
						cliente.addHistorial(String.valueOf(pago.getCodigoPago()));
						break;
					}
				}
				break;
				
			case 3:
				while(true) {
					Main.mensaje("Introduce el numero de cuenta");
					String cuenta = Main.sc.nextLine();
					if(pago.Cuenta(cuenta)!=0) {
						estado = Estado.PAGADO;
						cliente.addHistorial(String.valueOf(pago.getCodigoPago()));
						break;
					}
				}
				break;
			default:
				//imprimir error
				break;
		}
		if(pago.getCodigoPago()!= 0) {
			//cambiar estado
			//controla que no se pueda volver a llamar la funcionar Pagar mediante el estado
			estado = Estado.PAGADO;
			//fuera de esta clase una vez se pague, se debe cambiar el estado a PREPARANDO
		}
	}
	
	public void agregarCliente(Cliente cliente) {
		this.setCliente(cliente);
	}
	public void agregarProducto1(Producto producto) {
		this.producto1 = producto;		
	}
	
	public void agregarProducto2(Producto producto) {
		if(producto1 != null) {
			this.producto2 = producto;
		}else {
			//mensaje error -> volver atrás 
		}
	}
	
	public void eliminarProducto(Producto producto) {// ¿se puede eliminar si esta pagado? NO
		if(estado == null) {
			if(producto.equals(producto1)) {
				producto1 = null;
			}
			else if(producto.equals(producto2)) {
				producto2=null;
			}	
		}
		else {
			//no se puede eliminar producto
		}

	}

	@Override
	public String toString() {
		/*return "Pedido [producto1=" + producto1 + ", producto2=" + producto2 + ", importeTotal=" + importeTotal
				+ ", pago=" + pago + "]";*/
		String precio1 = String.format("%.2f", (float) producto1.cantidad * producto1.precio);
        String producto1Info = String.format("%5s$-%-8d%-25s%-17s%-10s%n","1", producto1.cantidad, producto1.nombre, precio1, precio1);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%5s$-%-8s%-25s%-17s%-10s%n", "ORDEN","CANT.", "PRODUCTO", "PRECIO UD.", "TOTAL"));
        stringBuilder.append("=====   =====   =========   =========   =====\n");
        //stringBuilder.append("  Producto 1:\n");
        stringBuilder.append(producto1Info+"\n");

        if (producto2 != null) {
            String precio2 = String.format("%.2f", (float) producto2.cantidad * producto2.precio);
            String producto2Info = String.format("%5s$-%-8d%-25s%-17s%-10s%n", "2",producto2.cantidad, producto2.nombre, precio2, precio2);
            //stringBuilder.append("  Producto 2:\n");
            stringBuilder.append(producto2Info+"\n");
        }

        stringBuilder.append("TOTAL -------------------------------------------->").append(importeTotal).append("\n");
        return stringBuilder.toString();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Producto getProducto1() {
		return producto1;
	}
	public void setProducto1(Producto producto1) {
		this.producto1 = producto1;
	}
	public Producto getProducto2() {
		return producto2;
	}
	public void setProducto2(Producto producto2) {
		this.producto2 = producto2;
	}

}
