<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <?php
        $registrosEliminados = App\Cliente::where('Nombre',$nombre)->delete();
        echo "Se ha eliminado $registrosEliminados registro: $nombre";
    ?>
</body>
</html>