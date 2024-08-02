<%@ include file="auth.jsp" %>
<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,org.firebirdsql.management.*"%>
<%@ include file="conexion.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Practica 1 DAI</title>
    <link rel="stylesheet" type="text/css" href="/prueba/JSP/layout-inserts.css" media="screen"/>
</head>
<body>
    <container>
        <header>
            <h1 style="font-family: Arial,sans-serif; font-style: italic;">ten-fe </h1>
            <p>de llegar a tiempo...</p>
        </header>
        <nav>
            <ul id="menu">
                <li><a href="/prueba/JSP/admin.jsp" style="text-decoration: underline;">VOLVER ATR&Aacute;S</a></li>
            </ul>
        </nav>
        <main>
            <%
                int idcompania = Integer.parseInt(request.getParameter("id_compania"));
                String compania = request.getParameter("name_compania");
                String sqlString = "INSERT into COMPANIA values("+idcompania+",'"+compania+"')";
                PreparedStatement sentencia = null;
                //boolean errorResulados = false;
                //boolean hayRegistros = false;
                try{
                    sentencia = connRSFind.prepareStatement(sqlString);
                    sentencia.executeUpdate();
            %>
                    <h2><span style="color:green;">&#x2714;</span> Compa&ntilde;&iacute;a agregada con &eacute;xito a la base de datos</h2>
            <%
                    //hayRegistros = rs.next();
                }catch(SQLException e1){
                    //errorResultados = true;
            %>
                    <h2>Error:<%=e1.getMessage() %></h2>
            <%
            }
                if(sentencia!=null)
                    sentencia.close();
                if(connRSFind!=null)
                    connRSFind.close();
            %>
            <a href="/prueba/JSP/admin.jsp"
            style="background-color: snow;color: #333;
                    cursor: pointer; padding: 3px;
                    border:1px solid #333;border-radius: 3px;">
            VOLVER A ADMINISTRACI&Oacute;N
            </a>
        </main>
        <footer>
        </footer>
    </container>
</body>
</html>