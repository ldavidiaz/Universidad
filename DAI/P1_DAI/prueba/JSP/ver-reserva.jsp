<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,org.firebirdsql.management.*"%>
<%@ include file="conexion.jsp" %>
<!DOCTYPE html>
<html lang="en">
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
            <ul id="menu">
                <li><a href="#">BUSCADOR</a></li>
                <li><a href="/prueba/JSP/loggin.jsp" style="color:white;background:transparent;
                    border:none;font-size:16px;font-weight: bold;
                    text-decoration: underline;cursor: pointer;
                    letter-spacing: 1px">
                    INICIAR SESION
                    </a>
                </li>
            </ul>
        </nav>
        <main style="width:80vw !important;">
			<div style="display: flex;
            align-items:flex-start;
            justify-content: flex-start;
            width: 100%">
                <h2 style="text-decoration: underline;">TUS RESERVAS</h2>
            
            </div>
            <%
                int idciudad_o = Integer.parseInt(request.getParameter("con-idciudad"));
                String sqlString = "SELECT R.IDRESERVA, R.IDVUELO, R.APELLIDOS, R.NOMBRE, CO.CIUDAD AS CIUDAD_ORIGEN, CD.CIUDAD AS CIUDAD_DESTINO, V.FECHA, V.DURACION, COM.COMPANIA, R.CANCELADO AS ESTADO FROM RESERVA R JOIN VUELO V ON R.IDVUELO = V.IDVUELO JOIN COMPANIA COM ON V.IDCOMPANIA = COM.IDCOMPANIA JOIN CIUDAD CO ON V.IDCIUDADORIGEN = CO.IDCIUDAD AND CO.IDCIUDAD =" + idciudad_o +" JOIN CIUDAD CD ON V.IDCIUDADDESTINO = CD.IDCIUDAD WHERE R.NIF =" +request.getParameter("con-nif");

                PreparedStatement sentencia = null;
                ResultSet rs = null;
                boolean errorResultados = false;
                boolean hayReg = false;

                try{
                    sentencia = connRSFind.prepareStatement(sqlString);
                    rs = sentencia.executeQuery();
                    hayReg = rs.next();
                }catch(SQLException e1){
                    errorResultados = true;
                %>
                    <p>Error:<%=e1.getMessage() %></p>
                <%
                }

                
                if(hayReg){
                    boolean hecho = false;
                %>
                <table style="margin-bottom: 30px;">
                    <tr>
                        <th>IDRESERVA</th>
                        <th>IDVUELO</th>
                        <th>APELLIDOS</th>
                        <th>NOMBRE</th>
                        <th>ORIGEN</th>
                        <th>DESTINO</th>
                        <th>FECHA</th>
                        <th>DURACION</th>
                        <th>COMPA&Ntilde;IA</th>
                        <th>ESTADO</th>
                    </tr>
                <%                       
                    while(!hecho){
                        
                        String  idreserva = rs.getObject("IDRESERVA").toString();
                        String idvuelo = rs.getObject("IDVUELO").toString();
                        String apellidos =  rs.getObject("APELLIDOS").toString();
                        String nombre =  rs.getObject("NOMBRE").toString();
                        String ciudad_o =  rs.getObject("CIUDAD_ORIGEN").toString();
                        String ciudad_d =  rs.getObject("CIUDAD_DESTINO").toString();
                        String fecha =  rs.getObject("FECHA").toString();
                        String duracion =  rs.getObject("DURACION").toString();
                        String compania =  rs.getObject("COMPANIA").toString();
                        String estado =  rs.getObject("CANCELADO").toString();

                        if(estado.equals("0"))
                            estado="NO CANCELADO";
                        else
                            estado="CANCELADO";
                %>
                    <tr>
                        <td><%=idreserva  %></td>
                        <td><%=idvuelo  %></td>
                        <td><%=apellidos  %></td>
                        <td><%=nombre  %></td>
                        <td><%=ciudad_o  %></td>
                        <td><%=ciudad_d  %></td>
                        <td><%=fecha  %></td>
                        <td><%=duracion  %></td>
                        <td><%=compania  %></td>
                        <td><%=estado  %></td>
                    </tr>

                <%
                        //out.println("<tr><td>"+idreserva+"</td><td>"+idvuelo+"</td><td>"+apellidos+"</td><td>"+nombre+"</td><td>"+ciudad_o+"</td><td>"+ciudad_d+"</td><td>"+fecha+"</td><td>"+duracion+"</td><td>"+compania+"</td><td>"+estado+"</td></tr>");
                        
                        hecho = !rs.next();
                    }
                    rs.close();
                }else{
                    out.println("<h2>NO EXISTEN RESERVAS CON LOS DATOS INTRODUCIDOS</h2>");
                }
                
                if(sentencia !=null)
                    sentencia.close();
                if(connRSFind !=null)
                    connRSFind.close();
            %>
                </table>
                <div style="display:flex;justify-content: center;">
                    <a href="/prueba/JSP/reservas.jsp" 
                    style="color: #333;background-color: snow;
                    border:1px solid #333;border-radius:3px;
                    font-weight:normal; padding: 3px;">
                    VOLVER ATRAS
                    </a>
                </div>
        </main>
    </container>
</body>
</html>                    