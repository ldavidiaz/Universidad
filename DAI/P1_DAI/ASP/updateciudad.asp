<!-- #include file=auth.asp -->
<!-- #include file=conexion.asp -->
<%
	idciudad = request.form("idciudad")
	ciudad = request.form("ciudad")
	tasa_aeropuerto = request.form("tasa_aeropuerto")

	SentenciaSQL = "UPDATE CIUDAD SET CIUDAD='" & ciudad & "', TASA_AEROPUERTO=" & tasa_aeropuerto & "WHERE IDCIUDAD=" & idciudad
	Set rs = Conexion.Execute(SentenciaSQL)
	Conexion.Close
	Set Conexion = nothing
	response.redirect("admin_ciudades.asp")
%>
