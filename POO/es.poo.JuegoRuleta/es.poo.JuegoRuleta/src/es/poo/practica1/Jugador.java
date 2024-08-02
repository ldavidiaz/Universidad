package es.poo.practica1;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public class Jugador {
	private static final String CADENA_ERROR_LOGIN ="USUARIO O CONTRASE�A INCORRECTO";
	private static final String CADENA_REG_USUARIO = "Alias/usuario: ";
	private static final String CADENA_REG_PASS = "Contrase�a: ";
	private static final String rutaFichero="bdJugadores.txt";
	private static final String CADENA_REG_NOMBRE = "Nombre: ";
	private static final String CADENA_REG_FECHANAC = "Fecha de nacimiento dd/mm/aa: ";	
	private  String alias,password,nombre,fechaNac;
	
	private static boolean fechaOk=false,passOk=false,error_login=false;//cambiar a variables de instancia
	private static int edad;

	
	
	
	public Jugador() {

	}
	/**
	 * Constructor para la clase jugador
	 * @param alias
	 * @param password
	 * @param nombre
	 * @param fechaNac
	 */
	public Jugador(String alias,String password, String nombre, String fechaNac) {
		this.alias = alias;
		this.password = password;	
		this.nombre = nombre;
		this.fechaNac = fechaNac;
	}
	
	/**
	 * Guarda el numero de partidas <b> n </b> que se pase por teclado. 
	 * <p>-<b>0 si n = 0</b>
	 * <p>-<b>n = n en otro caso</b>
	 * @param enunciado
	 */

	
	/**
	 * Método para registrar a un usuario. Guarda los valores de 'alias',
	 * 'nombre','contraseña' y 'fecha de nacimiento'.
	 */
	protected  void signUp() 
	{
		alias=imprimir(CADENA_REG_USUARIO);
		nombre=imprimir(CADENA_REG_NOMBRE);
		
		do {
			password=imprimir(CADENA_REG_PASS);
			if(password.length()<6)
				System.out.println("La contraseña debe tener 6 caracteres como minimo");
			else
				passOk=true;
		}while(passOk==false);
		
		do {
			//TODO CONTROLAR DIA/MES NO VALIDOS
			fechaNac=imprimir(CADENA_REG_FECHANAC);
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate fechaNacAux = null;
			try {
				fechaNacAux = LocalDate.parse(fechaNac,fmt);
				LocalDate ahora = LocalDate.now();
				Period periodo = Period.between(fechaNacAux, ahora);
				edad = periodo.getYears();
				if(fechaNacAux.isAfter(ahora)) {
					System.out.println("Fecha Inválida, vuelva a probar.");
					continue;
				}else if(edad<10) 
					System.out.println("Los menores de 10 años no pueden jugar");
				else
					fechaOk=true;
	        } catch (Exception e){ 
	            System.out.println("Fecha Inválida, vuelva a probar.");
	        }			
		}while(fechaOk==false);
		Jugadores nuevoJugador = new Jugadores();
		nuevoJugador.addJugador(RuletaFortuna.getDatosJugador());
		nuevoJugador.persistToJson(rutaFichero);
	}
	
	/**
	 * Método para comprobar los datos('alias' y 'contraseña') de entrada para acceder al juego.
	 * 
	 */
	protected  void login() 
	{//comprobamos entrada de datos

		Jugadores datos = Jugadores.loadFromJson(rutaFichero);
		
		do 
		{
			alias=imprimir(CADENA_REG_USUARIO);
			password=imprimir(CADENA_REG_PASS);
			
			for(int i=0;i<datos.bdJugadores.size();i++) {
				if((datos.bdJugadores.get(i).getAlias().equals(alias)) && (datos.bdJugadores.get(i).getPassword().equals(password)))
				{
						Main.mensaje("BIENVENIDO "+alias);
						error_login=true;
						break;
				}
				else
					error_login=false;
			}
			if(error_login==false)
				Main.mensaje(CADENA_ERROR_LOGIN);
			
		}while(error_login==false);
		
	}

	/**
	 * Método que imprime por pantalla {@code String} <i>cadena</i> 
	 * y devuelve el valor introducido por teclado
	 * @param cadena
	 * @return {@code String}
	 */
	private static  String imprimir(String cadena) {
		System.out.println(cadena);	
		return(Main.scanIn.nextLine());
	}
	
	
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	public int getEdad() {
		return edad;
	}
}
