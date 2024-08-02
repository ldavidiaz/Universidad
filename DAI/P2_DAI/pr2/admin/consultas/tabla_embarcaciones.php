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
        #tabla-bd{
            width:75%;
            border-spacing: 0;
        }
        #tabla-bd td{
            border:1px solid #333;
            padding:3px;
            
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
    <main>
        <a href="consultar_tablas.php" id="btn-volver">
            < Volver
        </a> 
        <h1 style="width:45%!important;height:10%!important">CONSULTA: TABLA EMBARCACIONES</h1>

        <form action="tabla_embarcaciones.php" method="POST" enctype="multipart/form-data" 
        class="ctd-app" style="width: 45%!important">

            <table style="display:flex;
                          align-items:center;
                          justify-content:start;">
                <tr>
                    <td>Longitud:</td>    
                    <td>
                        <input type="text" name="Longitud">
                    </td>
                </tr>
                <tr>
                    <td>Potencia:</td>    
                    <td>
                        <input type="text"  name="Potencia">
                    </td>
                </tr>
                <tr>
                    <td>Motor:</td>    
                    <td>
                        <input type="text"  name="Motor">
                    </td>
                </tr>
                <tr>
                    <td>Año:</td>    
                    <td>
                        <input type="number" name="Año">
                    </td>
                </tr>
                <tr>
                    <td>Material:</td>    
                    <td>
                        <input type="text" name="Material">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="BUSCAR"
                        class="inputs-clear" >
                    </td>
                </tr>               
            </table> 
        </form>

        <table id="tabla-bd">
            <?php
                include '../conexion.php';
                // Función para generar los filtros de la consulta SQL
            if(!empty($_POST)){
                function generateFilters($params) {
                    $filters = array();
                    
                    // Verificar cada parámetro y agregar los filtros correspondientes
                    foreach ($params as $param) {
                        if (!empty($_POST[$param])) {
                            $value = $_POST[$param];
                            if($param=="Longitud"){
                                $filters[] = "$param LIKE '$value'";
                            }else{
                                $filters[] = "$param = '$value'";
                            }
                            
                        }
                    }
                    
                    // Devolver los filtros como una cadena separada por AND
                    return implode(" AND ", $filters);
                }

                // Definir los nombres de los parámetros
                $params = array('Longitud', 'Potencia', 'Motor', 'Año', 'Material');

                // Generar los filtros de la consulta SQL
                $filters = generateFilters($params);

                // Construir la consulta SQL con los filtros
                $sql = "SELECT * FROM embarcaciones";
                // Agregar los filtros a la consulta SQL si se proporcionaron
                if (!empty($filters)) {
                    $sql .= " WHERE $filters";
                }
                $result = $conexion->query($sql);

                if(!$result){
                    echo "<tr><td>No se han encontrado registros en la BD</td></tr>";
                }else{
                    echo 
                    "<tr>
                        <td><center><b>Matricula</b></center></td>
                        <td><center><b>Longitud</b></center></td>
                        <td><center><b>Potencia</b></center></td>
                        <td><center><b>Motor</b></center></td>
                        <td><center><b>Año</b></center></td>
                        <td><center><b>Color</b></center></td>
                        <td><center><b>Material</b></center></td>
                    </tr>
                    ";
                    foreach($result as $row){
                        $matricula = $row['Matricula'];
                        $longitud = $row['Longitud'];
                        $potencia = $row['Potencia'];
                        $motor = $row['Motor'];
                        $anyo = $row['Año'];
                        $color = $row['Color'];
                        $material = $row['Material'];
                        
                        echo 
                        "
                        <tr>
                            <td>
                                <a href='../embarcaciones/editar_embarcacion.php?matricula=$matricula&isConsulta=1' style='color: darkblue;text-decoration:underline!important;'>
                                    <center>$matricula</center>
                                </a>
                            </td>
                            <td>
                                <center>
                                    $longitud
                                </center>
                            </td>
                            <td>
                                <center>
                                    $potencia
                                </center>
                            </td>
                            <td>
                                <center>
                                    $motor
                                </center>
                            </td>
                            <td>
                                <center>
                                    $anyo
                                </center>
                            </td>
                            <td>
                                <center>
                                    $color
                                </center>
                            </td>
                            <td>
                                <center>
                                    $material
                                </center>
                            </td>
                        </tr>";
                    }
                    
                }
            }
            
            ?>
        </table>
    </main>

</body>
</html>