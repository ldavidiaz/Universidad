<?php
    include ("../../auth.php");
    require_once ("../conexion.php");
    $array_borrados=$_POST["borrar"];
    $error = 0;
    for($i=0;$i<count($array_borrados);$i++){
        $sql = "Delete from factura where Numero_Factura='$array_borrados[$i]'";
        $result = $conexion->query($sql);
        if(!$result){
            $error=1;
        }     
        $sql2="Delete from detalle_factura where Numero_Factura='$array_borrados[$i]'";
        $result2=$conexion->query($sql2);
        
    }
    if($error==0){
        header("Location: gestion_facturas.php");
        exit();
    }
?>