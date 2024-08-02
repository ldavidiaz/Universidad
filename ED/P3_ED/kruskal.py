# Una clase para representar un conjunto disjunto
#https://www-hackerearth-com.translate.goog/blog/developers/kruskals-minimum-spanning-tree-algorithm-example/?_x_tr_sl=en&_x_tr_tl=es&_x_tr_hl=es&_x_tr_pto=rq#:~:text=Most%20of%20the%20cable%20network,city%20or%20group%20of%20cities.

class DisjointSet:
    #sirve para que no se formen los ciclos
    conjuntos = {}
 
    def hacerConjunto(self, n):
        # crear `n` conjuntos disjuntos (uno para cada vértice)
        for i in range(n):
            self.conjuntos[i] = i
            
    # Encuentra la raíz del conjunto al que pertenece el elemento `k`
    # ayuda a determinar a qué subconjunto pertenece un elemento en particular.
    # También ayuda a determinar si el elemento está en más de un subconjunto.
    def find(self, k):
        # si `k` es root
        if self.conjuntos[k] == k:
            return k
        
        # recurre para el padre hasta que encontramos la raíz
        return self.find(self.conjuntos[k])
    
    # Realizar unión de dos subconjuntos
    # Comprueba que no hay ciclos
    def union(self, a, b):
        # encontrar la raíz de los conjuntos a los que pertenecen los elementos `x` e `y`
        x = self.find(a)
        y = self.find(b)
        
        self.conjuntos[x] = y
        print("DESPUES DE UNION")
        print(self.conjuntos.__str__())
        
 
# Función # para construir MST usando el algoritmo de Kruskal
def AlgoritmoKruskal(edges, n):
 
    # almacena los bordes presentes en MST
    #Árbol de coste total mínimo (mínimum spinning tree - MST)
    MST = []
 
    # Inicializa la clase `DisjointSet`.
    # Crea un conjunto singleton para cada elemento del universo.
    ds = DisjointSet()
    ds.hacerConjunto(n)
    print("Primero hacemos un conjuto para comprobar despues que hemos unido y comprobar que no hay ciclos")
    print(ds.conjuntos.__str__(),"\n")
    index = 0
 
    # ordena los bordes aumentando el peso
    edges.sort(key=lambda x: x[2])
    print("Ordenamos los grafos segun su peso")
    for x in edges:
        print(x)

    # MST contiene exactamente aristas `V-1`
    print("---------------------------------")
    while len(MST) != n - 1:
        print("PASO ",index)
        # considerar el borde siguiente con peso mínimo del graph
        (inicio, destino, weight) = edges[index]
        
        #print(ds.parent.__str__())
        # encontrar la raíz de los conjuntos a los que se unen dos extremos
        # vértices de la siguiente arista pertenecen
        print("inicio: ",inicio ," | destino:",destino)
        print(ds.conjuntos.__str__())
        
        x = ds.find(inicio)
        y = ds.find(destino)
        
       
        # si ambos extremos tienen diferentes padres, pertenecen a
        # diferentes componentes conectados y se pueden incluir en MST
        print("FIND: ",end=" ")
        print("X: ",x, "| Y:",y,"\n")
        if x != y:
            
            print("SON DISTINTOS X e Y, metemos (",inicio,",",destino,",",weight,") a la solución MST",sep="")
            MST.append((inicio, destino, weight))#Aqui se guardan los valores de la solución
            print("MST: ",MST,"\n")
            print("UNION: ")
            ds.union(x, y)
        
        else:
            
            print("SON IGUALES X e Y, NO metemos (",inicio,",",destino,",",weight,") a la solución MST , PORQUE FORMA UN CICLO",sep="")

        print("---------------------------------")
        index = index + 1

    return MST
 
 
if __name__ == '__main__':
 
    # (u, v, w) el triplete representa un borde no dirigido desde
    # vértice `u` a vértice `v` con peso `w`
    
    """
    prueba = [
        (0, 1, 4), (0, 2, 2), (1, 2, 14),(1, 3, 15)
    ]
    # número total de nodos en el graph (etiquetados de 0 a 2)
    numNodos = 4
    """
    
    grafos = [
        (0, 1, 20), (0, 4, 17), (0, 2, 11),(0, 8, 18),(0, 5, 12),(0, 6, 23),
        (1, 3, 10), (1, 6, 15), (3, 4, 21),(4, 7, 13),(2, 7, 14),
        (5, 6, 16), (5, 8, 18)
    ]
    numNodos = 9
    # graph de construcción
    print("----------INICIO----------")
    print("GRAFOS")
    print("inicio,fin,peso")
    for x in grafos:
        print(x)
    print("numero de nodos: ",numNodos)

    print("----------COMIENZO ALGORITMO KRUSKAL----------")
    solucionFinal = AlgoritmoKruskal(grafos, numNodos)
    
    print("SOLUCION FINAL :")
    print(solucionFinal)
    
    #Código para calcular el pesoTotal de la solución Final
    """pesoTotal = 0
    index = 0
    while index != numNodos - 1:
        (inicio, destino, weight) = solucionFinal[index]

        pesoTotal = pesoTotal + weight
        index = index+1
    print("PESO TOTAL: ", pesoTotal)
    """