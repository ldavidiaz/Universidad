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
        <main>

            <buscador>
                <titulo>
                    <h2>Consulta tus reservas de vuelos</h2>
                </titulo>
                <form name="form-reservas" method="post" action="/prueba/JSP/ver-reserva.jsp">
					<div>
						<label for="con-idciudad"> CIUDAD DE ORIGEN: </label>
						<select name="con-idciudad" id="select-ciudad-o" required>
                            <%
                                String sqlString = "SELECT * FROM CIUDAD ORDER BY CIUDAD";
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
                                %>
                                        <option value='<%=campoIdCiudad%>'><%=campoCiudad%></option>
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
                        </select>
					</div>
					<div>
    					<label for="con-nif">NIF:</label>
    					<input type="text" name="con-nif" id="con-res-nif" required>
					</div>
					<div id="btn-buscar">
						<input type="submit" value="CONSULTAR" 
                        style="background-color: snow;cursor: pointer;
                        border:1px solid #333;border-radius: 3px;">
                    </div>
				</form>
            </buscador>
        </main>
        <footer>

        </footer>
    </container>
</body>
</html>