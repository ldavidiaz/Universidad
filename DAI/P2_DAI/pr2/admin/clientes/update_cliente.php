<?php
    include ("../../auth.php");
    include("../conexion.php");
    include '../eliminar_temporales.php';
    $idCliente = $_POST['idCliente'];
    $dni = $_POST['dni'];
    $nombre = $_POST['nombre'];
    $apellido1 = $_POST['apellido1'];
    $apellido2 = $_POST['apellido2'];
    $direccion = $_POST['direccion'];
    $cp = $_POST['cp'];
    $poblacion = $_POST['poblacion'];
    $provincia = $_POST['provincia'];
    $telefono = $_POST['telefono'];
    $email = $_POST['email'];

    $sql = "UPDATE clientes
            SET DNI='$dni',
            Nombre='$nombre',
            Apellido1='$apellido1',
            Apellido2='$apellido2',
            Direccion='$direccion',
            CP='$cp',
            Poblacion='$poblacion',
            Provincia='$provincia',
            Telefono='$telefono',
            Email='$email' 
            WHERE Id_Cliente='$idCliente'";
    $update = $conexion->prepare($sql);
    $update->execute();
    if(!$update){
        echo "#Error. No se han podido actualizar los datos<br>
        <a href='../menu_inicial.php'>Pulse aquí</a> para volver a la página principal";
    }else{
        header("Location: gestion_clientes.php");
        exit();
    }
?>