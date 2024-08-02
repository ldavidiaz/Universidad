package es.poo.GestionPedidos;

import java.util.Date;

//import java.util.Date;

public class PasarelaPago {
	
	private float importe;
	private long codigoPago;
	
	public PasarelaPago() {
		
	}
	public PasarelaPago(float importeTotal) {
		
		
		this.importe = importeTotal;
	}
	
	protected long Efectivo(){
		
		generarCodigo();
		this.importe=0;
		return this.codigoPago;
	}
	
	protected long Tarjeta(String numTarjeta) {
		if(validarTajertaCuenta(numTarjeta)) {
			generarCodigo();
			this.importe=0;
			return this.codigoPago;
		}
		else {
			Main.mensaje("El numero de tarjeta no es valido");
		}		
		return 0;
	}
	
	protected long Cuenta(String cuenta) {
		if(validarTajertaCuenta(cuenta)) {
			generarCodigo();
			this.importe=0;
			return this.codigoPago;
		}
		else {
			Main.mensaje("El numero de cuenta no es valido");
		}		
		return 0;
	}
	
	private long generarCodigo() {
		this.codigoPago=new Date().getTime();
		
		return this.codigoPago;
	}
	private boolean validarTajertaCuenta(String numTarjeta) {
		// Eliminar espacios en blanco y verificar si la longitud es la esperada
        String numeroLimpio = numTarjeta.trim();
        if (numeroLimpio.length() != 16) {
            return false;
        }

        // Verificar que todos los caracteres sean dígitos
        for (char c : numeroLimpio.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        // Si pasa las validaciones anteriores, se considera válido
        return true;
	}
	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public long getCodigoPago() {
		return codigoPago;
	}

	public void setCodigoPago(long codigoPago) {
		this.codigoPago = codigoPago;
	}
	
}
