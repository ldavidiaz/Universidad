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
                <li><a href="/ASP/horarios.asp" style="text-decoration: underline;">VOLVER ATRÁS</a></li>
            </ul>
        </nav>

        <main>
            <h2>INTRODUCIR DATOS PARA RESERVA DE VUELO</h2>
            <%
            Set consulta = Conexion.Execute("Select max(IDRESERVA) as ultimo_id from RESERVA")
                sig_id = consulta("ultimo_id")+1
            %>
            <%

                Session("vuelo-id") = request.queryString("ID_VUELO")
                id_vuelo = Session("vuelo-id")

                Set consulta = Conexion.Execute("SELECT PRECIO from LISTA_VUELOS_PRECIO2 where IDVUELO="& id_vuelo)
                precio = consulta("PRECIO")
            %>
            
            <form name="form-reserva" method="post" action="insertreserva.asp">
                <table>
                    <tr><td>ID VUELO: </td>
                    <td><input type="number" name="reserva-idvuelo" readonly style="background-color: lightgray;cursor: default;border-style: solid;" value=
                        <% response.write(sig_id) %>></td></tr>
                    <tr><td>Nombre: </td>
                    <td><input type="text" name="reserva-nombre" required></td></tr>
                    <tr><td>Apellidos: </td>
                    <td><input type="text" name="reserva-apellidos" required></td></tr>
                    <tr><td>NIF: </td>
                    <td><input type="text"  name="reserva-nif" required></td></tr>
                    <tr><td>Número de asientos: </td>
                    <td><input type="number" name="reserva-num-asientos" onchange="calcular()" max="120" min="1" required value="1"></td></tr>
                    <tr><td>Precio: </td>
                    <td><input type="text" name="reserva-precio" readonly style="background-color: lightgray;cursor: default;border-style: solid;" value="<% response.write(round(precio,2)) %>"></td></tr>

                    <tr><td colspan="2"><center><input type="submit" id="introducir-reserva" value="Reservar"/></center></td></tr>
                </table>                
            </form>

            <%
                if Session("bd-error") <> "" Then
                    response.write("<p style='font-weigth: bold;'>Ha ocurrido un error con su reserva: </p>" & "<p style='color:red;'>" & Session("bd-error") & "</p>")
                End if

                Conexion.Close
                Set Conexion = nothing
            %>
        </main>
        <footer>
        </footer>
    </container>
    <script>
        function calcular(){
            var numAsientos = document.getElementsByName("reserva-num-asientos")[0].value
            var str = undefined
            var precio_i = parseFloat(str="<% response.write(round(precio,2)) %>".replace(",","."))
            //console.log(precio_i)
            if (!isNaN(numAsientos) && !isNaN(precio_i)) {
                precio_final = numAsientos * precio_i
                
                document.getElementsByName("reserva-precio")[0].value = precio_final.toFixed(2).replace(".",",")
            }
        }
    </script>
</body>
</html>