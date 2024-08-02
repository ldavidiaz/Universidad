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
        <h1 style="width:45%!important;height:10%!important">CONSULTA: TABLA FACTURA</h1>

        <form action="tabla_facturas.php" method="POST" enctype="multipart/form-data" 
        class="ctd-app" style="width: 45%!important">

            <table style="display:flex;
                          align-items:center;
                          justify-content:start;">
                <tr>
                    <td>Fecha de emision entre:</td>    
                    <td>
                        <input type="date" name="fechaE1" id="fecha1">
                    </td>
                    <td>
                        y &nbsp;
                        <input type="date"  id="fecha2" name="fechaE2">
                    </td>
                </tr>
                <tr>
                    <td>Pendiente de pago:</td>    
                    <td>
                        <select name="p_pago">
                            <option></option>
                            <option value="si">Si</option>
                            <option value="no">No</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>ID Cliente:</td>    
                    <td>
                    <select name="idCliente">
                        <option></option>
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
                    <td>
                        <input type="submit" value="BUSCAR"
                        class="inputs-clear" >
                    </td>
                </tr>               
            </table> 
        </form>

        <table id="tabla-bd">
            <?php
                require_once '../conexion.php';
                // Función para generar los filtros de la consulta SQL
            if(!empty($_POST)){
                function generateFilters2($params,$conexion) {
                    
                    $filters = array();
                    // Verificar cada parámetro y agregar los filtros correspondientes
                    foreach ($params as $param) {
                        if (!empty($_POST[$param])) {
                            $value = $_POST[$param];
                            if($param=="fechaE1"){
                                $filters[] = "Fecha_Emision >= '$value'";
                            }
                            else if($param=="fechaE2"){
                                $filters[] = "Fecha_Emision <= '$value'";
                            }
                            else if($param=="p_pago"){
                                if($value=="si"){
                                    $filters[] = "Fecha_Pago = ' '";
                                }
                                else if($value=="no"){
                                    $filters[] = "Fecha_Pago <> ' '";
                                }
                            }else if($param=="idCliente"){
                               $id_cliente = intval($value);
                                $sql2 = "SELECT Matricula FROM embarcaciones WHERE Id_Cliente=$id_cliente";
                                $result2 = $conexion->query($sql2);
                                $matricula=null;
                                foreach($result2 as $row2){
                                    $matricula = $row2['Matricula']; 
                                }
                                    $filters[] = "Matricula = '$matricula'";
                                
                            }
                        }
                    }
                    
                    // Devolver los filtros como una cadena separada por AND
                    return implode(" AND ", $filters);
                }

                // Definir los nombres de los parámetros
                $params = array('fechaE1', 'fechaE2', 'p_pago', 'idCliente');

                // Generar los filtros de la consulta SQL
                $filters = generateFilters2($params,$conexion);

                // Construir la consulta SQL con los filtros
                $sql = "SELECT * FROM factura";
                // Agregar los filtros a la consulta SQL si se proporcionaron
                if (!empty($filters)) {
                    $sql .= " WHERE $filters";
                }
                
                $result = $conexion->query($sql);

                if($result){
                    echo 
                    "<tr>
                        <td><center><b>Numero_Factura</b></center></td>
                        <td><center><b>Matricula</b></center></td>
                        <td><center><b>Mano de obra</b></center></td>
                        <td><center><b>Precio hora</b></center></td>
                        <td><center><b>Fecha de emisión</b></center></td>
                        <td><center><b>Fecha de pago</b></center></td>
                        <td><center><b>Base Imponible</b></center></td>
                        <td><center><b>IVA</b></center></td>
                        <td><center><b>TOTAL</b></center></td>
                    </tr>
                    ";
                    foreach($result as $row){
                        $num_factura = $row['Numero_Factura'];
                        $matricula = $row['Matricula'];
                        $mano_obra = $row['Mano_de_Obra'];
                        $precio_hora = $row['Precio_Hora'];
                        $fechaE = $row['Fecha_Emision'];
                        $fechaP = $row['Fecha_Pago'];
                        $baseI = $row['Base_Imponible'];
                        $iva = $row['IVA'];
                        $total = $row['Total'];
                        
                        echo 
                        "
                        <tr>
                            <td>
                                <a href='../facturas/editar_factura.php?Numero_Factura=$num_factura&isConsulta=1' style='color: darkblue;text-decoration:underline!important;'>
                                    <center>$num_factura</center>
                                </a>
                            </td>
                            <td>
                                <center>
                                    $matricula
                                </center>
                            </td>
                            <td>
                                <center>
                                    $mano_obra
                                </center>
                            </td>
                            <td>
                                <center>
                                    $precio_hora
                                </center>
                            </td>
                            <td>
                                <center>
                                    $fechaE
                                </center>
                            </td>
                            <td>
                                <center>
                                    $fechaP
                                </center>
                            </td>
                            <td>
                                <center>
                                    $baseI
                                </center>
                            </td>
                            <td>
                                <center>
                                    $iva
                                </center>
                            </td>
                            <td>
                                <center>
                                    $total
                                </center>
                            </td>
                        </tr>";
                    }
                   
                }else{
                    echo "<tr><td>No se han encontrado registros en la BD</td></tr>";
                }
            }
            ?>
        </table>
    </main>
    <script>
        const fecha1 = document.getElementById('fecha1');
        const fecha2 = document.getElementById('fecha2');

        // Agrega un evento de cambio al primer campo de fecha
        fecha1.addEventListener('change', () => {
            // Verifica si se ha seleccionado una fecha en el primer campo
            if (fecha1.value !== '') {
                fecha2.setAttribute('required', 'true');
                fecha2.setAttribute('min', fecha1.value);
            } else {
                fecha2.removeAttribute('required');
                fecha2.removeAttribute('min');
            }
        });
    </script>


</body>
</html>