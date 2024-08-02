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
    <style>
        .upd-client-pl-3{
            padding-left: 30px;
        }
    </style>
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
        <h1 style="width:45%!important;height:10%!important">DETALLES DEL REPUESTO</h1>
        
        <form action="update_repuesto.php" method="POST" enctype="multipart/form-data" 
        class="ctd-app" style="width: 45%!important">
            <table style="display:flex;
                          align-items:center;
                          justify-content:start;">
                <?php
                    include '../conexion.php';
                    $referencia = $_GET["referencia"];
                    $sql = "SELECT * FROM repuestos WHERE Referencia='$referencia'";
                    $result = $conexion->query($sql);
                    $resultado= $result->fetchAll();

                    foreach($resultado as $row){
                        $descripcion = $row['Descripcion'];     
                        $importe = $row['Importe']; 
                        $ganancia = $row['Ganancia']; 
                        echo"
                        <tr>
                            <td>Referencia:</td>
                            <td>
                                <input type='text' name='referencia' value='$referencia'>
                            </td>
                        </tr>
                        <tr>
                            <td>Descripcion:</td> 
                            <td>
                                <input type='text' name='descripcion' value='$descripcion'>
                            </td>
                        </tr>
                        <tr>
                            <td>Importe:</td>    
                            <td>
                                <input type='text' name='importe' value='$importe'>
                            </td>
                        </tr>
                        <tr>
                            <td>Ganancia:</td>    
                            <td>
                                <input type='text' name='ganancia' value='$ganancia'>
                            </td>
                        </tr>                     
                        <tr>
                            <td>
                                <input type='submit' value='Actualizar datos' class='inputs-add-update'>
                            </td>
                            </form> 
                            <td>
                                <form action='borrar_repuesto.php' method='POST' >
                                <input type='text' value='$referencia' name='borrar[]' style='display:none!important'>
                                <input type='submit' id='btn-eliminar-cliente' class='btn-active' value='Eliminar'>
                                </form>
                            </td>
                        </tr>";
                    }
            ?>
            </table>   
            
    </main>
    <!-- TODO eliminar footer, aumentar height del main -->
<!--     <footer>
        Luis David Diaz Mesa
    </footer> -->
</body>
</html>