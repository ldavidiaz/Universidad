
<?php
session_start();
$_SESSION['isAuth']=false;
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../pr2/style/layout.css" media="screen"/>
    <!-- FUENTE Roboto -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    <!-- ICONOS BOOSTRAP -->


    <title>Practica 2</title>
</head>
<body>
    <header>
        <p>MyAdmin-Embarcaciones</p>
<!--         <a href="login.php">
            <span class="">salir</span>
        </a> -->
    </header>
    <main>
        <h1>Iniciar sesion</h1>
        <form action="../pr2/login.php" method="POST">
            <table >    
                <tr>
                    <td><label for="usuario">Usuario:</label></td>
                    <td><input type="text" name="usuario"></td>
                </tr>
                <tr>
                    <td><label for="password">Contraseña:</label></td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td colspan="2"><center><input type="submit" value="ENTRAR"></center></td>
                </tr>
            </table>
            <?php  
            if($_POST!=null){
                $user = $_POST["usuario"];
                $pwd = $_POST["password"];
                if($user=="admin" && $pwd=="1234"){
                    $_SESSION['isAuth']=true;
                    header("Location: ../pr2/admin/menu_inicial.php");
                    exit();
                }else{
                    echo "<small>*Usuario o contraseña incoreccto<small>";
                }
            }
            ?>      
        </form>

    </main>
    <footer>
        Luis David Diaz Mesa
    </footer>
    
</body>
</html>