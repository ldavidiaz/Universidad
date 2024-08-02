<!-- #include file=auth.asp -->
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
            <form method="post" action="rd-home.asp"  style="align-items: center;">
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
                <h1>Administrar ciudades</h1>
            </div>
            <div style="display: flex;
            align-items:flex-start;
            justify-content: flex-start;
            width: 100%">
                <a href="/ASP/addcity.asp" style="background-color: #4a934a;
                border-radius: 5px;font-weight: 500;
                padding: 5px;margin-bottom: 10px;
                letter-spacing: normal">
                Añadir ciudad
                </a>
            </div>
            <form method="post" action="editar-ciudad.asp">
    			<table id="table">
                    <tr>
                        <th>ID</th>
                        <th>CIUDAD</th>
                        <th>TASA</th>
                        <th>EDITAR</th>
                        <th>ELIMINAR</th>
                    </tr>
    				<%
    				on Error Resume Next
    				Set origen = Conexion.Execute("Select IDCIUDAD,CIUDAD,TASA_AEROPUERTO from CIUDAD")
    				'recorrer las filas de la consulta usando recordset'
    				do while not origen.EOF
    					response.write("<tr><td>" & origen("IDCIUDAD") & "</td><td>" & origen("CIUDAD") & "</td><td>" & origen("TASA_AEROPUERTO") & "</td><td><button type='submit' value='" & origen("IDCIUDAD") &"' name='editar-ciudad'>Editar</button></td><td><a style='color: #b71c1c !important;' href='elim-ciudad.asp?id="&origen("IDCIUDAD") & "'>X</a></td></tr>")
    					origen.MoveNext
    				loop
                    Conexion.Close
                    Set Conexion = nothing
                    %>
                </table>
            </form>
        </main>
    </container>
</body>
</html>