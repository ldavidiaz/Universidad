<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../../public/css/layout.css" media="screen"/>
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
        <h2>Datos del cliente:</h2>
    <table style="display:flex;
                          align-items:space-between;
                          justify-content:space-between;">
    <?php
    foreach($clientes as $cliente){

        echo "
                <tr>
                    <td>ID:</td>
                    <td>$cliente->Id_Cliente</td>
                </tr>
                <tr>
                    <td>DNI:</td>
                    <td>$cliente->DNI</td>
                </tr>
                <tr>
                    <td>Nombre:</td>
                    <td>$cliente->Nombre</td>
                </tr>
                <tr>
                    <td>Primer Apellido:</td>
                    <td>$cliente->Apellido1</td>
                </tr>
                <tr>
                    <td>Segundo Apellido:</td>
                    <td>$cliente->Apellido2</td>
                </tr>
                <tr>
                    <td>Direccion:</td>
                    <td>$cliente->Direccion</td>
                </tr>
                <tr>
                    <td>CP:</td>
                    <td>$cliente->CP</td>
                </tr>
                <tr>
                    <td>Poblacion:</td>
                    <td>$cliente->Poblacion</td>
                </tr>
                <tr>
                    <td>Provincia:</td>
                    <td>$cliente->Provincia</td>
                </tr>
                <tr>
                    <td>Telefono:</td>
                    <td>$cliente->Telefono</td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>$cliente->Email</td>
                </tr>";
        }

    ?>
    </table>
    </main>

</body>
</html>