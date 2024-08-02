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
        <a href="gestion_repuestos.php" id="btn-volver">
            < Volver
        </a> 
        <h1 style="width:45%!important;height:10%!important">AÑADIR NUEVO REPUESTO</h1>

        <form action="add_repuesto2.php" method="POST" enctype="multipart/form-data" 
        class="ctd-app" style="width: 45%!important">

            <table style="display:flex;
                          align-items:center;
                          justify-content:start;">
                <tr>
                    <td>Referencia:</td>    
                    <td>
                        <input type="number" name="referencia" required>
                    </td>
                </tr>
                <tr>
                    <td>Descripcion:</td>    
                    <td>
                        <input type="text" name="descripcion" required>
                    </td>
                </tr>
                <tr>
                    <td>Importe:</td>    
                    <td>
                        <input type="text"  name="importe" required>
                    </td>
                </tr>
                <tr>
                    <td>Ganancia:</td>    
                    <td>
                        <input type="number"  name="ganancia" required>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Añadir repuesto"
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