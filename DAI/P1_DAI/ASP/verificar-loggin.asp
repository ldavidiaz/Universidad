<%
Dim usuario, password
usuario = Request.form("usuario")
password = Request.form("password")
If usuario = "admin" and password = "1234" Then
	Session("isAuth") = true
	response.Redirect("admin_ciudades.asp")
Else
	response.Redirect("loggin.html")
End If
%>