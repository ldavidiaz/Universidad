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
        <a href="gestion_clientes.php" id="btn-volver">
            < Volver
        </a> 
        <h1 style="width:45%!important;height:10%!important">DETALLES DEL CLIENTE</h1>
        <form action="update_cliente.php" method="POST" enctype="multipart/form-data" 
        class="ctd-app" style="width: 45%!important">
            <table style="display:flex;
                          align-items:center;
                          justify-content:start;">
                <?php
                    include '../conexion.php';
                    error_reporting(E_ALL & ~E_NOTICE);
                    include '../eliminar_temporales.php';
                    $id_cliente = $_GET["id_cliente"];
                    $sql = "SELECT * FROM clientes WHERE Id_Cliente=$id_cliente";
                    $result = $conexion->query($sql);
                    $resultado= $result->fetchAll();

                    foreach($resultado as $row){
                        $dni = $row['DNI'];     
                        $nombre = $row['Nombre']; 
                        $ape1 = $row['Apellido1']; 
                        $ape2 = $row['Apellido2']; 
                        $direccion = $row['Direccion']; 
                        $cp = $row['CP']; 
                        $poblacion = $row['Poblacion']; 
                        $provincia = $row['Provincia']; 
                        $telefono = $row['Telefono']; 
                        $email = $row['Email']; 
                        echo"
                        <tr>
                            <td>ID:</td>
                            <td>
                                <input type='text' name='idCliente' value='$id_cliente' readonly style='cursor:default'>
                            </td>
                        </tr>
                        <tr>
                            <td>DNI:</td>    
                            <td>
                                <input type='text' name='dni' value='$dni'>
                            </td>
                            <td class='upd-client-pl-3'>Nombre:</td>    
                            <td>
                                <input type='text' name='nombre' value='$nombre'>
                            </td>
                        </tr>
                        <tr>
                            <td>Primer apellido:</td>    
                            <td>
                                <input type='text'  name='apellido1' value='$ape1'>
                            </td>
                            <td class='upd-client-pl-3'>Segundo apellido:</td>    
                            <td>
                                <input type='text'  name='apellido2' value='$ape2'>
                            </td>
                        </tr>
                        <tr>
                            <td>Dirección:</td>    
                            <td>
                                <input type='text' name='direccion' value='$direccion'>
                            </td>
                            <td class='upd-client-pl-3'>CP:</td>    
                            <td>
                                <input type='text' name='cp' value='$cp'>
                            </td>
                        </tr>
                        <tr>
                            <td>Población:</td>    
                            <td>
                                <input type='text' name='poblacion' value='$poblacion'>
                            </td>
                            <td class='upd-client-pl-3'>Provincia:</td>    
                            <td>
                                <input type='text'  name='provincia' value='$provincia'>
                            </td>
                        </tr>
                        <tr>
                            <td>Teléfono:</td>    
                            <td>
                                <input type='tel' name='telefono' value='$telefono'>
                            </td>
                            <td class='upd-client-pl-3'>E-mail:</td>    
                            <td>
                                <input type='email' name='email' value='$email'>
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
                                <form action='borrar_cliente.php' method='POST' >
                                <input type='text' value='$id_cliente' name='borrar[]' style='display:none!important'>
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