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
<!--         <a href="gestion_embarcaciones.php" id="btn-volver">
            < Volver
        </a>  -->
        <?php
            if(isset($_GET['isConsulta'])){
                echo"
                <a href='../consultas/tabla_embarcaciones.php' id='btn-volver'>
                    < Volver
                </a> ";
            }else{
                echo"
                <a href='gestion_embarcaciones.php' id='btn-volver'>
                    < Volver
                </a> ";
            }
        ?>


        <h1 style="width:45%!important;height:10%!important">DETALLES DE LA EMBARCACIÓN</h1>
        <form action="update_embarcacion.php" method="POST" enctype="multipart/form-data" 
        class="ctd-app" style="width: 45%!important">
            <table style="display:flex;
                          align-items:center;
                          justify-content:start;">
                <?php
                    include '../conexion.php';
                    error_reporting(E_ALL & ~E_NOTICE);
                    include '../eliminar_temporales.php';
                    $matricula = $_GET["matricula"];
                    $sql = "SELECT * FROM embarcaciones WHERE Matricula='$matricula'";
                    $result = $conexion->query($sql);
                    $resultado= $result->fetchAll();

                    foreach($resultado as $row){
                        $longitud = $row['Longitud'];     
                        $potencia = $row['Potencia']; 
                        $motor = $row['Motor']; 
                        $anyo = $row['Año']; 
                        $color = $row['Color']; 
                        $material = $row['Material']; 
                        $id_cliente = $row['Id_Cliente']; 
                        echo"
                        <tr>
                            <td>Matricula:</td>
                            <td>
                                <input type='text' name='matricula' value='$matricula'>
                            </td>
                            <td class='upd-client-pl-3'>Longitud:</td>    
                            <td>
                                <input type='text' name='longitud' value='$longitud'>
                            </td>
                        </tr>
                        <tr>
                            <td>Potencia:</td>    
                            <td>
                                <input type='text' name='potencia' value='$potencia'>
                            </td>
                            <td  class='upd-client-pl-3'>Moto:</td>    
                            <td>
                                <input type='text'  name='motor' value='$motor'>
                            </td>
                        </tr>
                        <tr>
                            <td>Año:</td>    
                            <td>
                                <input type='text'  name='anyo' value='$anyo'>
                            </td>
                            <td class='upd-client-pl-3'>Color:</td>    
                            <td>
                                <input type='text' name='color' value='$color'>
                            </td>
                        </tr>
                        <tr>
                            <td>Material:</td>    
                            <td>
                                <input type='text' name='material' value='$material'>
                            </td>
                            <td class='upd-client-pl-3'>ID Cliente:</td>    
                            <td>
                                <input type='text' name='idCliente' value='$id_cliente'>
                            </td>
                        </tr>";

                        $foto = $row['Fotografia']; 
                        // Tratamiento de la imagen antes de mostrarla
                        // getcwd devuelve el directorio actual
                        // tempnam crea un archivo temporal
                        // basename da nombre a un archivo
                        // Creamos una archivo en www con el nombre temp
                        $imagen=basename(tempnam(getcwd()."../../temporales","temp"));
                        // añadimos la extensión jpg al fichero
                        $imagen.=".jpg";
                        //abrimos el fichero con permisos de escritura
                        $fichero=fopen("../../temporales/".$imagen,"w");
                        // escribimos en el fichero el contenido del campo de la base de datos
                        fwrite($fichero,$foto);
                        //cerramos el fichero
                        fclose($fichero);
                        echo"
                        <tr>
                            <td>Fotografía:</td>    
                            <td>
                                <a href='../../temporales/$imagen'> 
                                    <img src='../../temporales/$imagen' width=50 border=0>
                                </a>
                            </td>
                        </tr>                       
                        <tr>
                            <td>
                                <input type='submit' value='Actualizar datos' class='inputs-add-update'>
                            </td>
                            </form> 
                            <td>
                                <form action='borrar_embarcacion.php' method='POST' >
                                <input type='text' value='$matricula' name='borrar[]' style='display:none!important'>
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