<?php
    include ("../../auth.php");
    include("../conexion.php");
    $array_borrados=$_POST["borrar"];
    $error = 0;
    for($i=0;$i<count($array_borrados);$i++){
        $sql = "Delete from repuestos where Referencia='$array_borrados[$i]'";

        $result = $conexion->query($sql);
        if(!$result){
            $error=1;
        }     
    }
    if($error==0){
        header("Location: gestion_repuestos.php");
        exit();
    }
?>