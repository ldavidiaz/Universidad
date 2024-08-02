package es.poo.GestionPedidos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import es.poo.GestionPedidos.Pedido.Estado;

public class GestionPedidos {
	
	//DEFINIR CONSTANTES PARA LOS OUTPUTS
	//MENUS
	private static final String MENU1="#####MENU INICIAL#####\n"+
										"1.Crear clientes\n"+
										"2.Crear Productos\n"+
										"3.Salir\n";
	private static final String MENU2="#####MENU PEDIDOS#####\n"+
										"1.Realizar pedidos\n"+
										"2.Salir\n";
	private static final String MENU3="#####¿TERMINAR PEDIDO?######\n"+
										"1.Pagar\n"+
										"2.Eliminar producto\n"+
										"3.Cancelar todo y salir\n";
	private static final String TIPO_PAGO="1.Efectivo\n"+
										"2.Tarjeta\n"+
										"3.Cuenta\n";
	
	//SOLICITUD DE DATOS AL USUARIO
	private static final String CREAR_NOMBRE_CLIENTE="Nombre: ";
	private static final String CREAR_APELLIDO_CLIENTE = "Apellido: ";
	private static final String CREAR_FECHA_ALTA_CLIENTE = "Fecha de alta: dd/mm/aaaa o deje vacío para la fecha actual: ";
	private static final String CREAR_DIRECCION_CLIENTE = "Direccion: ";
	private static final String CREAR_TELEFONO_CLIENTE = "Telefono: ";
	
	@SuppressWarnings("unused")
	private static final String SOLICITAR_TEL_CLIENTE="Introduce el telefono del cliente: ";
	private static final String SELECCIONAR_PRODUCTO="Seleccione un producto: ";
	private static final String SELECCIONAR_CLIENTE = "Seleccione un cliente(telefono) por el numero que lo identifica\n";
	
	//AVISOS Y ERRORES
	@SuppressWarnings("unused")
	private static final String TELEFONO_ERROR_NOEXISTE="#ERROR.El numero introducido no pertenece a ningun cliente\n";
	private static final String SELECCION_ERROR_PRODUCTO="#ERROR.Debe seleccionar los productos por el numero que los identifica\n";
	private static final String ERROR_CAMPO_VACIO="#ERROR.Este campo no puede estar vacio\n";
	private static final String ERROR_OPCION_INCORRECTA="ERROR.Debe seleccionar las opciones por el numero que las identifica\n";
	private static final String AVISO_MAX_CLIENTES="AVISO. Ya ha alcanzado el maximo de clientes que puede crear(3)\n";
	private static final String AVISO_MAX_PRODUCTOS = "AVISO. Ya ha alcanzado el maximo de productos que puede crear(3)\n";
	private static final String AVISO_TEL_EXISTE="AVISO.Este telefono ya esta dado de alta\n";
	private static final String CREAR_PRECIO_PRODUCTO = "Ingrese el precio del producto: ";
	
	
	//INFO USUARIO
	private static final String INFO_BD_INCOMPLETA="El máximo de clientes o productos aún no han sido agregados";
	
	private static final int MAX_CLIENTES=3,MAX_PRODUCTOS=5;
	
	private ArrayList<Cliente> listaClientes;//verificar cantidad con clientes.size() ==3
	private ArrayList<Producto> listaProductos;//verificar cantidad con productos.size() ==5
	private Cliente cliente;
	private Producto producto;
	private Pedido pedido;
	
	private int clienteElegido;
	private ArrayList<Integer> listaProductosElegidos;
	
	
	public void InicarApp() {//Inicio de la app
		boolean exit = false;
		boolean exitMenu1=false;
		boolean finPrograma=false;
		int nuevoPedido=0;
		//Inicializamos arrayList's clientes y productos
		listaClientes = new ArrayList<>();
		listaProductos = new ArrayList<>();
		listaProductosElegidos = new ArrayList<>();
		do {
			//MENU 1
			if(nuevoPedido==0) {
				while(true) {
					menu1();
					try {
						
			            String opcString = Main.sc.nextLine();
			            int opcNumero = Integer.parseInt(opcString);
			            switch(opcNumero){
				            case 1:
				            	//funcion que solicite y verifique los inputs
				            	//esta funcion se encarga de crear el objeto cliente
				            	crearClientes();	//DONE		       
				            	break;
				            case 2:
				            	crearProductos(); 
				            	break;
				            case 3:
				            	exitMenu1=true;
				            	break;	            
			            }
			        } catch (NumberFormatException e) {
			        	Main.mensaje(ERROR_OPCION_INCORRECTA);
			        }
					if(listaClientes.size()==MAX_CLIENTES && listaProductos.size()==MAX_PRODUCTOS || exitMenu1==true) {
						//Main.mensaje(INFO_BD_INCOMPLETA);
						break;
					}
					Main.mensaje(INFO_BD_INCOMPLETA);
				}			
			}
			
			//Si se crea un nuevo pedido empezar desde aqui
			//MENU 2
			while(true) {
				menu2();
				try {
		            String opcString = Main.sc.nextLine();
		            int opcNumero = Integer.parseInt(opcString);
		            switch(opcNumero){
			            case 1:
			            	//elegimos cliente de una lista de nums de tel
			            	clienteElegido= elegirCliente();
			            	//una vez elegido nos pedira seleccionarProductos(maximo 2 productos diferentes)			         	
			            	break;
			            case 2:
			            	exit=true;
			            	break;	            
		            }
				}catch(NumberFormatException e){
					Main.mensaje(ERROR_OPCION_INCORRECTA);
					
				}
				if(clienteElegido!=-1) {
					break;
				}
			}
			
			//SELECCIONAR PRODUCTOS
			elegirProducto();
			
			//METER en Pedido-->tener en cuenta numcliente para listaClientes.get(numCliente)
			crearPedido();
			
			//Pagar?Eliminar producto? Salir?
			//si paga, generar factura y pasarla al cliente de listaClientes.get(numCliente).addHistorial(Pedido.pago.getFactura);
			while(true) {
				boolean exitmenu=false;
				boolean salir=false;
				menu3();
				try {
				 String opcString = Main.sc.nextLine();
		            int opcNumero = Integer.parseInt(opcString);
		            switch(opcNumero){
			            case 1:
			            	//PAGAR	
			            	elegirTipoPago();
			            	salir=true;
			            	break;
			            case 2:
			            	//ELIMINAR PRODUCTO
			            	eliminarProductoListado();
			            	break;
			            case 3:
			            	//CANCELAR Y SALIR DEL PROGRAMA
			            	exitmenu=true;
			            	break;	            
		            }
		        }catch(NumberFormatException e){
					Main.mensaje(ERROR_OPCION_INCORRECTA);
				}
				if(salir)
					break;
				if(exitmenu) {
					exit=true;
					break;
				}
					
			}
			//resto de funcionalidades fuera del enunciado
			
			//realizar otro pedido? Si No
			while(true) {
				otroPedido();
				try {
					String opcString = Main.sc.nextLine();
					int opcNumero = Integer.parseInt(opcString);
					switch(opcNumero){
						case 1:	//SI
							nuevoPedido++;
							break;
						case 2:
							finPrograma=true;
							secuenciaFinPedido();
							break;
					}
				}catch(NumberFormatException e){
					Main.mensaje(ERROR_OPCION_INCORRECTA);
				}
				break;
			}
			
			if(finPrograma) {
				while(true) {
					finalizarPrograma();
					try {
						String opcString = Main.sc.nextLine();
						int opcNumero = Integer.parseInt(opcString);
						switch(opcNumero){
							case 1:	//SI
								secuenciaFinPedido();
								exit=true;
								break;
							case 2://NO->OTRO PEDIDO
								nuevoPedido++;
								finPrograma=false;
								break;
						}
					}catch(NumberFormatException e){
						Main.mensaje(ERROR_OPCION_INCORRECTA);
					}
					break;	
				}
			}

		}
		while(!exit);
		
	}
	//FUNCIONALIDADES QUE NO SE PIDEN EN LA PRACTICA
	public void otroPedido() {
		Main.mensaje("Realizar otro pedido: [1].SI - [2].NO");
	}
	public void finalizarPrograma() {
		Main.mensaje("¿DESEA FINALIZAR EL PROGRAMA O REALIZAR OTRO PEDIDO? [1].FINALIZAR - [2].OTRO PEDIDO");
	}
	public void secuenciaFinPedido() {
		prepararPedido();
		pedidoListo();
		pedidoServido();
		//TODO LIMPIAR DATOS
	}
	public void prepararPedido() {
		while(true) {
			boolean exitmenu=false;
			Main.mensaje("¿PREPARAR PEDIDO? [1].SI - [2].NO\n");
			try {
				String opcString = Main.sc.nextLine();
	            int opcNumero = Integer.parseInt(opcString);
	            switch(opcNumero){
		            case 1://SI
		            	pedido.estado = Estado.PREPARANDO;
		            	exitmenu=true;
		            	break;
		            case 2://NO
		            	Main.mensaje("AVISO.El cliente esta esperando...");
		            	Thread.sleep(5000);
		            	
		            	break;		            
	            }
	        }catch(NumberFormatException e){
				Main.mensaje(ERROR_OPCION_INCORRECTA);
			} catch (InterruptedException e) {
	            // Manejar interrupciones si es necesario
	            e.printStackTrace();
	        }
			if(exitmenu) {
				break;			
			}
		}
	}
	
	private void pedidoListo() {

		pedido.estado = Estado.LISTO;
		Main.mensaje("El pedido ya esta listo!. En 5 segundos se sirve al cliente...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
            // Manejar interrupciones si es necesario
            e.printStackTrace();
        }

	}
	
	private void pedidoServido() {
		pedido.estado = Estado.SERVIDO;
		Main.mensaje("El pedido ya esta servido!. El cliente se encuentra satisfecho");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
            // Manejar interrupciones si es necesario
            e.printStackTrace();
        }
	}
	
	//FIN FUNCIONALIDADES EXTRAS
	
	private void crearPedido() {
		//pasar cliente
		//pasar productos 
		//crear PEDIDO segun el numero de productos. 1 o 2
		Cliente clienteAux = listaClientes.get(clienteElegido);
		Producto producto1Aux = listaProductos.get(listaProductosElegidos.get(0)-1);
		//int numProductosElegidos = listaProductosElegidos.size();
		float importeProducto1 = producto1Aux.precio*(float)producto1Aux.cantidad;
		if(listaProductosElegidos.size()==1) {
			
			pedido = new Pedido(clienteAux,producto1Aux,importeProducto1);
		}
		else if(listaProductosElegidos.size()==2) {
			Producto producto2Aux = listaProductos.get(listaProductosElegidos.get(1)-1);
			float importeProducto2 = producto2Aux.precio*(float)producto2Aux.cantidad;
			float importeTotal = importeProducto2+importeProducto1;
			pedido = new Pedido(clienteAux,producto1Aux,producto2Aux,importeTotal);
		}
		
	}

	//imprimir los productos del pedido
	private void eliminarProductoListado() {
		while(true) {
			boolean salir=false;
			try {
				Main.mensaje(pedido.toString());
				Main.mensaje("Seleccione el producto que quiere eliminar segun el orden: [1] o [2]\n");
				String opcString = Main.sc.nextLine();
	            int opcNumero = Integer.parseInt(opcString);
	            switch(opcNumero){
		            case 1:
		            	//eliminar producto1 -- ¿Eliminar producto2 o intercambiar?
		            	Producto producto1Aux = listaProductos.get(listaProductosElegidos.get(0)-1);
		            	pedido.eliminarProducto(producto1Aux);
		            	if(listaProductos.size()==2) {
		            		Producto producto2Aux = listaProductos.get(listaProductosElegidos.get(1)-1);
		            		pedido.setProducto1(producto2Aux);
		            		pedido.eliminarProducto(producto2Aux);
		            	}
		            	salir=true;
		            	break;
		            case 2:
		            	//eliminar product2 
		            	Producto producto2Aux = listaProductos.get(listaProductosElegidos.get(1)-1);
		            	pedido.eliminarProducto(producto2Aux);
		            	salir=true;
		            	break;
		            case 3:
		            	break;	            
	            }
		        }catch(NumberFormatException e){
					Main.mensaje(ERROR_OPCION_INCORRECTA);
				}	
			if(salir)
				break;
		}
	}



	private int elegirCliente() {
		
		int numCliente;
		while(true) {
			int i=1;
			Main.mensaje(SELECCIONAR_CLIENTE);
			for(Cliente clienteAux : listaClientes) {	
				System.out.printf("%d.Cliente TELEFONO: %d\n",i,clienteAux.getTelefono());
				i++;
			}
			String opcString = Main.sc.nextLine();
	        int opcNumero = Integer.parseInt(opcString);
	        if(opcNumero>=1 && opcNumero<=5) {
	        	numCliente=opcNumero;
	        	break;
	        }
	        else
				Main.mensaje(ERROR_OPCION_INCORRECTA);        		
		}	
		return numCliente-1;
	}

	private int elegirProducto() { 

        int numProducto;
        int contador=0;
        while(true) {
            int i=1;
            Main.mensaje(SELECCIONAR_PRODUCTO);

            for(Producto productoAux : listaProductos) {
                System.out.printf("%d.Producto %d (%s)\n",i,i,productoAux.nombre);
                i++;
            }
            //String nnumero de Producto;
            int numProductoAux = Integer.parseInt(Main.sc.nextLine());

            if(numProductoAux>=1 && numProductoAux<=5) {
                numProducto = numProductoAux;
                contador++;
                if (contador==1) {
                    //añadimos el producto al arraylist de elegidos
                    listaProductosElegidos.add(numProducto);//es la opcion elegida
                    //aumentamos la cantidad del  Producto,que esta dentro del arraylist de productos
                    listaProductos.get(numProducto-1).cantidad+=1;
                } else if (contador>=1) {

                    if (!listaProductosElegidos.contains(numProducto) && listaProductosElegidos.size()<2) {
                    	
                        listaProductosElegidos.add(numProducto);
                        listaProductos.get(numProducto-1).cantidad+=1;
                    }
                    else if(listaProductosElegidos.contains(numProducto) && listaProductosElegidos.size()<2)
                        listaProductos.get(numProducto-1).cantidad+=1;
                    else if(listaProductosElegidos.size()==2)
                    	Main.mensaje("AVISO.No puede agregar más productos diferentes al pedio.\nSolo puede aumentar la cantidad de los siguientes productos:\n"+
                    listaProductosElegidos.get(0)+". -"+listaProductos.get(listaProductosElegidos.get(0)-1).nombre+"\n"+
                    listaProductosElegidos.get(1)+". -"+listaProductos.get(listaProductosElegidos.get(1)-1).nombre+"\n");
                    

                }

                    Main.mensaje("¿Desea seguir añadiendo productos? [1].SI - [2].NO\n ");
                    String opcStr=Main.sc.nextLine().trim();
                    int opc = Integer.parseInt(opcStr);
                    if(opc==2) {
                        break;
                    }
            }
            else {
                Main.mensaje(SELECCION_ERROR_PRODUCTO);
            }

        }
        return numProducto-1;
    }
	private void elegirTipoPago() {
		
		try {
			Main.mensaje(TIPO_PAGO);
			String opcString = Main.sc.nextLine();
		    int opcNumero = Integer.parseInt(opcString);
		    switch(opcNumero){
		        case 1:
		        	//efectivo
		        	pedido.Pagar(opcNumero);
		        	break;
			    case 2:
			    	//tarjeta
			    	pedido.Pagar(opcNumero);
			    	break;
			    case 3:
			    	//cuenta
			    	pedido.Pagar(opcNumero);
			    	break;	            
            }
        }catch(NumberFormatException e){
			Main.mensaje(ERROR_OPCION_INCORRECTA);
		}
	}
	
	private void crearProductos() {
		if(listaProductos.size()==5) {
			Main.mensaje(AVISO_MAX_PRODUCTOS);
		}
		else {
			String nombre;
			float precio;
			int cantidad=0;

			//NOMBRE
	        while(true) {
				try {
				    Main.mensaje(CREAR_NOMBRE_CLIENTE);
				    nombre = Main.sc.nextLine().toLowerCase();
	
				    // Verificar si el nombre está vacío
				    if (nombre.trim().isEmpty()) {
				        throw new IllegalArgumentException(ERROR_CAMPO_VACIO);
				    }
				    break;
				} catch (IllegalArgumentException e) {
				    System.out.println(e.getMessage());
				    // Puedes decidir qué hacer aquí, como pedir al usuario que ingrese el nombre nuevamente o tomar otra acción
				}
	        }
	       // Main.sc.nextLine();
	        //PRECIO
	        while(true) {
		        try {
		            Main.mensaje(CREAR_PRECIO_PRODUCTO);
		            String precioStr = Main.sc.nextLine().trim();
		            if (!precioStr.matches("^[0-9]+([.,][0-9]{1,2})?$")) {
		            	Main.mensaje("Error: El precio debe tener como máximo dos decimales.");
		            }else {
		            	float precioAux = Float.parseFloat(precioStr);
			            precio =  precioAux;
			            break;
		            }
		            // Validar que el precio tenga como máximo dos decimales
		            
		        } catch (NumberFormatException e) {
		            System.out.println("Error: Ingrese un valor numérico válido.");
		        }
	        }
	        
	        producto = new Producto(nombre,precio,cantidad);
	        listaProductos.add(producto);

		}
        //Main.sc.nextLine();
        /*try {
            Field bufField = Scanner.class.getDeclaredField("buf");
            bufField.setAccessible(true);

            // Obtener el contenido del buffer como una cadena
            char[] buffer = (char[]) bufField.get(Main.sc);
            String bufferContent = new String(buffer);

            System.out.println("Contenido del buffer: " + bufferContent);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }*/
	}



	@SuppressWarnings("unused")
	private void crearClientes() {
		//primero verificar que listaClientes<3
		if(listaClientes.size()==3) {
			Main.mensaje(AVISO_MAX_CLIENTES);
		}
		else {
			//Solicitamos datos al usuario y verificamos
			String nombre,apellido,direccion;
			int telefono;
			String fechaAlta;
			Date fechaIngresada=null;//esta se pasa al objeto cliente
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	        Date fechaActual = new Date();//se pasa esta al objeto cliente si el campo esta vacio
			//String historial --> se pasa cuando se haya pagado y generado un codigo
			boolean nomAux=false,
					apeAux=false,
					dirAux=false,
					telAux=false,
					fechaAux=false,
					fechaActualAux=false;
			//NOMBRE
	        while(true) {
				try {
				    Main.mensaje(CREAR_NOMBRE_CLIENTE);
				    nombre = Main.sc.nextLine().toLowerCase();
	
				    // Verificar si el nombre está vacío
				    if (nombre.trim().isEmpty()) {
				        throw new IllegalArgumentException(ERROR_CAMPO_VACIO);
				    }
				    nomAux=true;
				    break;
				} catch (IllegalArgumentException e) {
				    System.out.println(e.getMessage());
				    // Puedes decidir qué hacer aquí, como pedir al usuario que ingrese el nombre nuevamente o tomar otra acción
				}
	        }
			//APELLIDO
	        while(true) {
				try {
				    Main.mensaje(CREAR_APELLIDO_CLIENTE);
				    apellido = Main.sc.nextLine().toUpperCase();
	
				    // Verificar si el nombre está vacío
				    if (apellido.trim().isEmpty()) {
				        throw new IllegalArgumentException(ERROR_CAMPO_VACIO);
				    }
				    apeAux=true;
				    break;
				} catch (IllegalArgumentException e) {
				    System.out.println(e.getMessage());			
				}
	        }
	        //TELEFONO
	        while(true) {
		        try {
		            Main.mensaje(CREAR_TELEFONO_CLIENTE);
		            String telefonoStr = Main.sc.nextLine().trim();
	
		            // Validar la longitud del número de teléfono
		            if (telefonoStr.length() != 9) {
		                throw new IllegalArgumentException("Error: El número de teléfono debe tener 9 dígitos.");
		            }
	
		            // Validar el primer dígito para determinar si es un móvil o teléfono fijo
		            char primerDigito = telefonoStr.charAt(0);
		            if (primerDigito != '6' && primerDigito != '7' && primerDigito != '8' && primerDigito != '9') {
		                throw new IllegalArgumentException("Error: El primer dígito debe ser 6, 7, 8 o 9.");
		            }
		            
		            //Validar que no este repetido el cliente	            
		            telefono = Integer.parseInt(telefonoStr);
		            for(Cliente clienteAux : listaClientes) {
		            	if(clienteAux.getTelefono()==telefono) {
		            		throw new IllegalArgumentException(AVISO_TEL_EXISTE);
		            	}
		            }	
		            telAux=true;
	 		        break;
		        } catch (IllegalArgumentException e) {
		            System.out.println(e.getMessage());
		            // Puedes decidir qué hacer aquí, como solicitar al usuario que ingrese el número nuevamente o tomar otra acción
		        }

	        }
	        
	        //DIRECCION
	        while(true) {
				try {
				    Main.mensaje(CREAR_DIRECCION_CLIENTE);
				    direccion = Main.sc.nextLine().toUpperCase();

				    // Verificar si la direccion está vacía
				    if (direccion.trim().isEmpty()) {
				        throw new IllegalArgumentException(ERROR_CAMPO_VACIO);
				    }
				    dirAux=true;
				    break;
				} catch (IllegalArgumentException e) {
				    System.out.println(e.getMessage());			
				}
	        }

			
			//FECHA DE ALTA
	        while(true) {
		        try {
		            Main.mensaje(CREAR_FECHA_ALTA_CLIENTE);
		            fechaAlta = Main.sc.nextLine().trim();
	
		            if (fechaAlta.isEmpty()) {
		            	//cliente = new Cliente(nombre,apellido,fechaActual,telefono,direccion);
		            	fechaActualAux = true;
		            	break;
		            } else {
		                // Si el campo no está vacío, intentamos parsear la fecha y comprobamos que no sea superior a la fecha actual
		                fechaIngresada = formatoFecha.parse(fechaAlta);
	
		                if (fechaIngresada.after(fechaActual)) {
		                    System.out.println("Error: La fecha no puede ser superior a la fecha actual.");
		                } else {
		                   // System.out.println("Fecha ingresada: " + formatoFecha.format(fechaIngresada));
		                    // Aquí puedes realizar otras acciones con la fecha ingresada
		                	fechaAux=true;
		                    break;
		                }
		            }
		        } catch (ParseException e) {
		            System.out.println("Error: Formato de fecha incorrecto. Utiliza el formato dd/mm/aaaa.");
		        } 
	        }
	        if(fechaActualAux==true) {
	        	cliente = new Cliente(nombre,apellido,fechaActual,telefono,direccion);
	        }else if(fechaActualAux==false && fechaAux==true) {
	        	cliente = new Cliente(nombre,apellido,fechaIngresada,telefono,direccion);
	        }
	        listaClientes.add(cliente);
			//LLEGADO A ESTE PUNTO 1 CLIENTE HA SIDO CREADO
	        Main.mensaje("\n");
		}	
	}

	public static void menu1() {
		Main.mensaje(MENU1);	
	}
	public static void menu2() {

		Main.mensaje(MENU2);
	}
	public static void menu3() {
		Main.mensaje(MENU3);
	}


	public void setClienteElegido(int clienteElegido) {
		this.clienteElegido = clienteElegido;
	}

}
