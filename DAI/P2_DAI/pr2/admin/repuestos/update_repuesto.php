<?php
    include ("../../auth.php");
    include("../conexion.php");
    $referencia = $_POST['referencia'];
    $descripcion = $_POST['descripcion'];
    $importe = $_POST['importe'];
    $ganancia = $_POST['ganancia'];


    $sql = "UPDATE repuestos
            SET Referencia='$referencia',
            Descripcion='$descripcion',
            Importe='$importe',
            Ganancia='$ganancia' 
            WHERE Referencia='$referencia'";
    $update = $conexion->prepare($sql);
    $update->execute();
    if(!$update){
        echo "#Error. No se han podido actualizar los datos<br>
        <a href='../menu_inicial.php'>Pulse aquí</a> para volver a la página principal";
    }else{
        header("Location: gestion_repuestos.php");
        exit();
    }
?>