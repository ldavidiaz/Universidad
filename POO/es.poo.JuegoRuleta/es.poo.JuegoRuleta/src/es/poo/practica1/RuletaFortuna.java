package es.poo.practica1;

import java.io.IOException;
import java.util.ArrayList;

//TODO GUARDAR JUNTO ESTOS VALORES en arrays: JUGADOR-NUMPARTIDA-PUNTUACIONACUM-MODO-FRASE --->(PARA CADA PARTIDA EN CLASE PARTIDA)
public class RuletaFortuna {
	final String CADENA_INICIAR_SESION = "[1]-Iniciar sesion.\n[2]-Registrarme.  ";
	final String CADENA_PARTIDA = "¿Desea introducir el numero de partidas a jugar?\n [1]-Si\n [0]-No ";		
	private static final String CADENA_MODO_JUEGO = "Ingrese modo de juego:\n[1]-FACIL\n[2]-MEDIO\n[3]-EXPERTO";
	private static final String ELEGIR_C = "Elige una consonante:";
	private static final String COMPRAR_V = "COMPRAR VOCAL: [1]SI [0]NO";
	private static final String NOMBRE_JUEGO = "JUEGO DE LA FORTUNA";
	private static final String MENU_FINAL= "[1]-Jugar de nuevo.\n[0]-SALIR.";
	private static final String SUBMENU_FINAL= "-DESEA GUARDAR LA PARTIDA: [1]SI [0]NO";
	private static final String RUTA_FICHERO = "bdJugadores.txt";
	private static final String RUTA_FICHERO2 = "bdPartidas.txt";
	private  ModoJuego modo;
	 
	private String fraseO,fraseE;
	
	private int intentos;
	private static int numPartidas;
	private static int numPartidasActual;
	private static int puntuacionAcum;
	@SuppressWarnings("unused")
	private static boolean esConsonante,esVocal,letraKo = true;
	//OBJETOS PARA ACCEDER A METODOS (Y ATRIBUTOS)
	private static Jugador jugador = new Jugador();
	//private static Jugadores datosJugadores = new Jugadores();
	private static Jugadores datosJugadores =Jugadores.loadFromJson(RUTA_FICHERO);
	private static Partida datosPartida=new Partida();
	private static PartidasPersistencia dtPartidaPersistencia = new PartidasPersistencia();
	//private static PartidasPersistencia dtPartidaPersistencia =  PartidasPersistencia.loadFromJson(RUTA_FICHERO2);
	int opcion,tirada,contador;
	String opcVocal;
	String opcResolver;
	private String consonante,vocal;
	private ArrayList<String> listaConsonante;
	private ArrayList<String> listaVocales;
	protected enum ModoJuego{
		FACIL,MEDIO,EXPERTO;
	}
	private String modo_aux;
	private int intentos_aux;
	
	//CONTROLA EL JUEGO NO LAS PARTIDA
	public void Jugar() throws InterruptedException, IOException 
	{	
		boolean loginOk=true;
		String opc;		
		do {
			Main.mensaje(CADENA_INICIAR_SESION);
			opc = Main.scanIn.nextLine();
			if(opc.equals("1")) {
				if(datosJugadores!=null) {
				jugador.login();
				//PartidasPersistencia.loadFromJson(RUTA_FICHERO2);
				loginOk=true;
				}
			else {
					Main.mensaje("#ERROR. No hay jugadores registrados en la base de datos");
					loginOk=false;
				}
			}
			else if(opc.equals("2")) {
				jugador.signUp();
				Jugadores.loadFromJson(RUTA_FICHERO);
				//datosJugadores = new Jugadores();
				datosJugadores.addJugador(jugador);
				datosJugadores.persistToJson(RUTA_FICHERO);//en partidas es al final en guardatos();
			}
			
			else
				Main.mensaje("#Opcion incorrecta. ");
		}while(!((opc.equals("1") && loginOk==true)|| opc.equals("2")));
		
		datosPartida.introducirNumPartidas(CADENA_PARTIDA);	
		introducirModo();
		
		intentos_aux = intentos;
		numPartidasActual=1; 
		numPartidas = datosPartida.getNumPartidasTotales();
		
		
		int estado=1;

		listaConsonante = new ArrayList<String>();
		listaVocales = new ArrayList<String>();
		
		do {
			contador=0;
			
			switch(estado)
			{	
			case 1:
				if(numPartidasActual==1)
				obtenerFrase();
				else {
					do {
						obtenerFrase();
					}while(Frase.frasesJugadas.contains(fraseE));
				}
				datosPartida.setFraseO(fraseO);	
				encryptFrase();
				estado++;
				break;
			case 2:
				if(fraseE.equals(fraseO))
				{
					esGanador();
					estado=-1;
				}
				if(puedeSeguirJugando())
					estado++;
				else {
					esPerdedor();
					estado=-1;
				}
				break;
			case 3://obtenemos info y puntuacion
				mostrarInfoResultado();
				 tirada=Tirada.Tirar();
				 if(tirada==0) {
					 Main.mensaje("-Esta tirada tiene un valor de: 0 QUIEBRA.(-1 INTENTO)");
					 estado=2;
					 break;
				 }
				 else {
					 Main.mensaje("-Esta tirada tiene un valor de: "+tirada);
					 estado++;
				 }				
				break;
				
			case 4://elegir consonante
				Main.mensaje(ELEGIR_C);
				consonante = Main.scanIn.nextLine().toUpperCase().trim();
				//consonante.toUpperCase().trim();
				esConsonante = Caracter.comprobarC(consonante);
				
				if(esConsonante) 
				{
					if(esRepe(consonante)) 
					{
						desencrypt(consonante);
						if(contador!=0) //si encuentra la letra obtenemos puntuacionAcum y preguntamos si quiere resolver
						{
							puntuacionAcum+=(contador*tirada);
							listaConsonante.add(consonante);
							intentos--;
							mostrarInfoResultado();//QUITAR RESOLVER Y DEJAR QUE SIGA LA SECUENCIA DE LOS CASE
							estado++;
							
						}				
						else
						{
							Main.mensaje("#La consonante no se encuentra en la frase");
							intentos--;
							estado=2;
						}
					}
					else 
					{ 
						Main.mensaje("#La consonante [" +consonante+ "] ya se ha elegido antes");
						estado=2;
					}
				}
				else//#ERROR ENTRADA DE DATOS
				{
					Main.mensaje("#La letra no es una consonante");
					estado=2;
				}
				break;
			case 5://comprar vocal // comprobar si tiene puntos suficientes
				if(puntuacionAcum>0 && puntuacionAcum>=30) 
				{
					Main.mensaje(COMPRAR_V); 
					opcVocal=Main.scanIn.nextLine();
					if(opcVocal.equals("1"))
					{
						Main.mensaje("Comprar vocal: ");
						vocal=Main.scanIn.nextLine().toUpperCase().trim();
						esVocal = Caracter.comprobarV(vocal);
						if(esVocal)
					 	{	
					 		desencrypt(vocal);
							if(contador!=0)//puntuacionAcum+=contador*puntuacion;
							{
								puntuacionAcum-=(30);
								listaVocales.add(vocal);
								mostrarInfoResultado();
								estado++;
							}
							else
							{
								Main.mensaje("#La vocal no se encuentra en la frase");
								intentos--;
								estado=2;
							}	
					 	}
						else
						{
							Main.mensaje("#El valor introducido no es una vocal");
					 		estado=2;
						}
					 	
					} 
					else 
						estado++;
				}
				else {
					//Main.mensaje("No dispone de puntos suficientes para comprar una vocal");
					estado++;
				}
				
				break;
			case 6://resolver
				do {
					try {
						Main.mensaje("Desea resolver: [1]SI [0]NO");
						opcResolver=Main.scanIn.nextLine();
				 				
						if(opcResolver.equals("1"))
						{
							boolean fraseOk=resolver();
							if(fraseOk)
							{
								//esGanador();
								estado=-1;	
								break;
							}
							else {
								estado=2;
							}
						}
						else//no quiere resolver
							estado=2;
					} catch( Exception e){
			            e.printStackTrace();
			        }
				}while(opcResolver.equals("1") && opcResolver.equals("0"));
				break;
			default://fin de partida actual

				datosPartida.setNombreJg(jugador.getAlias());
				datosPartida.setModo(getModo_aux());
				datosPartida.setNumPartidaActual(numPartidasActual);
				datosPartida.setNumPartidaActual(numPartidas);
				datosPartida.setPuntAcumulada(puntuacionAcum);
				PartidasPersistencia.loadFromJson(RUTA_FICHERO2);
				dtPartidaPersistencia.addPartidasPersistencia(datosPartida);
				datosPartida = new Partida();
				Frase.addFrasesJugadas(fraseO);
				puntuacionAcum=0;
				intentos=intentos_aux;
				limpiarLetrasElegidas();
				numPartidasActual++;
				estado=1;
				break;
			}
		}while(numPartidasActual<=numPartidas);
	}
	
	/**
	 * Método que comprueba que la consonante introducida
	 *  ya se ha introducido anteriormente
	 * @param consonante2
	 * @return {@code true} si la consonante no está repetida,
	 * 			{@code false} en caso contrario
	 */
	private boolean esRepe(String consonante2) {
		for(int i=0;i<this.listaConsonante.size();i++)
		{
			if(consonante2.equals(this.listaConsonante.get(i))) {
				intentos--;
				return false;
			}
		}
		return true;
	}
	/**
	 * Muestro por pantalla los siguiente datos 
	 * <p>- 'alias' , 'numero de partida actual'['numero de partidas totales'] , 'puntuación acumuluda'.
	 * <p>- 'frase encriptada'
	 * <p>- 'numero de intentos restantes'.
	 */
	public void mostrarInfoResultado() {
		Main.mensaje("\nJugador: "+jugador.getAlias()+"\t\t"+"Partida:"+numPartidasActual+"["+numPartidas+"]"+"\t\t"+"Puntuacion:" + puntuacionAcum+"\n");
		Main.mensaje("\t\t"+fraseE);
		Main.mensaje("Intentos: "+intentos+"\n");
	}
	
	/**
	 * Método que comprueba si el jugador ha agotado el número de intentos
	 * @return {@code true} si aún quedan intentos, 
	 * 			{@code false} en caso contrario.
	 */
	public boolean puedeSeguirJugando() {
		if(intentos>0)
			return true;
		else
			return false;	
	}
	
	/**
	 * <p>Método que le solicita al jugador que introduzca la frase a resolver,
	 * <p>y comprueba si es correcta la respuesta o no
	 * @return {@code true} si ha adivinado la frase,
	 * 			{@code flase} en caso contrario.
	 */
	public boolean resolver() {
		String solucion; 
		Main.mensaje("Introduzca la frase a resolver:");
		solucion=Main.scanIn.nextLine().toUpperCase().trim();
		if(solucion.equals(fraseO))
			return esGanador();
		else
			Main.mensaje("la frase no es correcta");
		intentos--;
	return false;
	}
	
	/**
	 * Método que sustituye cada letra de la frase original por asteriscos que se almacena en otra variable.
	 */
	private void encryptFrase() {
		fraseE = fraseE.replaceAll("[a-zA-Z]","*");
	}
	
	/**
	 * Método que sustituye una letra que se encuentra
	 * en la frase original, en la posición/es de la frase
	 * encriptada
	 * @param letra
	 */
	private void desencrypt(String letra) {
		char letra_aux = letra.charAt(0);
		
		for(int i=0;i<fraseO.length();i++) {
			if(fraseO.charAt(i)==letra_aux)
			{
				StringBuilder sb = new StringBuilder(fraseE);
				sb.setCharAt(i,letra_aux);
				fraseE = sb.toString();
				contador++;
				letraKo=false;
			}
		}
		if(contador==0)
			letraKo=true;
	}
	
	/**
	 * Añade un string a un ArrayList de consonantes
	 * @param c
	 */
	public void addListaC(String c) {
		listaConsonante.add(c);
	}
	
	/**
	 * Añande un strin a un ArrayList de vocales
	 * @param v
	 */
	public void addListaV(String v) {
		listaVocales.add(v);
	}
	
	/**
	 * Método que duelve un mensaje por pantalla si el jugador a resuelto la frase
	 * @return
	 */
	private boolean esGanador() {
		Main.mensaje("***ENHORABUENA HAS RESUELTO LA FRASE***");
		return true;
	}
	
	/**
	 * Método que devuelve un mensaje por pantalla si el jugador ha agotado el número 
	 * de intentos para resolver la frase
	 * @return
	 */
	private boolean esPerdedor() {
		Main.mensaje("***HAS PERDIDO LA PARTIDA, SE TE ACABARON LOS INTENTOS***");
		return true;
	}
	
	/**
	 * Obtiene una frase de forma aleatoria llamando al método {@code dameFrase()} para la partida actual
	 */
 	public void obtenerFrase() {
 	fraseO = Frase.dameFrase(modo);
 	fraseE = fraseO;
 	}
 	
 	/**
 	 * Método que limpia los datos en los arrays de consonante y vocales elegidas 
 	 * cada vez que inicia una partida nueva
 	 */
 	public void limpiarLetrasElegidas() {
		listaConsonante.clear();
		listaVocales.clear();
	}

 	/**
 	 * Método que solicita al usuario el modo de juego de forma numérica,
 	 * y a su vez introduce el número de intentos por defecto para cada modo.
 	 * <p>- '1' para jugar en modo FACIL
 	 * <p>- '2' para jugar en modo MEDIO
 	 * <p>- '3' para jugar en modo EXPERTO 
 	 */
	public void introducirModo() {
		do {
		Main.mensaje(CADENA_MODO_JUEGO);
		opcion=Integer.parseInt(Main.scanIn.nextLine());
		if(opcion==1)
		{
		 	setModo(RuletaFortuna.ModoJuego.FACIL);
		 	modo_aux="FACIL";
		 	this.intentos = 10;
		}
		else if(opcion==2)
		{
			setModo(RuletaFortuna.ModoJuego.MEDIO);	
			modo_aux="MEDIO";
			this.intentos = 8;
		}
		else if(opcion==3)
		{
			setModo(RuletaFortuna.ModoJuego.EXPERTO);
			modo_aux="EXPERTO";
			this.intentos = 5;
		}
		else
			System.out.println("Opcion incorrecta, intentelo de nuevo.");
		}while(!(opcion==1 || opcion==2 || opcion==3));		
	}
	
	/**
	 * Método que comprueba si el usuario quiere iniciar una nueva partida
	 * o salir del juego.
	 * @return {@code true} si el jugador quiere seguir jugando,
	 * 			{@code false} en caso contrario.
	 */
	public boolean finJuego() {
		String opcfinal;
		do {
			Main.mensaje(MENU_FINAL);
			opcfinal=Main.scanIn.nextLine();
			if(opcfinal.equals("1"))//si juega de nuevo ->solo guardamos en arrays los datos de la partida actual
			{// y si quiere guardarlos se pide despues de hacer esto
				//se guarda los datos del jugador y la partida
				return guardarDatosJuego();//solo guarda datos de la partida
			}
			else
			{
				if(guardarDatosJuego())
					return false;
				else
					return false;
			}
		}while(!(opcfinal.equals("1") || opcfinal.equals("0")));
	}
	
	/**
	 * Comprueba si el jugador quiere guardar los datos de la partida
	 * @return {@code true} si guarda los datos de la partida,
	 * 			{@code false} en caso contrario.
	 */
	private boolean guardarDatosJuego() {
		// TODO Auto-generated method stub
		String opcsave;
		do {
			Main.mensaje(SUBMENU_FINAL); 
			opcsave = Main.scanIn.nextLine();
			if(opcsave.equals("1"))
			{//persistencia de partidas
				dtPartidaPersistencia.persistToJson(RUTA_FICHERO2);

				return true;
			}
			else {
				Main.mensaje("FIN DEL JUEGO.");
				return false;
			}			
		}while(!(opcsave.equals("1") || opcsave.equals("0")));
	}

	public ModoJuego getModo() {
		return modo;
	}

	public void setModo(ModoJuego modo) {
		this.modo = modo;
	}
	public static Jugador getDatosJugador() {
		return jugador;
	}
	public static String getNombreJuego() {
		return NOMBRE_JUEGO;
	}

	public String getModo_aux() {
		return modo_aux;
	}

}