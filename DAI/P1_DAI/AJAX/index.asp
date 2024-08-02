<!-- #include file=conexion.asp -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Practica 1 DAI</title>
    <link rel="stylesheet" type="text/css" href="/AJAX/formulario.css" media="screen"/>
    <script>
        //creamos el objeto AJAX HttpRequest
        function AJAXCrearObjeto(){
            var obj;
            if (window.XMLHttpRequest){//no es IE
                obj = new XMLHttpRequest();
            }else{//es IE o no tiene el objeto
                try{
                    obj = new ActiveXObject("Microsoft.XMLHTTP");
                }catch (e){
                    alert('El navegador utilizado no está soportado para la tecnología AJAX');
                }
            }
        return obj; 
        }

        //recibe y muestra las ciudades destino de la ciudad de origen
        function leerDatos_ciudades(){
            //comprobamos que se han recibido los datos
            if (oXML.readyState == 4){
                //accedemos al XML recibido
                var xml = oXML.responseXML.documentElement;
                //accedemos al componente html correspondiente al select de destinos
                var select = document.getElementById('destino');
                //vaciamos el DIV?
                var def_options = new String("");

                //iteramos cada ciudad
                var id;
                var v;
                var item;

                for (i=0;i<xml.getElementsByTagName('ciudad').length;i++){
                    //accedemos al obj xml
                    item = xml.getElementsByTagName('ciudad')[i];
                    //recuperamos el id de la ciudad
                    id = item.getElementsByTagName('idciudad')[0].firstChild.data;
                    v = item.getElementsByTagName('ciudad_destino')[0].firstChild.data;
                    //lo añadimos al select
                    def_options = def_options + "<option value="+id+">"+v+"</option>";
                }
                select.innerHTML = def_options;
            }
        }
        function leerDatos_horarios(){
           if(oXML.readyState == 4){
                var xml = oXML.responseXML.documentElement;
                var tabla = document.getElementById('tabla-vuelos');
                var definicion_tabla = new String("");
                definicion_tabla = "<tr><th>RESERVAR</th><th>ID Vuelo</th><th>Ciudad Origen</th><th>Ciudad Destino</th><th>Fecha</th><th>Compañía</th><th>Plazas Disponibles</th><th>Duración</th><th>ID Avión</th><th>Precio</th></tr><tr>"
                var v;
                var item;

                for (i=0;i < xml.getElementsByTagName('vuelo').length;i++){

                    item = xml.getElementsByTagName('vuelo')[i];
                    //recuperamos el enlace para reservar
                    v_aux=item.getElementsByTagName('idvuelo')[0].firstChild.data;
                    v = "<a style='font-weigth: normal;color: darkmagenta;text-decoration:underline;' href='/ASP/form-reserva.asp?ID_VUELO="+v_aux+"'>Reservar</a>"
                    definicion_tabla = definicion_tabla + '<td>'+v+'</td>';

                    v = item.getElementsByTagName('idvuelo')[0].firstChild.data;
                    definicion_tabla = definicion_tabla + '<td>'+v+'</td>';

                    //recuperamos la ciudad origen
                    v = item.getElementsByTagName('ciudad_origen')[0].firstChild.data;
                    definicion_tabla = definicion_tabla + '<td>'+v+'</td>';

                    //recuperamos la ciudad destino
                    v = item.getElementsByTagName('ciudad_destino')[0].firstChild.data;
                    definicion_tabla = definicion_tabla + '<td>'+v+'</td>';

                    // Recuperamos la fecha
                    v = item.getElementsByTagName('fecha')[0].firstChild.data;
                    // Añadimos el campo a la tabla
                    definicion_tabla= definicion_tabla+'<td>' + v + '</td>';

                    // Recuperamos la compania
                    v = item.getElementsByTagName('compania')[0].firstChild.data;
                    // Añadimos el campo a la tabla
                    definicion_tabla= definicion_tabla+'<td>' + v + '</td>';

                    // Recuperamos el avion
                    v = item.getElementsByTagName('avion')[0].firstChild.data;
                    definicion_tabla= definicion_tabla+'<td>' + v + '</td>';

                    // Recuperamos el numero de planzas disponibles
                    v = item.getElementsByTagName('n_plazas_disponibles')[0].firstChild.data;
                    // Añadimos el campo a la tabla
                    definicion_tabla= definicion_tabla+'<td>' + v + '</td>';

                    // Recuperamos la duracion del vuelo
                    v = item.getElementsByTagName('duracion')[0].firstChild.data;
                    // Añadimos el campo a la tabla
                    definicion_tabla= definicion_tabla+'<td>' + v + '</td>';
                    //Recuperamos el precio del vuelo
                    v = item.getElementsByTagName('precio')[0].firstChild.data;
                    // añadimo el campo a la tabla
                    definicion_tabla= definicion_tabla+'<td>' + v + '</td></tr>';
                }
                tabla.innerHTML = definicion_tabla;
           } 
        }        
        function mostrar_ciudad(option){
            //recuperar el valor del despegable de ciudad origen
            
            var valorx = document.getElementById('origen').value;//IDCIUDAD(origen)
            oXML = AJAXCrearObjeto();
            switch(option){
                case 1:
                    oXML.open('POST','ciudad_destino.asp')
                    oXML.onreadystatechange = leerDatos_ciudades;
                    oXML.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                    oXML.send("origen="+valorx)
                    break;
                default:
                    var valory = document.getElementById('destino').value
                    oXML.open('POST','horarios-ajax.asp')
                    oXML.onreadystatechange = leerDatos_horarios;
                    oXML.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                    oXML.send("origen="+valorx+"&destino="+valory)
                    break;
            }
        }
    </script> 
</head>
<body>
    <container>
        <header>
            <h1 style="font-family: Arial,sans-serif; font-style: italic;">ten-fe </h1>
            <p>de llegar a tiempo...</p>
        </header>
        <nav>
            <ul id="menu">
            	<li><a href="/ASP/reservas.html">RESERVAS</a></li>
                <li><a href="/ASP/loggin.html" style="color:white;background:transparent;
                    border:none;font-size:16px;font-weight: bold;
                    text-decoration: underline;cursor: pointer;
                    letter-spacing: 1px">
                    INICIAR SESION
                    </a>
                </li>
            </ul>
        </nav>
        <main>
            <buscador>
                <titulo>
                    <h2>Buscador de vuelos</h2>
                </titulo>
					<div>
						<label for="origen"> Ciudad Origen: </label>
						<select name="origen" id="origen" onChange="return mostrar_ciudad(1)">
							<option value=0>Todos los origenes</option>
                        <%
                        on Error Resume Next
                        Set origen = Conexion.Execute("Select IDCIUDAD,CIUDAD from CIUDAD Order by CIUDAD")
                        do while not origen.EOF
                            response.write("<option value=" & origen("IDCIUDAD") & ">" & origen("CIUDAD") & "</option>")
                            origen.MoveNext
                        loop
                        %>
						</select>
					</div>
					<div>
					<label for="destino">Ciudad Destino:</label>
					<select name="destino" id="destino" >
						<!-- <option value="0">Todos los destinos</option>-->
                     
                    <%
                    Conexion.Close
                    Set Conexion = nothing
                    %>
					</select>
					</div>
					<div id="btn-buscar">
						<button type="button"
                        style="background-color: snow;border:1px solid #333;border-radius: 3px;
                            cursor:pointer;"
                        onClick="return mostrar_ciudad(2)">BUSCAR</button>
                    </div>
            </buscador>


            <table id="tabla-vuelos" name="tabla-vuelos">

            </table>
        </main>
        <footer>

        </footer>
    </container>
</body>
</html>