import time
from random import randrange,choice,random
from cola import Cola
from cliente import Cliente
from sala import Sala
def mostrar_menu(opciones):
    print('Seleccione una opción:')
    for clave in sorted(opciones):
        print(f' {clave}) {opciones[clave][0]}')


def leer_opcion(opciones):
    while (a := input('Opción: ')) not in opciones:
        print('Opción incorrecta, vuelva a intentarlo.')
    return a


def ejecutar_opcion(opcion, opciones):
    opciones[opcion][1]()


def generar_menu(opciones, opcion_salida):
    opcion = None
    while opcion != opcion_salida:
        mostrar_menu(opciones)
        opcion = leer_opcion(opciones)
        ejecutar_opcion(opcion, opciones)
        print()


def menu_principal():
    opciones = {#1,2,3,4,5 - 7 OK
    '1': ('Insertar un mienmbro en una cola:1,2,3', lambda: manipularDatos(1)),
    '2': ('Sacar el primer cliente de cualquier cola: 1,2,3',lambda: manipularDatos(2)),
    '3': ('Imprimir cualquier cola: 1,2,3',lambda: manipularDatos(3)),#MUESTRA TODOS LOS ID DE UNA COLA
    '4': ('Consultar que clientes estan esperando en cualquier cola: 1,2,3',lambda: manipularDatos(4)),#MUESTRA TODOS LOS DATOS DE LOS CLIENTES EN LAS COLAS
    '5': ('Ver información sobre un cliente',datos_id),#MUESTRA LA INFORMACION DE UN CLIENTE A TRAVES DE UN ID SIN ESPECIFICAR LA COLA, MOSTRAR LA COLA
    '6': ('Iniciar la simulación',simulacion),
    '7': ('Salir de la aplicación',salir)
    }
    generar_menu(opciones, '7')

###################################
#    FUNCIONES DEL MENU 1-2-3     #
###################################
#USO DE PLANTILLAS LITERALES(F-STRINGS) PARA AHORRAR LINEAS DE CODIGO
#(ya que se puede hacer esto en javascript imaginé que en python también)

def switch_case(numCola,case):
    
    if case==1:#INTRODUCIMOS DATOS DEL PASAJERO
        cliente = Cliente.añadir_datos()
    #INTRODUCIMOS DATOS A LA COLA SELECCIONADA
    match case:
        case 1:#INSERTAR ELEMENTOS -- comprobar que no se repitan elementos
            colas[numCola-1].insertarElemento(cliente)
        case 2:#BORRAR ELEMENTO -- OK
            print(f"COLA {numCola}:",end="\t\t")
            colas[numCola-1].mostrarCola()
            if not colas[numCola-1].colaVacia():
                print("Eliminando...\n")
                item = colas[numCola-1].borrarElemento()
                print(f"Cola {numCola}:",end="\t\t")
                colas[numCola-1].mostrarCola()
                print(f"Ha salido de la cola {numCola}:    {item.id}")
        case 3:#MOSTRAR COLA -- OK
            print(f"COLA {numCola}:",end="\t\t")   
            colas[numCola-1].mostrarCola()
        case 4:#MOSTRAR TODOS LOS DATOS DE LOS CLIENTES EN UNA COLA
            print("(ID ; NOMBRE ; EDAD ; GENERO ; TIPO ; ENTRADA)")
            #print(f"COLA {numCola}:",end="\t\t")
            colas[numCola-1].mostrarDatosCola()

def manipularDatos(case):##Elige una cola
    numCola = input("Elige una cola: \n1.Cola 1\n2.Cola 2\n3.Cola 3\n4.Volver atras\n")
    if numCola!="4":
        print("Has elegido la cola ",numCola)
        switch_case(int(numCola),case)

def datos_id():
    while True:
        id_solicitado = input("Introduzca ID: ")
        if len(id_solicitado) == 6:
            break
        else:
            print("La entrada no es válida. Debe tener 6 caracteres.")
    id_encontrado = False
    for numCola, cola in enumerate(colas):
        try:
            item = cola.accederAtributos()
            if item:
                for cliente in item:
                    if cliente.id == id_solicitado:
                        print(f'''
Cola actual: {numCola+1}
Información ID: {id_solicitado}
Nombre: {cliente.nombre if hasattr(cliente, 'nombre') else 'N/A'}
Edad: {cliente.edad if hasattr(cliente, 'edad') else 'N/A'}
Genero: {cliente.genero if hasattr(cliente, 'genero') else 'N/A'}
Tipo de cine favorito: {cliente.tipo if hasattr(cliente, 'tipo') else 'N/A'}
Estado de la entrada: {cliente.entrada if hasattr(cliente, 'entrada') else 'N/A'}      
                        ''')
                        id_encontrado=True
                        break
        except:
            pass
        if id_encontrado:
            break
    if not id_encontrado:
        print("El ID introducido no se encuentra en ninguna cola")

###################################
#   FUNCIONES DE LA SIMULACION    #
###################################
def imprimirColas():
    for i in range(3):
        print(f"Cola {i+1}:", end="\t")
        colas[i].mostrarCola()
        
def inicSim():
    print("Inicializando datos...")
    cliente1 = Cliente.nuevoDato()
    cliente2 = Cliente.nuevoDato()
    cliente3 = Cliente.nuevoDato()
    colas[0].insertarElemento(cliente1)
    colas[1].insertarElemento(cliente2)
    colas[2].insertarElemento(cliente3)
    imprimirColas()
    '''
    COLA 1: 123456
    COLA 2: 234567
    COLA 3: 345678
    '''
    print("EVENTO:\t\t") # "EVENTO:     "
    time.sleep(5)



def simulacion():    
    salaA = Sala()
    salaB = Sala()
    salaC = Sala()
    Cola_salas = [salaA,salaB,salaC]
    
    inicSim()#INICIALIZA LA SIMULACION CON UN PROMPT DE LAS COLAS Y EVENTO(VACIO)  
    
    case = 1 #DEFAULT
    numPersonas_max = 10
    numCola_evento=0
    numCola_random=0
    nuevoCliente = None
    item = None
    while case <= 6:
        match case:
            case 1:
                #Comprobar si las salas estan llenas,si estan llenas fin de simulacion
                if all(len(sala.items) == 10 for sala in Cola_salas):
                    print("Todas las Salas están llenas, fin de la simulación...")
                    case = 7
                else:
                    # Elegimos una cola al azar
                    numCola_random = choice([0,1,2])
                    case += 1
            case 2:#Generamos dato random en la cola. 
                #COMPROBAR QUE EL NUEVOCLIENTE.ID NO ESTE REPETIDO EN NINGUNA COLA
                nuevoCliente = Cliente.nuevoDato()
                colas[numCola_random].insertarElemento(nuevoCliente)
                case +=1
            case 3:#Mostramos las colas
                print()
                imprimirColas()
                case += 1
            case 4:#Mostramos el evento de llegada
                print(f'Evento:\tLlega cliente {nuevoCliente.id} a la Cola {numCola_random+1}\n')
                case+=1
            case 5:#Imprimimos cola y evento despues de salir un cliente
                time.sleep(1)
                #Elegimos cola al azar para el evento
                
                numCola_evento = choice([0,1,2])
                if colas[numCola_evento].colaVacia():
                    imprimirColas()
                    print(f'Evento:\t No hay clientes para proceder en la cola {numCola_evento+1}\n')
                else:
                    item = colas[numCola_evento].borrarElemento()        
                    letra = 'A' if numCola_evento == 0 else 'B' if numCola_evento == 1 else 'C'              
                    if item.entrada == 0 or item.entrada == 1:# PUEDE ENTRAR A LA SALA
                        if Cola_salas[numCola_evento].numPersonas < numPersonas_max:
                            Cola_salas[numCola_evento].insertarCliente(item)
                            imprimirColas()
                            print(f'Evento:\tEntrada cliente {item.id} para la Sala {letra}')
                        else:# Encontrar otra sala disponible
                            mover_otraCola = numCola_evento
                            while len(Cola_salas[mover_otraCola].items)==numPersonas_max:
                                mover_otraCola = choice([0,1,2])
                                
                            Cola_salas[mover_otraCola].insertarCliente(item)
                            letra_nueva_sala = 'A' if mover_otraCola == 0 else 'B' if mover_otraCola == 1 else 'C'
                            imprimirColas()
                            print(f'Evento:\tEntrada cliente {item.id} para la Sala {letra_nueva_sala} (Insastifecho, {letra})')
                    #Fallos
                    elif item.entrada!=0 and item.entrada!=1:
                        if random() <= 0.05:#FALLO DE TARJETA
                            if Cola_salas[numCola_evento].numPersonas < numPersonas_max:#SI PUEDE VOLVER A SU COLA
                                colas[numCola_evento].insertarElemento(item)
                                imprimirColas() 
                                print(f'Evento:\tError de venta al cliente {item.id}')                                
                        else:#ERROR DE VENTA-Otra cola
                            mover_otraCola = numCola_evento
                            while len(Cola_salas[mover_otraCola].items)==numPersonas_max or mover_otraCola==numCola_evento:
                                mover_otraCola = choice([0,1,2])
                            colas[mover_otraCola].insertarElemento(item)
                            imprimirColas()
                            print(f'Evento:\tError de venta al cliente {item.id} ')
                case +=1
            case 6:
                print(f'SALA A: {Cola_salas[0].numPersonasSala()} personas | SALA B: {Cola_salas[1].numPersonasSala()} personas | SALA C: {Cola_salas[2].numPersonasSala()} personas ')
                time.sleep(5)
                case = 1

def salir():
    print('Saliendo...')


if __name__ == '__main__':
    cola1 = Cola()
    cola2 = Cola()
    cola3 = Cola()
    colas =   [cola1,cola2,cola3]
    menu_principal()