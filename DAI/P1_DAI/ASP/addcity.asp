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
            <h2>Añadir nueva CIUDAD:</h2>
            <%
                Set consulta = Conexion.Execute("Select max(IDCIUDAD) as ultimo_id from CIUDAD")
                sig_id = consulta("ultimo_id")+1
            %>
            <form name="form-add" method="post" action="insertciudad.asp">
                <table>
                    <tr><td>Codigo ciudad: </td>
                    <td><input type="number" name="idciudad" readonly style="background-color: lightgray;cursor: default;border-style: solid;" value=
                        <% response.write(sig_id) %>></td></tr>
                    <tr><td>Ciudad: </td>
                    <td><input type="text" name="ciudad"></td></tr>
                    <tr><td>Tasa Aeropuerto: </td>
                    <td><input type="number" name="tasa_aeropuerto"></td></tr>

                    <tr><td colspan="2"><center><input type="submit" id="introducir-ciudad" value="Agregar ciudad"/></center></td></tr>
                </table>                
            </form>
        </main>
        <footer>
        </footer>
    </container>
</body>
</html>