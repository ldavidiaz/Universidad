<!-- #include file=conexion.asp -->
<%

cod_reserva = CInt(request.form("consultar-cod-reserva"))
nif_reserva = request.form("consultar-reserva-nif")

Set consulta = Conexion.Execute("SELECT IDRESERVA,NIF FROM RESERVA WHERE IDRESERVA="&cod_reserva&" AND NIF='"&nif_reserva&"'")
	bd_reserva = consulta("IDRESERVA")
	bd_nif = consulta("NIF")

If cod_reserva = bd_reserva and nif_reserva = bd_nif Then
	Conexion.Close
	Set Conexion = nothing
	Session("idreserva") = cod_reserva
	response.redirect("ver-reserva.asp")
Else 
	Conexion.Close
	Set Conexion = nothing
	response.redirect("reservas-no-encontradas.html")
End if

%>	