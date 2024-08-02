<!-- #include file=conexion.asp -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Practica 1 DAI</title>
    <link rel="stylesheet" type="text/css" href="/ASP/formulario.css" media="screen"/>
</head>
<body>
    <container>
        <header>
            <h1 style="font-family: Arial,sans-serif; font-style: italic;">ten-fe </h1>
            <p>de llegar a tiempo...</p>
        </header>
        <nav>
            <ul id="menu">
                <li><a href="/ASP/index.asp">BUSCADOR</a></li>
                <li><a href="/ASP/loggin.html" style="color:white;background:transparent;
                    border:none;font-size:16px;font-weight: bold;
                    text-decoration: underline;cursor: pointer;
                    letter-spacing: 1px">
                    INICIAR SESION
                    </a>
                </li>
            </ul>
        </nav>
        <main>
			<div style="display: flex;
            align-items:flex-start;
            justify-content: flex-start;
            width: 100%">
                <h2 style="text-decoration: underline;">TUS RESERVAS</h2>
            </div>
            <form method="post" action="editar-ciudad.asp">
    			<table id="table">
                    <tr>
                        <th>IDVUELO</th>
                        <th>ORIGEN</th>
                        <th>DESTINO</th>
                        <th>FECHA</th>
                        <th>COMPAÑIA</th>
                        <th>DURACION</th>
                        <th>CANCELAR</th>
                    </tr>
                    <%
                    Dim cod_res
                    cod_res = Session("idreserva")

                    Set consulta = Conexion.Execute("SELECT IDVUELO from RESERVA WHERE IDRESERVA="&cod_res)
                        res_vuelo = consulta("IDVUELO")
                    Set consulta1 = Conexion.Execute("Select IDVUELO,IDCIUDADORIGEN,IDCIUDADDESTINO,FECHA,IDCOMPANIA,DURACION from VUELO WHERE IDVUELO="&res_vuelo)
                    id_ciudad_o = consulta1("IDCIUDADORIGEN")
                    id_ciudad_d = consulta1("IDCIUDADDESTINO")
                    id_compania = consulta1("IDCOMPANIA")

                    Set consulta2 = Conexion.Execute("SELECT CIUDAD from CIUDAD WHERE IDCIUDAD="&id_ciudad_o)
                    ciudad_o = consulta2("CIUDAD")

                    Set consulta3 = Conexion.Execute("SELECT CIUDAD from CIUDAD WHERE IDCIUDAD="&id_ciudad_d)
                    ciudad_d = consulta3("CIUDAD")

                    Set consulta4 = Conexion.Execute("SELECT COMPANIA FROM COMPANIA WHERE IDCOMPANIA="&id_compania)
                    compania = consulta4("COMPANIA")
                
                    response.write("<tr><td>" & consulta1("IDVUELO") & "</td><td>" & ciudad_o & "</td><td>" & ciudad_d & "</td><td>"& consulta1("FECHA") & "</td><td>"& compania& "</td><td>"& consulta1("DURACION") & "</td><td>" &"<a style='color: #b71c1c !important;' href='cancelar-reserva.asp?id="& cod_res & "'>Cancelar</a></td></tr>")

                    Conexion.Close
                    Set Conexion = nothing
                    %>                        
                </table>
                <div style="display:flex;justify-content: center;">
                    <a href="/ASP/reservas.html" 
                    style="color: #333;background-color: snow;
                    border:1px solid #333;border-radius:3px;
                    font-weight:normal; padding: 3px;">
                    VOLVER ATRAS
                    </a>
                </div>
            </form>
        </main>
    </container>
</body>
</html>                    