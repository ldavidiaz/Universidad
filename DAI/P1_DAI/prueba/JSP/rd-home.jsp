<%
String salir = request.getParameter("btn-salir").toString();
if (salir.equals("SALIR")) {
	session.removeAttribute("loggedIn");
}
response.sendRedirect("/prueba/JSP/reservas.jsp");
%>