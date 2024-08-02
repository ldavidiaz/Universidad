<?xml version="1.0" encoding="UTF-8"?>
<!-- #include file=conexion.asp -->
<%
'accedemos al id de la ciudad destino desde la pagina principal'
idciudad_o = request.form("origen")
'idciudad_o = 1
'Contenido a devolver es XML'
response.ContentType = "text/xml"
response.CacheControl = "no-cache, must-revalidate"

'Consulta SQL de las ciudades destino del origen'
SentenciaSQL1 = "SELECT DISTINCT VUELO.IDCIUDADDESTINO, CIUDAD.CIUDAD FROM VUELO, CIUDAD WHERE VUELO.IDCIUDADORIGEN = " & idciudad_o & " AND VUELO.IDCIUDADDESTINO = CIUDAD.IDCIUDAD"
Set rs = Conexion.Execute(SentenciaSQL1)

if (not(rs.Eof)) Then
%>
<XML>
<%
'recorremos los rs'
	do until rs.EOF 

	response.write("<ciudad>")
	response.write("<idciudad>")
	response.write(rs("IDCIUDADDESTINO"))
	response.write("</idciudad>")

	response.write("<ciudad_destino>")
	response.write(rs("CIUDAD"))
	response.write("</ciudad_destino>")
	response.write("</ciudad>")
	rs.MoveNext
	rs2.MoveNext
	loop

	response.write("</XML>")
End if
%>
