<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../../../public/css/layout.css" media="screen"/>
    <title>Document</title>
</head>
<style>
    table{
        border-spacing: 0;
    }
/*     td:nth-child(odd){
        text-align: center;
    } */
    td{
        text-align: center;
        border:1px solid #333;
        padding: 5px;
    }
</style>

<body>
    <header>
        <p>Laravel-Taller_Embarcaciones</p>
    </header>
    <main>
    <?php
    foreach($embarcaciones as $embarcacion){
        echo"<h2>Año de la embarcación con matricula <u>$embarcacion->Matricula</u> actualizada:</h2>
        <table style='display:flex;
                              align-items:space-between;
                              justify-content:space-between;'>
            <tr>
            <td>Matricula:</td>
            <td>$embarcacion->Matricula</td>
            </tr>
            <tr>
                <td>Longitud:</td>
                <td>$embarcacion->Longitud</td>
            </tr>
            <tr>
                <td>Potencia:</td>
                <td>$embarcacion->Potencia</td>
            </tr>
            <tr>
                <td>Motor:</td>
                <td>$embarcacion->Motor</td>
            </tr>
            <tr>
                <td><b>*Año</b>:</td>
                <td>$embarcacion->Año</td>
            </tr>
            <tr>
                <td>Color:</td>
                <td>$embarcacion->Color</td>
            </tr>
            <tr>
                <td>Material:</td>
                <td>$embarcacion->Material</td>
            </tr>
            <tr>
                <td>ID Cliente:</td>
                <td>$embarcacion->Id_Cliente</td>
            </tr>
        </table>
        ";
    }
    ?>

    </main>

</body>
</html>