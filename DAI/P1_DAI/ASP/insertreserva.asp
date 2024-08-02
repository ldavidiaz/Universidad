<!-- #include  file=conexion.asp -->
<%
idvuelo = request.form("reserva-idvuelo")
apellidos = request.form("reserva-apellidos")
nombre = request.form("reserva-nombre")
nif = request.form("reserva-nif")
idvuelo = Session("vuelo-id")
n_asientos = request.form("reserva-num-asientos")
cancelado = 0

SentenciaSQL = "insert into RESERVA values(" & idvuelo & ", '" & apellidos & "', '" & nombre & "', '" & nif & "', " & idvuelo & ", "& n_asientos & ", " & cancelado & ")"
Set consulta = Conexion.Execute(SentenciaSQL)
Dim errores
errores = nothing
if Conexion.Errors.Count > 0 Then
	for each error in Conexion.Errors
		errores = Error.Number & " = " & Error.Description
	next
	Conexion.Close
	Set Conexion = nothing
	Session("bd-error") = errores
	'vuelta = false'
	response.redirect("/ASP/form-reserva.asp")
else
	Conexion.Close
	Set Conexion = nothing
	Session("vuelta") = true
	response.redirect("/ASP/horarios.asp")
end if
%>