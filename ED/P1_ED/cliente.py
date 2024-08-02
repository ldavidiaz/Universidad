from random import randrange,choice
import re
class Cliente:
    arrays_id = []
    def __init__(self, id, nombre, edad, genero, tipo, entrada):
        self.id = id
        self.nombre = nombre
        self.edad = edad
        self.genero = genero
        self.tipo = tipo
        self.entrada = entrada
        Cliente.arrays_id.append(id)
        #crear variable numerica que guarde si el cliente cambia de cola

    @classmethod
    def añadir_datos(cls):
        case = 1
        while(case != 0):
            match case:
                case 1:
                    id = input("Introduce el id del cliente (6 dígitos): ")
                    if len(id) != 6 or not id.isdigit():#comprobar que no se encuentre en la cola
                        print("El id debe tener 6 dígitos.")
                    else: 
                        #print(type(id))
                        case+=1
                case 2:
                    nombre = input("Introduce el nombre del cliente (máximo 10 letras): ")
                    if len(nombre) > 10:
                        print("El nombre debe tener como máximo 10 letras.")
                    else:
                        case+=1
                case 3:       
                    edad = input("Introduce la edad del cliente (0-99): ")
                    if not edad.isdigit() or int(edad) < 0 or int(edad) > 99:
                        print("La edad debe ser un número entre 0 y 99.")
                    else:
                        case+=1
                case 4:
                    genero = input("Introduce el género del cliente (femenino/masculino/otro): ")
                    if genero not in ["femenino", "masculino", "otro"]:
                        print("El género debe ser femenino, masculino u otro.")
                    else:
                        case+=1
                case 5:
                    tipo = input("Introduce el tipo del cliente (A/B/C): ")
                    if tipo not in ["A", "B", "C"]:
                        print("El tipo debe ser A, B o C.")               
                    else:
                        case+=1
                case 6:
                    entrada = input("Indica si el cliente ha entrado (s/n): ")
                    if entrada not in ["s", "n"]:
                        print("La respuesta debe ser s o n.")
                    else:
                        case+=1
                case _:
                        case = 0

        return cls(id, nombre, int(edad), genero, tipo, entrada==0)
    
    @classmethod
    def gen_id(cls):#generar id
        while True:
            cl_id = randrange(100000,999999)
            if cl_id not in cls.arrays_id:
                return str(cl_id)
    
    @classmethod
    def gen_nombre(cls):
        letras = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
        regex = r"[A-Z]"
        arr_letras = re.findall(regex, letras)
        cl_nombre = ""
        while len(cl_nombre)<10:
            cl_nombre += choice(arr_letras)
        return cl_nombre
    
    @classmethod
    def gen_edad(cls):
        cl_edad = randrange(0,99)
        return cl_edad
    
    @classmethod
    def gen_genero(cls):
        generos = ['Femenino','Masculino','Otro']
        cl_genero = choice(generos)
        return cl_genero
    
    @classmethod
    def gen_tipo(cls):
        tipos = ['A','B','C']
        cl_tipo = choice(tipos)
        return cl_tipo
    
    @classmethod
    def gen_entrada(cls):
        cl_entrada = randrange(0,4)  
        return cl_entrada
    
    @classmethod
    def nuevoDato(cls):
        #comprobar que no esta repetido
        inic_id = cls.gen_id()

        inic_nombre = cls.gen_nombre()
        inic_edad = cls.gen_edad()
        inic_genero = cls.gen_genero()
        inic_tipo = cls.gen_tipo()
        inic_entrada = cls.gen_entrada()
        return cls(inic_id,inic_nombre,inic_edad,inic_genero,inic_tipo,inic_entrada)
