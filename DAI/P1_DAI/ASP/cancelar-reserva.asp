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
            <%
            reserva_id = request.queryString("id")

            SentenciaSQL = "UPDATE RESERVA SET CANCELADO=CANCELADO + 1 WHERE IDRESERVA ="&reserva_id
            Set consulta = Conexion.Execute(SentenciaSQL)

            Dim errores
            errores = nothing
            if Conexion.Errors.Count > 0 Then
                for each error in Conexion.Errors
                    response.write("<h2>HA FINALIZADO EL PLAZO MAXIMO DE CANCELACIÓN</h2><p>"&Error.Number & " = " & Error.Description & "</p>")
                next
                Conexion.Close
                Set Conexion = nothing
            else
                response.write("<div style='display: none;align-items:flex-start;justify-content: flex-start;width: 100%'><h2 style='text-decoration: underline;'>RESERVA CANCELADA CON ÉXITO</h2></div>")
            end if
            %>

            <div style="display:flex;justify-content: center;">
                <a href="/ASP/reservas.html" 
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