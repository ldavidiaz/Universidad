<!-- #include file=auth.asp -->
<!-- #include file=conexion.asp -->
<%
id_recibido = request.querystring("id")

Set consulta = Conexion.Execute("delete from CIUDAD where IDCIUDAD=" & id_recibido)
if Conexion.Errors.Count > 0 Then 
	for each error in Conexion.Errors
		response.write(Error.Number & " = " & Error.Description)
	next
else
	response.redirect("admin_ciudades.asp")
end if

Conexion.Close
Set Conexion = nothing
%>