<?php
include '../../auth.php';
include '../conexion.php';

    $referencia = intval($_POST['referencia']);
    $descripcion = $_POST['descripcion'];
    $importe = floatval($_POST['importe']);
    $ganancia = intval($_POST['ganancia']);

    $SentenciaSQL=("insert into repuestos(Referencia,Descripcion,
    Importe,Ganancia) values
    ('$referencia','$descripcion','$importe','$ganancia')");
    // Creamos la consulta y asignamos el resultado a la variable $result
    $result = $conexion->query($SentenciaSQL);
    if (!$result){
        echo "<br>#Error al introducir el Cliente en la
        Base de Datos";
    }
    else{
        header("Location: gestion_repuestos.php");
    }
?>