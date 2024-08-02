<?xml version="1.0" encoding="UTF-8"?> 
<!-- #include file=conexion.asp -->
<%
' El contenido a devolver es XML
response.ContentType="text/xml"
response.CacheControl="no-cache, must-revalidate"
' Accedemos al compañía enviado desde la pagina principal

idciudad_o = request.form("origen")
idciudad_d = request.form("destino")


Set consulta_o = Conexion.Execute("SELECT CIUDAD FROM CIUDAD WHERE IDCIUDAD="&idciudad_o)
ciudad_o = consulta_o("CIUDAD")
Set consulta_d = Conexion.Execute("SELECT CIUDAD FROM CIUDAD WHERE IDCIUDAD="&idciudad_d)
ciudad_d = consulta_d("CIUDAD")


SentenciaSQL = "SELECT IDVUELO,FECHA,COMPANIA,AVION,N_PLAZAS_DISPONIBLES,DURACION,PRECIO from LISTA_VUELOS_PRECIO2 where CIUDAD_ORIGEN='"&ciudad_o&"' AND CIUDAD_DESTINO='"&ciudad_d&"'"
Set rs = Conexion.Execute(SentenciaSQL)
reserva = "<a style='font-weigth: normal;color: darkmagenta;text-decoration:underline;' href='/ASP/form-reserva.asp?ID_VUELO=" & rs("IDVUELO") & "'>Reservar</a>"
' Se genera una salida XML con la lista de vuelos
if (not(rs.Eof)) then
	response.write("<XML>")
	' Recorremos el Recorset
	do until rs.Eof
	response.write("<vuelo>")

	response.write("<idvuelo>")
	response.write( rs("IDVUELO") )
	response.write("</idvuelo>")

	response.write("<ciudad_origen>")
	response.write( ciudad_o )
	response.write("</ciudad_origen>")

	response.write("<ciudad_destino>")
	response.write( ciudad_d )
	response.write("</ciudad_destino>")

	response.write("<fecha>")
	response.write( rs("FECHA") )
	response.write("</fecha>")

	response.write("<compania>")
	response.write( rs("COMPANIA") )
	response.write("</compania>")

	response.write("<avion>")
	response.write( rs("AVION") )
	response.write("</avion>")

	response.write("<n_plazas_disponibles>")
	response.write( rs("N_PLAZAS_DISPONIBLES") )
	response.write("</n_plazas_disponibles>")

	response.write("<duracion>")
	response.write( rs("DURACION") )
	response.write("</duracion>")


	response.write("<precio>")
	response.write(round(rs("PRECIO"),2))
	response.write("</precio>")

	response.write("</vuelo>")
	rs.MoveNext
	loop
	response.write("</XML>")
End if
%>