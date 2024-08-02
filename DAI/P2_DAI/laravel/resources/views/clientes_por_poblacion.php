<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <?php
    $clientes =App\Cliente::where([
                                    ['Poblacion','=',$poblacion],
                                    ['Apellido1','<>','Moreno']
                                ])
                            ->orderBy('Nombre','desc')
                            ->take(2)
                            ->get();
    echo "<h2>Clientes de $poblacion</h2>";
    foreach($clientes as $cliente){
        echo $cliente->Nombre;
        echo "<br>";
    }
    ?>
</body>
</html>