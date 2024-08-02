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
        <a href="gestion_embarcaciones.php" id="btn-volver">
            < Volver
        </a> 
        <h1 style="width:45%!important;height:10%!important">AÑADIR NUEVA EMBARCACIÓN</h1>

        <form action="add_embarcacion2.php" method="POST" enctype="multipart/form-data" 
        class="ctd-app" style="width: 45%!important">

            <table style="display:flex;
                          align-items:center;
                          justify-content:start;">
                <tr>
                    <td>Matricula:</td>    
                    <td>
                        <input type="text" name="matricula" required>
                    </td>
                </tr>
                <tr>
                    <td>Longitud:</td>    
                    <td>
                        <input type="text" name="longitud" required>
                    </td>
                </tr>
                <tr>
                    <td>Potencia:</td>    
                    <td>
                        <input type="text"  name="potencia" required>
                    </td>
                </tr>
                <tr>
                    <td>Motor:</td>    
                    <td>
                        <input type="text"  name="motor" required>
                    </td>
                </tr>
                <tr>
                    <td>Año:</td>    
                    <td>
                        <input type="number" name="anyo" required>
                    </td>
                </tr>
                <tr>
                    <td>Color:</td>    
                    <td>
                        <input type="text" name="color" required>
                    </td>
                </tr>
                <tr>
                    <td>Material:</td>    
                    <td>
                        <input type="text" name="material" required>
                    </td>
                </tr>
                <tr>
                    <td>ID Dueño:</td>    
                    <td>
                        <select name="idCliente" required>
                        <?php
                            include '../conexion.php';
                            $sql = "SELECT Id_Cliente FROM clientes ORDER BY Id_Cliente";
                            $resultado = $conexion->query($sql);
                            $rows = $resultado->fetchAll();
                            foreach($rows as $row){
                                $id_cliente = $row['Id_Cliente'];
                                echo "
                                    <option value='$id_cliente'>$id_cliente</option>
                                ";
                            }

                        ?>
                        </select>
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
                        <input type="submit" value="Añadir embarcación"
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