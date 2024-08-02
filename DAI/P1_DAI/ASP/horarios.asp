<!-- #include file=conexion.asp -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Practica 1 DAI</title>
    <link rel="stylesheet" type="text/css" href="/ASP/formulario.css" media="screen"/>
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
                <li><a href="/ASP/index.asp">BUSCADOR</a></li>
                <li><a href="/ASP/loggin.html" style="color:white;background:transparent;
                    border:none;font-size:16px;font-weight: bold;
                    text-decoration: underline;cursor: pointer;
                    letter-spacing: 1px">INICAR SESION</a></li>
            </ul>
        </nav>
        <main>
			<%
                Dim origen_recibido,destino_recibido,ver_vuelta
                ver_vuelta = Session("vuelta")
                origen_recibido = Session("origen")
                destino_recibido = Session("destino")
                if ver_vuelta <> false Then
                    origen_recibido = Session("destino")
                    destino_recibido = Session("origen")
                End if
            %>
            <%
                if origen_recibido <> 0 Then
                    Set consulta = Conexion.Execute("SELECT CIUDAD from CIUDAD where IDCIUDAD="& origen_recibido)
                    ciudad_origen = consulta("CIUDAD")
                End if
                if destino_recibido <> 0 Then
                    Set consulta = Conexion.Execute("SELECT CIUDAD from CIUDAD where IDCIUDAD="& destino_recibido)
                    ciudad_destino = consulta("CIUDAD")
                End if
                If origen_recibido = 0 Then
                    ciudad_origen = "TODOS LOS ORIGENES" 
                End if
                if destino_recibido = 0 Then
                    ciudad_destino = "TODOS LOS ORIGENES"
                End if
            %>

            <div style="display: flex;flex-direction: column;width: 100%"><!--CONTENEDOR DE ORIGEN: --- <br> DESTINO:---  -->
                <div style="display: flex;flex-direction: row;"><!--CONTENEDOR DE ORIGEN: ---  -->
                    <p><b>ORIGEN: </b></p>
                    <p>&nbsp <% response.write(ciudad_origen) %></p>
                </div>
                <div style="display: flex;flex-direction: row;">
                    <p><b>DESTINO: </b></p>
                    <p>&nbsp <% response.write(ciudad_destino) %></p>
                </div>
            </div>

            
			<table>
                <%
                    if origen_recibido<>0 AND destino_recibido<>0 Then
                        Set consulta = Conexion.Execute("SELECT IDVUELO,FECHA,COMPANIA,AVION,N_PLAZAS_DISPONIBLES,DURACION,PRECIO from LISTA_VUELOS_PRECIO2 where CIUDAD_ORIGEN='"&ciudad_origen&"' AND CIUDAD_DESTINO='"&ciudad_destino&"'")
                    Elseif origen_recibido<>0 AND destino_recibido=0 Then
                        Set consulta = Conexion.Execute("SELECT IDVUELO,CIUDAD_ORIGEN,CIUDAD_DESTINO,FECHA,COMPANIA,AVION,N_PLAZAS_DISPONIBLES,DURACION,PRECIO from LISTA_VUELOS_PRECIO2 where CIUDAD_ORIGEN='"&ciudad_origen&"'")
                    Elseif origen_recibido=0 AND destino_recibido<>0 Then 
                        Set consulta = Conexion.Execute("SELECT IDVUELO,CIUDAD_ORIGEN,CIUDAD_DESTINO,FECHA,COMPANIA,AVION,N_PLAZAS_DISPONIBLES,DURACION,PRECIO from LISTA_VUELOS_PRECIO2 where CIUDAD_DESTINO='"&ciudad_destino&"'")
                    Else    
                        Set consulta = Conexion.Execute("SELECT IDVUELO,CIUDAD_ORIGEN,CIUDAD_DESTINO,FECHA,COMPANIA,AVION,N_PLAZAS_DISPONIBLES,DURACION,PRECIO from LISTA_VUELOS_PRECIO2")
                    End if
                    'COMPROBAMOS SI HAY OBJETOS EN EL RECORD SET
                    if consulta.EOF Then
                        response.write("<p>NO HAY VUELOS DISPONIBLES</p>")
                    Else
                        if origen_recibido<>0 AND destino_recibido<>0 Then
                            response.write("<tr><th>RESERVAR</th><th>IDVUELO</th><th>FECHA</th><th>DURACION</th><th>COMPAÑIA</th><th>AVION</th><th>NUM. PLAZAS DISP</th><th>PRECIO</th></tr>")
                        Else
                            response.write("<tr><th>RESERVAR</th><th>IDVUELO</th><th>ORIGEN</th><th>DESTINO</th><th>FECHA</th><th>DURACION</th><th>COMPAÑIA</th><th>AVION</th><th>NUM. PLAZAS DISP</th><th>PRECIO</th></tr>")
                        End if
          
                        'Conexion.Execute("PROCEDURE LISTA_VUELOS_PRECIO2")

        				'recorrer las filas de la consulta usando recordset'
                        if origen_recibido<>0 AND destino_recibido<>0 Then
            				do while not consulta.EOF
            					response.write("<tr><td><a style='font-weigth: normal;color: darkmagenta;text-decoration:underline;' href='form-reserva.asp?ID_VUELO=" & consulta("IDVUELO") & "'>Reservar</a></td><td>" & consulta("IDVUELO") & "</td><td>" & consulta("FECHA") & "</td><td>"& consulta("DURACION") &"</td><td>"& consulta("COMPANIA") &"</td><td>"& consulta("AVION") &"</td><td>"& consulta("N_PLAZAS_DISPONIBLES") &"</td><td>"& round(consulta("PRECIO"),2) &"</td></tr>")
            					consulta.MoveNext
            				loop
                        Else
                            do while not consulta.EOF
                                response.write("<tr><td><a style='font-weigth: normal;color: darkmagenta;text-decoration:underline; ' href='form-reserva.asp?ID_VUELO=" & consulta("IDVUELO") & "'>Reservar</a></td><td>" & consulta("IDVUELO") & "</td><td>" & consulta("CIUDAD_ORIGEN") & "</td><td>" & consulta("CIUDAD_DESTINO")&"</td><td>" & consulta("FECHA") & "</td><td>"& consulta("DURACION") &"</td><td>"& consulta("COMPANIA") &"</td><td>"& consulta("AVION") &"</td><td>"& consulta("N_PLAZAS_DISPONIBLES") &"</td><td>"& round(consulta("PRECIO"),2) &"</td></tr>")
                                consulta.MoveNext
                            loop
                        End if
                    End if                        
                    Conexion.Close
                    Set Conexion = nothing
                %>
            </table>
            <div style="width:100%;margin-top: 15px;">
                <a href="/ASP/index.asp" style="text-align: left ;text-decoration: underline;color:#333;">VOLVER ATRAS</a>
            </div>
		</main>
	</container>
</body>
</html>