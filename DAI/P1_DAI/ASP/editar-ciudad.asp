<!-- #include file=auth.asp -->
<!-- #include file=conexion.asp -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Practica 1 DAI</title>
    <link rel="stylesheet" type="text/css" href="/ASP/layout-inserts.css" media="screen"/>
</head>
<body>
    <container>
        <header>
            <h1 style="font-family: Arial,sans-serif; font-style: italic;">ten-fe </h1>
            <p>de llegar a tiempo...</p>
        </header>
        <nav>
            <ul id="menu">
                <li><a href="/ASP/admin_ciudades.asp" style="text-decoration: underline;">VOLVER ATRÁS</a></li>
            </ul>
        </nav>
        <main>
            <h2>EDITAR CIUDAD:</h2>
            <%
                id_recibido = request.form("editar-ciudad")
                
                Set consulta = Conexion.Execute("Select IDCIUDAD,CIUDAD,TASA_AEROPUERTO from CIUDAD where IDCIUDAD="& id_recibido)
                id = consulta("IDCIUDAD")
                ciudad = consulta("CIUDAD")
                tasa = consulta("TASA_AEROPUERTO")
            %>
            
            <form name="editando-ciudad" method="post" action="updateciudad.asp">
                <table>
                    <tr><td>Codigo ciudad: </td>
                    <td><input type="number" name="idciudad" readonly style="background-color: lightgray;cursor: default;border-style: solid;"
                        value=<% response.write(id) %>></td></tr>
                    <tr><td>Ciudad: </td>
                    <td><input type="text" name="ciudad" 
                        value="<% response.write(ciudad) %>"></td></tr>
                    <tr><td>Tasa Aeropuerto: </td>
                    <td><input type="number" name="tasa_aeropuerto"
                        value=<% response.write(tasa) %>
                        ></td></tr>

                    <tr><td colspan="2"><center><input type="submit" id="introducir-ciudad" value="GUARDAR CAMBIOS"/></center>
                    </td></tr>
                </table>                
            </form>
            <%
            Conexion.Close
            Set Conexion = nothing
            %>
        </main>
        <footer>
        </footer>
    </container>
</body>
</html>