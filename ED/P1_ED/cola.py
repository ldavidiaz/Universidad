class Cola:
    def __init__(self) :
        self.items = []

    def colaVacia(self):
        return self.items == []
    
    def insertarElemento(self, persona):
        self.items.append(persona)

    def borrarElemento(self):
        if not self.colaVacia():
            return self.items.pop(0)


    ##OPCION 3
    def mostrarCola(self):#MUESTRA TODOS LOS ID DE LA COLA
            if self.colaVacia():
                print("La cola esta vacia")
            else:
                print(",".join([cliente.id for cliente in self.items]))
    #OPCION 4
    def mostrarDatosCola(self):
        if self.colaVacia():
            print("La cola esta vacia")
        else:
            for cliente in self.items:
              print(cliente.id,";",cliente.nombre,";",cliente.edad,";",cliente.genero,"; ",cliente.tipo,"; ",cliente.entrada)  

    ##OK. modificar
    def accederAtributos(self):
        if not self.colaVacia():
            return self.items#.nombre, self.items[indice].edad ##continuar con todos los atributos
        else:    
            return []
    ##funciones para la simulacion
    
    def mostrarPrimero(self):#MUESTRA EL PRIMER ELEMENTO EN LA COLA
        if self.colaVacia():
            print("La cola está vacía")
        else:
            print("Primera persona en la cola es: ",self.items[0].ID)
  
    def mostrarUltimo(self):
        if self.colaVacia():
            print("La cola esta vacia")
        else:
            print(self.items[-1].id,end="")
    def moverFinalCola(self):
        if not self.colaVacia():
            persona = self.borrarElemento()
            self.insertarElemento(persona)
##para imprimir todos los datos de una persona con una coma y espacio como separador
##for persona in self.items:
##  print(f"{persona.nombre}, {persona.edad}, ....)