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
    <link rel="stylesheet" type="text/css" href="/prueba/JSP/formulario.css" media="screen"/>
</head>
<body>
    <container>
        <header>
            <h1 style="font-family: Arial,sans-serif; font-style: italic;">ten-fe </h1>
            <p>de llegar a tiempo...</p>
        </header>
        <nav>
            <form method="post" action="/prueba/JSP/rd-home.jsp"  style="align-items: center;">
                <ul id="menu">
                    <li><input type="submit" value="SALIR" name="btn-salir" 
                    style="color:white;background:transparent;
                        border:none;font-size:16px;font-weight: bold;
                        text-decoration: underline;cursor: pointer;
                        letter-spacing: 1px"></li>
                </ul>
            </form>
        </nav>
        <main>
			<div style="display: flex;
            align-items:flex-start;
            justify-content: flex-start;
            width: 100%">
                <h1>Administrador</h1>
            </div>
            <div style="display: flex; gap:10px;
            align-items:flex-start;
            justify-content: flex-start;
            width: 100%">
                <a href="#" style="background-color: rgba(86, 86, 86, 0.41);
                cursor: not-allowed !important; 
                border-radius: 5px;font-weight: 500;
                padding: 5px;margin-bottom: 10px;
                letter-spacing: normal" >
                A&ntilde;adir ciudad
                </a>
                <a href="/prueba/JSP/add-comp.jsp" style="background-color: #4a934a;
                border-radius: 5px;font-weight: 500;
                padding: 5px;margin-bottom: 10px;
                letter-spacing: normal">
                A&ntilde;adir compa&ntilde;ia
                </a>
            </div>
            <form method="post" action="#">
    			<table id="table">
                    <tr>
                        <th>ID</th>
                        <th>CIUDAD</th>
                        <th>TASA</th>
                        <th>EDITAR</th>
                        <th>ELIMINAR</th>
                    </tr>
                    <%
                        String sqlString = "SELECT IDCIUDAD,CIUDAD,TASA_AEROPUERTO FROM CIUDAD";
                        PreparedStatement sentencia = null;
                        ResultSet rs = null;
                        boolean errorResultados = false;
                        boolean hayRegistros = false;

                        try{
                            sentencia = connRSFind.prepareStatement(sqlString);
                            rs = sentencia.executeQuery();
                            hayRegistros = rs.next();
                        }catch(SQLException e1){
                            errorResultados = true;
                        %>
                        <p>Error:<%=e1.getMessage() %></p>
                        <%
                        }
                                    
                        if (hayRegistros){
                            boolean hecho = false;
                            while(!hecho){
                                String campoCiudad = (String) rs.getObject("CIUDAD");
                                int campoIdCiudad = Integer.parseInt(rs.getString("IDCIUDAD"));
                                String tasaCiudad = rs.getString("TASA_AEROPUERTO").toString();
                        %>
                                <tr>
                                    <td><%=campoIdCiudad%></td>
                                    <td><%=campoCiudad%></td>
                                    <td><%=tasaCiudad%></td>
                                    <td><button type='submit' style="cursor:not-allowed !important;"
                                     value='#' disabled>
                                    Editar
                                        </button>
                                    </td>
                                    <td><a style='color: #b71c1c !important; cursor: not-allowed !important;'
                                     href='#'>
                                    Eliminar
                                        </a>
                                    </td>
                                </tr>
                        <%
                                hecho = !rs.next();
                            }//fin while
                            rs.close();
                        }  
                        if(sentencia !=null)
                            sentencia.close();
                        if(connRSFind !=null)
                            connRSFind.close();                     

                    %>
                </table>
            </form>
        </main>
    </container>
</body>
</html>