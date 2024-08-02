package es.poo.practica1;

//import java.util.ArrayList;

public class Partida {
	//ArrayList<Jugador> listJugador = new ArrayList<Jugador>();
	private static final String CADENA_NUM_PARTIDAS = "Ingrese numero de partidas a jugar: ";
	//private static final String RUTA_FICHERO ="bdPartidas.txt";
	private String nombreJg;
	@SuppressWarnings("unused")
	private String juego="JUEGO DE LA FORTUNA";
	private String modo;
	private int numPartidaActual;
	private int numPartidasTotales;//convertir en arrays
	private int puntAcumulada;
	private String fraseO;
	private static int opcion;

	public Partida() {}
	/*public Partida(String nombreJugador, String frase, S) {
		//super();
	}*/
	
	/**
	 * Método para introducir el número de partidas del juego
	 * @param enunciado
	 */
	public void introducirNumPartidas(String enunciado) {
		Main.mensaje(enunciado);//Desae introducir el numero de partidas a jugar? [1]-Si|\n [2]-No
		opcion=Integer.parseInt(Main.scanIn.nextLine());
		if(opcion==0) 
			setNumPartidasTotales(3);
		else {
			System.out.println(CADENA_NUM_PARTIDAS);
			setNumPartidasTotales(Integer.parseInt(Main.scanIn.nextLine()));
		}
	}

	public int getPuntAcumulada() {
		return puntAcumulada;
	}
	public void setPuntAcumulada(int puntAcumulada) {
		this.puntAcumulada = puntAcumulada;
	}


	public String getNombreJg() {
		return nombreJg;
	}


	public void setNombreJg(String nombreJg) {
		this.nombreJg = nombreJg;
	}


	public String getModo() {
		return modo;
	}


	public void setModo(String modo) {
		this.modo = modo;
	}


	public String getFraseO() {
		return fraseO;
	}


	public void setFraseO(String fraseO) {
		this.fraseO = fraseO;
	}

	public int getNumPartidaActual() {
		return numPartidaActual;
	}

	public void setNumPartidaActual(int numPartidaActual) {
		this.numPartidaActual = numPartidaActual;
	}

	public int getNumPartidasTotales() {
		return numPartidasTotales;
	}

	public void setNumPartidasTotales(int numPartidasTotales) {
		this.numPartidasTotales = numPartidasTotales;
	}

	//QUE CONTIENE EXACTAMENTE ESTA CLASE
}
