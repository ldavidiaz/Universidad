<%
if Session("isAuth") <> true then
	response.redirect("Not-Authorized.html")
end if
%>