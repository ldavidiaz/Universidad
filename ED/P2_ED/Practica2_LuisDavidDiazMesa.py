import time
from random import choice
from arbol import AVLTree
from cliente import Cliente
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
'''
Nota: los ID's en la func 6 pueden repetirse, por lo que no se comprueban
cuando los crea.
1->manipularDatos v2 -- OK
2->datos_id v2 --> OK
3->manipularDatos v2 -- OK
4->datos_id v2 -> OK
5->manipularDatos v2 --> OK
6->manipularDatos v2: !IS_REP = TRUE --> comprueba todas las lista_arboles? --> OK?
7->simulacion v2 -> OK
'''

def menu_principal():
    opciones = {#1,2,3,4,5 - 7 OK
    '1': ('Insertar un miembro en un ABB: 1,2,3', lambda: manipularDatos(1)),
    '2': ('Buscar cualquier espectador en un ABB: 1,2,3',lambda: manipularDatos(2)),
    '3': ('Imprimir lista de espectadores en cualquier ABB indicado recorridos de cualquier forma: anchura, profundidad (PRE, IN, POST ORDEN)',lambda: manipularDatos(3)),
    '4': ('Borrar un espectador, cuya tarjeta haya sido introducida por teclado, si existe en el correspondiente ABB',lambda: manipularDatos(4)),
    '5': ('Introducir un número y crear dicha cantidad de espectadores al azar, guardando en el correspondiente ABB estos espectadores: 1,2,3',lambda: manipularDatos(5)),
    '6': ('Introducir un número y generar al azar dicha cantidad de espectadores, que se borrarán, si existen, en los correspondientes ABB',function6),
    '7': ('Iniciar la simulación',simulacion),
    '8': ('Salir de la aplicación',salir)
    }
    generar_menu(opciones, '8')

###################################
#    FUNCIONES DEL MENU 1-2--4    #
###################################
#USO DE PLANTILLAS LITERALES(F-STRINGS) PARA AHORRAR LINEAS DE CODIGO

def switch_case(numArbol,case):

    if case==1:#INTRODUCIMOS DATOS DEL PASAJERO
        cliente = Cliente.añadir_datos()
    #INTRODUCIMOS DATOS A LA COLA SELECCIONADA
    match case:
        case 1:#INSERTAR ELEMENTOS 
            lista_arboles[numArbol-1].insert(cliente)
        case 2: ##2 = buscar un espectador en un arbol-- 4?
            datos_id(numArbol,case)
        case 4:
            datos_id(numArbol,case)
        case 5:#GENERAMOS i Numeros al azar y lo introducimos en el arbol seleccionado
            function5(numArbol-1)
            '''
            numDatos = input("Introduce el número de espectadores a crear de forma aleatoria: ")
            i = numDatos
            while i > 0 :
                cliente.gen_datos(IS_REP)
                lista_arboles[numArbol-1].insert(cliente)
                i-=1
            '''
            
def function5(numArbol):
    numDatos = int(input("Introduce el número de espectadores a crear de forma aleatoria: "))
    i = numDatos

    while i > 0 :        
        lista_arboles[numArbol].insert(Cliente.gen_datos(IS_REP))
        i-=1               
 


def function6():
    
    # Pedir el número de clientes a crear de forma aleatoria
    numDatos = int(input("Introduce el número de espectadores a crear de forma aleatoria: "))
    # Crear lista de clientes aleatorios
    
    datos = [Cliente.gen_datos(True) for i in range(numDatos)]
    nodo_eliminado = False
    # Eliminar los clientes si aparecen en los árboles
    for numArbol,arbol in enumerate(lista_arboles):
        for dato in datos:
            nodo = arbol.search(dato.id)
            if nodo is not None:
                nodo_eliminado = True
                arbol.borrar_nodo(dato.id)
                print(f"Ha sido borrado el espectador {dato.id} del arbol AVL {numArbol}")
    if not nodo_eliminado:
        print("No se ha encontrado ningun espectador para borrar en los arboles AVL")
                
       
def manipularDatos(case):
    #if case == '3' elegir modo de recorrido anchura,pre,in o post orden
    if case == 3:
        while True:
            opc = input('Seleccione modo de recorrido: \n1.Anchura \n2.Pre orden \n3.In orden \n4.Post orden\n')           
            if int(opc) == 1:
                print(f"AVL 1:",end="\t\t")   
                lista_arboles[0].bfs_traversal()
                print(f"AVL 2:",end="\t\t")   
                lista_arboles[1].bfs_traversal()
                print(f"AVL 3:",end="\t\t")   
                lista_arboles[2].bfs_traversal()
                break
            elif int(opc) == 2:
                print(f"AVL 1:",end="\t\t")   
                lista_arboles[0].mostrarArbol(2)
                print(f"AVL 2:",end="\t\t")   
                lista_arboles[1].mostrarArbol(2)
                print(f"AVL 3:",end="\t\t")   
                lista_arboles[2].mostrarArbol(2)  
                break              
            elif int(opc) == 3:
                print(f"AVL 1:",end="\t\t")   
                lista_arboles[0].mostrarArbol(3)
                print(f"AVL 2:",end="\t\t")   
                lista_arboles[1].mostrarArbol(3)
                print(f"AVL 3:",end="\t\t")   
                lista_arboles[2].mostrarArbol(3)      
                break          
            elif int(opc) == 4:
                print(f"AVL 1:",end="\t\t")   
                lista_arboles[0].mostrarArbol(4)
                print(f"AVL 2:",end="\t\t")   
                lista_arboles[1].mostrarArbol(4)
                print(f"AVL 3:",end="\t\t")   
                lista_arboles[2].mostrarArbol(4)   
                break             
            else:
                print("La entrada no es válida.")
    elif case == 1 or case==2 or case==4 or case==5:
        numArbol = input("Elige un ARBOL: \n1.AVL 1\n2.AVL 2\n3.AVL 3\n4.Volver atras\n")
        if numArbol!="4":
            print("Has elegido la el arbol ",numArbol)
            switch_case(int(numArbol),case)
        

def solicitarDato():
    while True:
        id = input("Introduzca ID:")
        if len(id) == 6:
            return int(id)
        else:
            print("La entrada no es válida. Debe tener 6 números.")

def datos_id(numArbol,case):#modificar para buscar y para eliminar
    id_solicitado = solicitarDato()
    if case == 2:
        id_encontrado = False
        arbol = lista_arboles[numArbol-1]  
        try:
            nodo = arbol.search(id_solicitado)
            if nodo is not None:
                id_encontrado=True
                print("ID ; NOMBRE ; EDAD ; GENERO ; ENTRADA")
                print(f"{nodo.value.id} ; {nodo.value.nombre} ; {nodo.value.edad} ; {nodo.value.genero} ; {nodo.value.entrada}")
        except:
            pass
    elif case == 4:#BORRAMOS NODO DEL ARBOL SOLICITADO
        id_encontrado = False
        arbol = lista_arboles[numArbol-1]
        try:
            nodo = arbol.search(id_solicitado)
            if nodo is not None:
                arbol.borrar_nodo(id_solicitado) 
                id_encontrado=True
                print(f"Se ha borrado el espectador {id_solicitado} del arbol AVL {numArbol}")
        except:
            pass
               
    if not id_encontrado:
        print(f"El ID {id_solicitado} no se encuentra en el arbol AVL {numArbol}")


###################################
#   FUNCIONES DE LA SIMULACION    #
###################################
def imprimirArboles():
    for i in range(3):
        print(f"AVL {i+1}:", end="\t")
        lista_arboles[i].mostrarArbol(3)
        #print()
        
def inicSim():
    print("Inicializando datos...")
    lista_arboles[0].insert(Cliente.gen_datos(IS_REP))
    lista_arboles[1].insert(Cliente.gen_datos(IS_REP))
    lista_arboles[2].insert(Cliente.gen_datos(IS_REP))
    
    imprimirArboles()
    '''
    AVL1: 123456
    AVL2: 234567
    AVL3: 345678
    '''
    print("EVENTO:\t\t") # "EVENTO:     "
    time.sleep(1)

def simulacion():    

    inicSim()#INICIALIZA LA SIMULACION CON UN PROMPT DE LAS lista_arboles Y EVENTO(VACIO)  
    time.sleep(1) 
    
    case = 1 #DEFAULT
    numArbol_salida=0
    numArbol_random=0
    nuevoCliente = None
    item = None
    start_time = time.time()  # tiempo inicial del bucle

    while time.time() - start_time < 60:
        match case:
            case 1:
                    # Elegimos una cola al azar
                    numArbol_random = choice([0,1,2])
                    case += 1
            case 2:#Generamos dato random en la cola. 
                nuevoCliente = Cliente.gen_datos(IS_REP)
                lista_arboles[numArbol_random].insert(nuevoCliente)
                case +=1
            case 3:#Mostramos las lista_arboles
                print()
                imprimirArboles()
                case += 1
            case 4:#Mostramos el evento de llegada
                print(f'Evento:\tSe introduce espectador {nuevoCliente.id} en el AVL{numArbol_random+1}\n')
                case+=1
            case 5:#Imprimimos los arboles y evento despues de salir un cliente
                time.sleep(1)               
                #Elegimos cola al azar para el evento
                numArbol_salida = choice([0,1,2])
                
                if lista_arboles[numArbol_salida].isEmpty():
                    imprimirArboles()
                    print(f'Evento:\t No hay espectadores para proceder en el AVL{numArbol_salida+1}\n')
                else:
                    node = lista_arboles[numArbol_salida].get_random_node()
                    #item = lista_arboles[numArbol_salida].borrar_nodo(node.value.id)        
                    aux = node                         
                    if node.value.entrada == 0:# SALE DE LA SALA
                            item = lista_arboles[numArbol_salida].borrar_nodo(node.value.id) 
                            imprimirArboles()
                            print(f'Evento:\tSe borra espectador {aux.value.id} del AVL{numArbol_salida+1}')
                    elif node.value.entrada==1: #NO SE PUEDE SACAR DE LA SALA
                        imprimirArboles() 
                        print(f'Evento:\tNo se puede borrar el espectador {node.value.id} del AVL{numArbol_salida+1}')                                
                case +=1
            case 6:
                time.sleep(5)
                case = 1
            case 7:
                break
            
def salir():
    print('Saliendo...')


if __name__ == '__main__':
    arbol1 = AVLTree()
    arbol2 = AVLTree()
    arbol3 = AVLTree()
    IS_REP = False
    lista_arboles = [arbol1,arbol2,arbol3]
    menu_principal()