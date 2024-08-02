<!-- #include file=auth.asp -->
<!-- #include file=conexion.asp -->
<%
	idciudad = request.form("idciudad")
	ciudad = request.form("ciudad")
	tasa_aeropuerto = request.form("tasa_aeropuerto")

	SentenciaSQL = "insert into CIUDAD values (" & idciudad & ", '" & ciudad & "', '" & tasa_aeropuerto & "')"
	Set rs = Conexion.Execute(SentenciaSQL)
	'response.write("La Ciudad se ha introducido con exito en la Base de Datos")
	Conexion.Close
	Set Conexion = nothing
	response.redirect("/ASP/admin_ciudades.asp")
%>