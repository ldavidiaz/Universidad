<%
Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
if (loggedIn == null || loggedIn != true ) {
	//out.println(loggedIn);
    response.sendRedirect("/prueba/Not-Authorized.html");
}
%>