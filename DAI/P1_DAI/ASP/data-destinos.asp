<%
Session("origen") = request.form("origen")
Session("destino") =  request.form("destino")
Session("vuelta") = false
'response.write("-origen:"&Session("origen")&" -destino:"&Session("destino") & "vuelta:" & Session("vuelta"))
response.redirect("horarios.asp")
%>