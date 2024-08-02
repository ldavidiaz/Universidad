<?php 
    include '../../auth.php';
    require_once '../conexion.php';
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
            padding-left: 20px;
        }
        .del-line-factura{
            background-color: #E64946;
            border: none;
            border-radius: 3px;
            color: #EDEDED;
            font-weight: bold;
            cursor: pointer;
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
    <?php
            if(isset($_GET['isConsulta'])){
                echo"
                <a href='../consultas/tabla_facturas.php' id='btn-volver'>
                    < Volver
                </a> ";
            }else{
                echo"
                <a href='gestion_facturas.php' id='btn-volver'>
                    < Volver
                </a> ";
            }
        ?>
        <h1 style="width:45%!important;height:10%!important">DETALLES DE LA FACTURA</h1>
        <form action="add-update-factura.php" method="POST" enctype="multipart/form-data" 
        class="ctd-app" style="width: 45%!important">
            <table id="tb-datos-factura"  style="display:flex;
                          align-items:center;
                          justify-content:start;">
                <?php
                    
                    $num_factura = $_GET["Numero_Factura"];
                    $sql = "SELECT * FROM factura WHERE Numero_Factura='$num_factura'";
                    $result = $conexion->query($sql);
                    $resultado= $result->fetchAll();

                    foreach($resultado as $row){
                        $matricula = $row['Matricula'];     
                        $mano_obra = $row['Mano_de_Obra']; 
                        $precioHora = $row['Precio_Hora']; 
                        $fechaE = $row['Fecha_Emision']; 
                        $fechaP = $row['Fecha_Pago']; 
                        $baseI = $row['Base_Imponible']; 
                        $iva = floatval($row['IVA']); 
                        $total = $row['Total'];
                        echo"
                        <tr>
                            <td>Número de factura:</td>
                            <td>
                                <input type='text' name='num_factura2' value='$num_factura' style='display:none'>
                                <input type='text' name='num_factura' value='$num_factura'>
                            </td>
                            <td class='upd-client-pl-3'>Matrícula:</td>
                            <td>
                                <input type='text' name='matricula' value='$matricula'>
                            </td>
                        </tr>
                        <tr>
                            <td>Mano de obra:</td>    
                            <td>
                                <input type='text' name='mano_obra' value='$mano_obra'>
                            </td>
                            <td  class='upd-client-pl-3'>Precio por hora:</td>    
                            <td>
                                <input type='text'  name='precio_hora' value='$precioHora'>
                            </td>
                        </tr>
                        <tr>
                            <td>Fecha de emisión:</td>    
                            <td>
                                <input type='text' name='fechaE' value='$fechaE'>
                            </td>
                            <td  class='upd-client-pl-3'>Fecha de pago:</td>    
                            <td>
                                <input type='text'  name='fechaP' value='$fechaP'>
                            </td>
                        </tr>
                        <tr>
                            <td>Base imponible:</td>    
                            <td>
                                <input type='text'  name='baseI' value='$baseI' readonly>
                            </td>
                            <td class='upd-client-pl-3'>IVA:</td>    
                            <td>
                                <input type='number'  name='iva' value='$iva'>
                            </td>
                        </tr>
                        <tr>
                            <td>Total:</td>    
                            <td>
                                <input type='text'  name='total' value='$total' readonly>
                            </td>
                        </tr>";
                        
                        $sql2 = "SELECT * FROM detalle_factura WHERE Numero_Factura='$num_factura'";
                        $result2 = $conexion->query($sql2);
                        $resultado2= $result2->fetchAll();
                        if($result2){
                            echo"
                            <tr>
                                <td colspan='5'>
                                <h2>Linea de detalles</h2>
                                </td>
                            </tr>
                            <tr>    
                                <td><center><b>ID Linea de detalles<b></center></td>
                                <td><center><b>Numero de factura<b></center></td>
                                <td><center><b>Repuesto<b></center></td>
                                <td><center><b>Unidades<b></center></td>
                            </tr>";
                            foreach($resultado2 as $row2){
                                $id_det = $row2['Id_Det_Factura'];
                                $det_num_factura = $row2['Numero_Factura'];
                                $referencia = $row2['Referencia'];
                                $unidades = $row2['Unidades'];
                                $sql3="SELECT Descripcion FROM repuestos WHERE Referencia=$referencia";
                                $result3 = $conexion->query($sql3);
                                foreach($result3 as $value){
                                    $descripcion = $value['Descripcion'];
                                }
                                echo"
                                <tr>
                                    <td>
                                        <center>
                                            <input type='number' name='id_det2' value='$id_det' style='display:none'>
                                            <input type='number' name='id_det_factura' value='$id_det'>
                                        </center>
                                    </td>
                                    <td>
                                        <center>
                                            <input type='text' name='det_num_factura' value='$det_num_factura' readonly>
                                        </center>
                                    </td>
                                    <td>
                                        <center>
                                            <select name='referencias[]' value='$referencia'>
                                                <option value='$referencia' default>$descripcion</option>
                                    ";
                                            $sql4="SELECT Referencia,Descripcion FROM repuestos WHERE Descripcion<>'$descripcion' ORDER BY Referencia";
                                            $result4 = $conexion->query($sql4);
                                            foreach($result4 as $row2){
                                                $ref = $row2['Referencia'];
                                                $descripcion2 = $row2['Descripcion'];
                                                echo"
                                                    <option value='$ref'>$descripcion2</option>
                                                ";
                                            }
                                    echo"
                                            </select>
                                        </center>
                                    </td>
                                    <td>
                                        <center>
                                            <input type='number' name='unidades[]' value='$unidades'>
                                        </center>
                                    </td>
                                </tr>
                                ";
                            }
                        }

                        
                        echo"
                        <tr>
                            <td style='display:none'><input name='add_update' value='actualizar'></td>
                            <td>
                                <input type='submit' value='Actualizar datos' class='inputs-add-update'>
                            </td>
                            </form> 
                            <td>
                                <form action='borrar_factura.php' method='POST' >
                                <input type='text' value='$num_factura' name='borrar[]' style='display:none!important'>
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