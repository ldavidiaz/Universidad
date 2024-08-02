<%@ include file="auth.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Practica 1 DAI</title>
    <link rel="stylesheet" type="text/css" href="/prueba/JSP/layout-inserts.css" media="screen"/>
</head>
<body>
    <container>
        <header>
            <h1 style="font-family: Arial,sans-serif; font-style: italic;">ten-fe </h1>
            <p>de llegar a tiempo...</p>
        </header>
        <nav>
            <ul id="menu">
                <li><a href="/prueba/JSP/admin.jsp" style="text-decoration: underline;">VOLVER ATR&Aacute;S</a></li>
            </ul>
        </nav>
        <main>
            <h2>A&ntilde;adir nueva COMPA&Ntilde;&Iacute;A:</h2>
            <form name="form-add" method="post" action="insertcomp.jsp">
                <table>
                    <tr><td>ID compa&ntilde;&iacute;a: </td>
                    <td><input type="number" name="id_compania" required></td></tr>
                    <tr><td>Nombre compa&ntilde;&iacute;a: </td>
                    <td><input type="text" name="name_compania" required></td></tr>
                    <tr><td colspan="2"><center><input type="submit" 
                        style="font-size:16px;color:#333;" 
                        id="introducir-compania" value="Agregar compa&ntilde;&iacute;a"/></center></td></tr>
                </table>                
            </form>
        </main>
        <footer>
        </footer>
    </container>
</body>
</html>