<?php
include '../../auth.php';
require_once '../conexion.php';


    $num_factura = $_POST['num_factura']; 
    $matricula = $_POST['matricula'];
    $mano_obra = floatval($_POST['mano_obra']);
    $precio_hora = floatval($_POST['precio_hora']);
    $fechaE = $_POST['fechaE'];
    $fechaP = $_POST['fechaP'];    
    $iva = $_POST['iva'];
    // Calcular importe imponible
    $precio_base = $mano_obra * $precio_hora;
    
    $add_update = $_POST['add_update'];

    // Verificar si se han enviado datos en los arrays
    if (isset($_POST['referencias']) && isset($_POST['unidades'])) {
        $array_referencia = $_POST['referencias'];
        $array_unidades = $_POST['unidades'];


        // Recorrer los arrays y realizar las operaciones necesarias
        for ($i = 0; $i < count($array_referencia); $i++) {
            // Insertar o actualizar datos en la tabla detalle_factura
            if($add_update=="añadir"){
                $sql1=("INSERT INTO detalle_factura(Numero_Factura,Referencia,Unidades)
                VALUES('$num_factura','$array_referencia[$i]','$array_unidades[$i]')");
               $detalle_factura = $conexion->query($sql1);
            }
            else if($add_update=="actualizar"){
                $id_det_aux = intval($_POST['id_det2']);
                $id_det = intval($_POST['id_det_factura']);
                $sqlBorrar2="DELETE FROM detalle_factura WHERE Id_Det_Factura=$id_det_aux";
                $conexion->query($sqlBorrar2);

                $sqlU1 =("INSERT INTO detalle_factura(Id_Det_Factura,Numero_Factura,Referencia,Unidades)
                VALUES('$id_det','$num_factura','$array_referencia[$i]','$array_unidades[$i]')");
                $update1 = $conexion->query($sqlU1);
            }

            // actualizar el importe a traves de los datos del repuesto
            $sql2 = "SELECT Importe, Ganancia FROM repuestos WHERE Referencia = $array_referencia[$i]";
            $result = $conexion->query($sql2);
            $rows = $result->fetchAll();
            if ($result) {
                foreach ($rows as $row) {
                    $importe = floatval($array_unidades[$i]) * $row['Importe'];
                    $ganancia = $row['Ganancia'];
                    $importe = $importe * (floatval("1." . $ganancia));
                    $precio_base += $importe;
                }
            }
        }
    }


    //calcular iva y el total
    $total_iva = floatval($precio_base * floatval("0.".$iva));
    $total = floatval($precio_base + $total_iva);

    //insertamos o actualizamos datos en factura
    if($add_update=="añadir"){
        $SentenciaSQL=("insert into factura(Numero_Factura,Matricula,
        Mano_de_Obra,Precio_Hora,Fecha_Emision,Fecha_Pago,Base_Imponible,IVA,Total) values
        ('$num_factura','$matricula','$mano_obra','$precio_hora','$fechaE','$fechaP','$precio_base','$iva','$total')");
        // Creamos la consulta y asignamos el resultado a la variable $result
        $result = $conexion->query($SentenciaSQL);
        if (!$result){
            echo "<br>#Error al introducir el Cliente en la
            Base de Datos";
        }
        else{
            header("Location: gestion_facturas.php");
            exit();
        }
    }
    else if($add_update=="actualizar"){
        $factura_aux = $_POST['num_factura2'];
        $sqlBorrar2="DELETE FROM factura WHERE Numero_Factura='$factura_aux'";
        $conexion->query($sqlBorrar2);

        $sqlU2 = ("insert into factura(Numero_Factura,Matricula,
        Mano_de_Obra,Precio_Hora,Fecha_Emision,Fecha_Pago,Base_Imponible,IVA,Total) values
        ('$num_factura','$matricula','$mano_obra','$precio_hora','$fechaE','$fechaP','$precio_base','$iva','$total')");
        $update2 = $conexion->query($sqlU2);
        if(!$update2){
            echo "#Error. No se han podido actualizar los datos<br>
            <a href='../menu_inicial.php'>Pulse aquí</a> para volver a la página principal";
        }else{
            header("Location: gestion_facturas.php");
            exit();
        }
    }
?>