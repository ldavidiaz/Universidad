<?php
    include ("../../auth.php");
    include("../conexion.php");
    include '../eliminar_temporales.php';
    $matricula = $_POST['matricula'];
    $longitud = $_POST['longitud'];
    $potencia = $_POST['potencia'];
    $motor = $_POST['motor'];
    $anyo = $_POST['anyo'];
    $color = $_POST['color'];
    $material = $_POST['material'];
    $idCliente = $_POST['idCliente'];

    $sql = "UPDATE embarcaciones
            SET Matricula='$matricula',
            Longitud='$longitud',
            Potencia='$potencia',
            Motor='$motor',
            Año='$anyo',
            Color='$color',
            Material='$material',
            Id_Cliente='$idCliente' 
            WHERE Matricula='$matricula'";
    $update = $conexion->prepare($sql);
    $update->execute();
    if(!$update){
        echo "#Error. No se han podido actualizar los datos<br>
        <a href='../menu_inicial.php'>Pulse aquí</a> para volver a la página principal";
    }else{
        header("Location: gestion_embarcaciones.php");
        exit();
    }
?>