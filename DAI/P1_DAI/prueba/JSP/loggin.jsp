<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,org.firebirdsql.management.*"%>
<%@ include file="conexion.jsp" %>
<% session.setAttribute("loggedIn",false); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Practica 1 DAI</title>
    <link rel="stylesheet" type="text/css" href="/prueba/JSP/formulario.css" media="screen"/>
    <style type="text/css">
        table{
            border-spacing: 5px;
            width: 350px;
        }
        td{
            border:none !important;
            text-align: left !important;
            background-color: transparent !important;
        }
        input[type="text"],input[type="password"]{
            cursor: default !important;
            text-decoration: none !important;
            border:1px solid black;
        }
        input[type="submit"]{
            background-color: snow;
            border:1px solid #333;
            border-radius: 3px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <container>
        <header>
            <h1 style="font-family: Arial,sans-serif; font-style: italic;">ten-fe </h1>
            <p>de llegar a tiempo...</p>
        </header>
        <nav>
                <ul id="menu">
                    <li><a href="/prueba/JSP/rd-home.jsp" style="color:white;background:transparent;
                    border:none;font-size:16px;font-weight: bold;
                    text-decoration: underline;cursor: pointer;
                    letter-spacing: 1px">
                    VOLVER ATR&Aacute;S
                        </a>
                    </li>
                </ul>
        </nav>
        <main>
            <div style="display: flex;
            align-items:center;
            justify-content: center;
            width: 100%;margin:10px 0 20px 0">
                <h1>Iniciar Sesion</h1>
            </div>
            <table> 
                <form method="post" action="#">
                    <tr><td>
                        <label for="usuario">Nombre de usuario: </label>
                    </td><td>
                        <input type="text" name="usuario" required>
                    </td></tr>
                    <tr><td>
                        <label for="password">Contrase&ntilde;a: </label>
                    </td><td>
                        <input type="password" name="password" required>
                    </td></tr>
                    <%
                    //aqui mandamos mensaje de datos incorrectos
                    //si es correcto mandamos a otra pagina
                    String USER = "admin";
                    String PWD = "1234";
                    String usuario = request.getParameter("usuario");
                    String password = request.getParameter("password");
                    if(usuario != null & password != null){
                        if(USER.equals(usuario) & PWD.equals(password)){
                            session.setAttribute("loggedIn",true);
                            response.sendRedirect("/prueba/JSP/admin.jsp");
                        }else{
                            out.println("<tr><td colspan='2'><small style='color:red;'>*Usuario o contrase&ntilde;a incorrecto</small></td></tr>");
                        }
                    }
                    %>
                    <tr ><td colspan="2"><center style="margin-top: 5px">
                    <input type="submit" value="ENTRAR">
                    </center></td></tr>
                </form>
            </table>


        </main>
    </container>
</body>
</html>