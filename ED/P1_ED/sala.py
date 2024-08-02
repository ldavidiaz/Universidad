from random import choice

class Sala:
    def __init__(self):
        self.items = []
        self.tipoPelicula = self.genTipo_Pelicula()
        self.numPersonas = self.numPersonasSala()
    
    def insertarCliente(self,cliente):
        self.items.append(cliente)
    
    def numPersonasSala(self):
        return len(self.items)
    
    @classmethod
    def genTipo_Pelicula(cls):
        tipos = ['A','B','C']
        return choice(tipos)
     
