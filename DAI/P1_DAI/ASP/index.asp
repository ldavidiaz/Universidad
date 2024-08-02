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
            	<li><a href="/ASP/reservas.html">RESERVAS</a></li>
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
            <buscador>
                <titulo>
                    <h2>Buscador de vuelos</h2>
                </titulo>
                <form name="form-buscar" method="post" action="data-destinos.asp">
					<div>
						<label for="origen"> Ciudad Origen: </label>
						<select name="origen" id="origen">
							<option value=0>Todos los origenes</option>
                        <%
                        on Error Resume Next
                        Set origen = Conexion.Execute("Select IDCIUDAD,CIUDAD from CIUDAD Order by CIUDAD")
                        do while not origen.EOF
                            response.write("<option value=" & origen("IDCIUDAD") & ">" & origen("CIUDAD") & "</option>")
                            origen.MoveNext
                        loop
                        %>
						</select>
					</div>
					<div>
					<label for="destino">Ciudad Destino:</label>
					<select name="destino" id="destino">
						<option value="0">Todos los destinos</option>
                    <%
                    on Error Resume Next
                    Set destino = Conexion.Execute("Select IDCIUDAD,CIUDAD from CIUDAD Order by CIUDAD")
                        do while not destino.EOF
                            response.write("<option value=" & destino("IDCIUDAD") & ">" & destino("CIUDAD") & "</option>")
                            destino.MoveNext
                        loop
                    %>
                    <%
                    Conexion.Close
                    Set Conexion = nothing
                    %>
					</select>
					</div>
					<div style="display: none;">
						<label for="fecha-ida">Seleccionar fecha de ida: </label>
						<input name="fecha-ida" id="fecha-ida" type="date"/>
                    </div>
					<div id="btn-buscar">
						<input type="submit" value="BUSCAR" style="background-color: snow;border:1px solid #333;border-radius: 3px;">
                    </div>
				</form>
            </buscador>
        </main>
        <footer>

        </footer>
    </container>
</body>
</html>