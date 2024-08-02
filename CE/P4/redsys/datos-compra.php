<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle de Producto</title>
    <link rel="stylesheet" href="style-tienda.css" type="text/css" media="screen">
</head>
<style>
table tr td:first-child {
    font-weight: bold;
}
</style>
<body>
    <header>
        <a href="tienda-redsys.html" style="cursor: hand;">
            <img src="img/logoPequeno.png" height="85px">
        </a>  
    </header>
    <main style="width:100% !important">
        <h2 style="font-family: sans-serif;margin-top:15px;margin-bottom:0;margin-left:150px ">DATOS DE LA COMPRA</h2>
        <section>
        <?php
        $clave_secreta = "UMH2809";
        $merchant_code = "012809";
        $producto = "";
        $precio ="";
        $titular ="";
        $urlOK ="";
        $urlKO="";
        if (isset($_POST['producto']) && isset($_POST['amount']) && isset($_POST['titular']) && isset($_POST['return']) && isset($_POST['cancel_return'])) {
            $producto = $_POST['producto'];
            $precio = $_POST['amount'];
            $titular = $_POST['titular'];
            $urlOK = $_POST['return'];
            $urlKO = $_POST['cancel_return'];
        } else {
            echo '<p>No se han proporcionado detalles del producto.</p>';
        }
        function generarCadenaAleatoria() {
            $caracteresNumeros = '0123456789';
            $caracteresLetras = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
            $cadenaGenerada = '';
        
            // Generar los primeros 4 caracteres como números
            for ($i = 0; $i < 4; $i++) {
                $caracterAleatorio = $caracteresNumeros[rand(0, strlen($caracteresNumeros) - 1)];
                $cadenaGenerada .= $caracterAleatorio;
            }
        
            // Generar los siguientes 8 caracteres como números o letras
            for ($i = 0; $i < 8; $i++) {
                $caracterAleatorio = $caracteresNumeros . $caracteresLetras;
                $cadenaGenerada .= $caracterAleatorio[rand(0, strlen($caracterAleatorio) - 1)];
            }
        
            return $cadenaGenerada;
        }
    
        ?>
            
            <table>
                <tr>
                    <td colspan="2"><center>RESUMEN DE LA COMPRA</center></td>
                </tr>
                <tr> 
                    <td>Ds_Merchant_Amount</td>
                    <td><?php  $amount = intval($precio*100);
                        echo $amount; ?>
                    </td>
                </tr>
                <tr>
                    <td>Ds_Merchant_Currency</td>
                    <td>978</td>
                </tr>
                <tr>
                    <td>Ds_Merchant_Order</td>
                    <td><?php $order = generarCadenaAleatoria();
                        echo $order; ?>
                    </td>
                </tr>
                <tr>
                    <td>Ds_Merchant_ProductDescription</td>
                    <td><?php echo $producto; ?></td>
                </tr>
                <tr>
                    <td>Ds_Merchant_MerchantCode</td>
                    <td><?php echo $merchant_code ?></td>
                </tr>
                <tr>
                    <td>Ds_Merchant_MerchantName</td>
                    <td>TuCamisetaOversize</td>
                </tr>
                <tr>
                    <td>Ds_Merchant_Terminal</td>
                    <td>50</td>
                </tr>
                <tr>
                    <td>Ds_Merchant_TransactionType</td>
                    <td>0</td>
                </tr>
                <tr>
                    <td>Ds_Merchant_Titular</td>
                    <td><?php echo $titular; ?></td>
                </tr>
                <tr>
                    <td>Ds_Merchant_urlOK</td>
                    <td><?php echo $urlOK ?></td>
                </tr>
                <tr>
                    <td>Ds_Merchant_urlKO</td>
                    <td><?php echo $urlKO ?></td>
                </tr>
                <tr>
                    <td>Ds_Merchant_Signature</td>
                    <td>
                    <?php 
                    $firma_digital = $amount.$order.$merchant_code."978"."0".$clave_secreta;
                    $firma_digital_encriptada = sha1($firma_digital);
                    echo strtoupper($firma_digital_encriptada);
                    ?>

                    </td>
                </tr>
                <tr> 
                    <td colspan="2">
                        <center>
                        <a href="tienda-redsys.html" class="redsys-button">Volver a la tienda<a>
                        </center>
                    </td>
                </tr>
            </table>
        </section>
        <footer>
        <p style="color: white;margin-left: 10px;">© 2023 tucamisetaoversize.es. Powered by tucamisetaoversize.es</p>
        </footer>
    </main>
</body>
</html>
