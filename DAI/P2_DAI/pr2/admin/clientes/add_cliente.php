<?php 
    include '../../auth.php'
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="../../style/layout.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="../../style/layout-apps.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="../../style/layout-info.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="../../style/layout-formularios.css" media="screen"/>
    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- ICONOS Boostrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <!-- FUENTE Roboto -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    <title>Practica 2</title>
</head>
<body>
    <header>
        <p>MyAdmin-Embarcaciones</p>
        <a href="../../logout.php" style="margin-right:15px">
            <span>Salir &nbsp;<i class="bi bi-box-arrow-left" style="font-size:20px"></i></span>
        </a>
    </header>
    <!-- Obtener EL ID's y DNI's de los clientes -->
    <main>
        <a href="gestion_clientes.php" id="btn-volver">
            < Volver
        </a> 
        <h1 style="width:45%!important;height:10%!important">AÑADIR NUEVO CLIENTE</h1>

        <form action="add_cliente2.php" method="POST" enctype="multipart/form-data" 
        class="ctd-app" style="width: 45%!important">

            <table style="display:flex;
                          align-items:center;
                          justify-content:start;">
                <tr>
                    <td>DNI:</td>    
                    <td>
                        <input type="text" name="dni" required>
                    </td>
                </tr>
                <tr>
                    <td>Nombre:</td>    
                    <td>
                        <input type="text" name="nombre" required>
                    </td>
                </tr>
                <tr>
                    <td>Primer apellido:</td>    
                    <td>
                        <input type="text"  name="apellido1" required>
                    </td>
                </tr>
                <tr>
                    <td>Segundo apellido:</td>    
                    <td>
                        <input type="text"  name="apellido2" required>
                    </td>
                </tr>
                <tr>
                    <td>Dirección:</td>    
                    <td>
                        <input type="text" name="direccion" required>
                    </td>
                </tr>
                <tr>
                    <td>CP:</td>    
                    <td>
                        <input type="text" name="cp" required>
                    </td>
                </tr>
                <tr>
                    <td>Población:</td>    
                    <td>
                        <input type="text" name="poblacion" required>
                    </td>
                </tr>
                <tr>
                    <td>Provincia:</td>    
                    <td>
                        <input type="text"  name="provincia" required>
                    </td>
                </tr>
                <tr>
                    <td>Teléfono:</td>    
                    <td>
                        <input type="tel" name="telefono" required>
                    </td>
                </tr>
                <tr>
                    <td>E-mail:</td>    
                    <td>
                        <input type="email" name="email" required>
                    </td>
                </tr>
                <tr>
                    <td>Fotografía:</td>    
                    <td>
                        <input type="file" name="foto">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Añadir cliente"
                        class="inputs-add-update" >
                    </td>
                    <td>
                        <input type="reset" value="Limpiar formulario"
                        class="inputs-clear" >
                    </td>
                </tr>
                
            </table>    
        </form>
    </main>

    <!-- TODO eliminar footer, aumentar height del main -->
<!--     <footer>
        Luis David Diaz Mesa
    </footer> -->
</body>
</html>