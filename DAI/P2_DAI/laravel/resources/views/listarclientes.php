<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <?php
    $clientes = App\Cliente::all();//devuelve todos los registros de la tabla clientes
    foreach($clientes as $cliente){
        echo $cliente->Nombre;
        echo "<br>";
    }

    ?>
</body>
</html>