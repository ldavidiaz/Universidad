<?php
include '../../auth.php';
include '../conexion.php';

    $matricula = $_POST['matricula'];
    $longitud = floatval($_POST['longitud']);
    $potencia = intval($_POST['potencia']);
    $motor = $_POST['motor'];
    $anyo = intval($_POST['anyo']);
    $color = $_POST['color'];
    $material = $_POST['material'];
    $idCliente = intval($_POST['idCliente']);
    if (is_uploaded_file($_FILES['foto']['tmp_name']))
    {
        $foto=$_FILES['foto']['tmp_name'];
        // Tratamiento necesario para introducir la imagen en la basede datos
        $fotografia=imagecreatefromjpeg($foto);
        ob_start(); // abrimos el buffer interno
        // obtenemos el fichero jpg-binario del buffer y lo almacena en la variable jpg
        imagejpeg($fotografia);
        $jpg=ob_get_contents();
        //cerramos el buffer
        // preparamos la variable para usarla en una consulta sql
        ob_end_clean();
        $intermedio = addslashes(trim($jpg));
        $jpg=str_replace('##','\##',$intermedio);
    }
    $SentenciaSQL=("insert into embarcaciones(Matricula,Longitud,
    Potencia,Motor,Año,Color,Material,Id_Cliente,Fotografia) values
    ('$matricula','$longitud','$potencia','$motor','$anyo'
    ,'$color','$material','$idCliente','$jpg')");
    // Creamos la consulta y asignamos el resultado a la variable $result
    $result = $conexion->query($SentenciaSQL);
    if (!$result){
        echo "<br>#Error al introducir el Cliente en la
        Base de Datos";
    }
    else{
        header("Location: gestion_embarcaciones.php");
    }

?>