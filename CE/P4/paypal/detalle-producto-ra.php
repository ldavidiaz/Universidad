<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle de Producto</title>
    <link rel="stylesheet" href="style-tienda.css" type="text/css" media="screen">
    <style>
        /* Agrega este bloque de estilos en tu archivo style-tienda.css o en un archivo CSS separado */
table {
    width: 80%; /* Ancho de la tabla */
    margin: 20px auto; /* Centrar la tabla en la página */
    border-collapse: collapse; /* Combinar bordes de las celdas */
    border: 2px solid #ddd; /* Borde de la tabla */
}

th, td {
    padding: 12px; /* Espaciado interno de las celdas */
    text-align: left; /* Alineación del texto en las celdas */
}

tr:nth-child(even) {
    background-color: #f2f2f2; /* Fondo alternado de filas */
}

th {
    background-color: #333; /* Fondo del encabezado */
    color: white; /* Color de texto del encabezado */
}

td {
    border: 1px solid #ddd; /* Borde de las celdas */
}

strong {
    font-weight: bold; /* Aplicar estilo cursiva a texto enfatizado */
}

    </style>
</head>
<body>
    <header>
        <a href="tienda-retorno-automatico.html" style="cursor: hand;">
            <img src="img/logoPequeno.png" height="85px">
        </a>  
    </header>
    <main>
        <div id="tipo-tienda">
            <a href="tienda-no-retorno.html" class="version-tienda" style="text-decoration: underline;">Versión sin retorno automático</a>
            <span> | </span>
            <a href="tienda-retorno-automatico.html" class="version-tienda" style="color: #f0bb0f;">Versión con retorno automático</a>
        </div>
        <h2 style="font-family: sans-serif; ">RESUMEN DEL PEDIDO</h2>
        <section>
        <?php
            // Verifica si se han enviado datos del producto a través del formulario POST
            $producto = "";
            $precio ="";
            if (isset($_POST['articulo']) && isset($_POST['amount'])) {
                $producto = $_POST['articulo'];
                $precio = $_POST['amount'];
            } else {
                echo '<p>No se han proporcionado detalles del producto.</p>';
            }
        ?>
            <table style>
                <tr>
                    <td>Descripcion</td>
                    <td>Importe</td>
                </tr>
                <tr> 
                    <td>Compra del producto <?php echo $producto ?></td>
                    <td><?php  echo $precio ?> €</td>
                </tr>
                <tr>
                    <td>Precio artículo: <?php echo $precio ?> €</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Cantidad: 1</td>
                    <td></td>
                </tr>
                <tr>
                    <td><strong>Importe total a pagar</strong></td>
                    <td><strong><?php echo $precio ?>€ </strong></td>
                </tr>
                <tr>
                    <td></td>
                    <td>Total <?php echo $precio ?> €</td>
                </tr>
                <tr>
                    
                    <td colspan="2">
                        <center>
                        <!-- VERSION RETORNO AUTOMATICO ACTIVADO -->
                        <form action="https://www.sandbox.paypal.com/cgi-bin/webscr"
                        method="post">
                            <input type="hidden" name="cmd" value="_xclick">
                            <input type="hidden" name="business" value="sb-5pkrk27204113@business.example.com">
                            <input type="hidden" name="item_name" value="Compra de Producto <?php echo $producto?>">
                            <input type="hidden" name="currency_code" value="EUR">
                            <input type="hidden" name="amount" value="<?php echo $precio?>">
                            <input type="image" src="https://www.paypalobjects.com/es_XC/i/btn/x-click-but01.gif"
                            name="submit" alt="Make payments with PayPal - it's fast, free and secure!">
                        </form>
                        </center>
                    </td>
                </tr>
            </table>
        </section>
    </main>
    <footer>
        <p style="color: white; margin-left: 10px;">© 2023 tucamisetaoversize.es. Powered by tucamisetaoversize.es</p>
    </footer>
</body>
</html>
