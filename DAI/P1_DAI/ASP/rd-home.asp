<%
Dim salir
salir = request.form("btn-salir")
if salir = "SALIR" then
	Session.Abandon()
end if
Response.Redirect("index.asp")
%>