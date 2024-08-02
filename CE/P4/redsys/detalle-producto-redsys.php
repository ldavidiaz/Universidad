<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle de Producto</title>
    <link rel="stylesheet" href="style-tienda.css" type="text/css" media="screen">
</head>
<body>
    <header>
        <a href="tienda-redsys.html" style="cursor: hand;">
            <img src="img/logoPequeno.png" height="85px">
        </a>  
    </header>
    <main>
        <h2 style="font-family: sans-serif;margin-top:15px;margin-bottom:0 ">RESUMEN DEL PEDIDO</h2>
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
            
            <table>
            <form action="datos-compra.php" method="post">
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
                    
                    <td colspan="2"><center><strong>Introduce tus datos</strong></center></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <center>
                            <label for="titular">Nombre y apellidos: </label><input type="text" name="titular" required>
                        </center>
                    </td>
                </tr>
                <tr>
                    
                    <td colspan="2">
                        <center>
                        <!-- VERSION RETORNO AUTOMATICO DESACTIVADO --> 
                            <input type="hidden" name="producto" value="<?php echo $producto?>">
                            <input type="hidden" name="currency_code" value="EUR">
                            <input type="hidden" name="amount" value="<?php echo $precio?>">
                            <input type="hidden" name="return" value="https://umh2809.umh.es/material/practicas/implementacion-de-una-pagina-de-pago-por-paypal/compra-realizada-con-exito-return/">
                            <input type="hidden" name="cancel_return" value="https://umh2809.umh.es/material/practicas/implementacion-de-una-pagina-de-pago-por-paypal/error-en-el-pago-cancel_return/">
                            <input type="submit" class="redsys-button" value="Pagar con redsys" alt="Pago seguro con redsys">
                        </center>
                    </td>
                </tr>
            </form>
            </table>
            
        </section>
    </main>
    <footer>
        <p style="color: white;margin-left: 10px;">© 2023 tucamisetaoversize.es. Powered by tucamisetaoversize.es</p>
    </footer>
</body>
</html>
