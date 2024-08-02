<?php 
    include '../../auth.php'
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="../../style/layout.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="../../style/layout-apps.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="../../style/layout-info.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="../../style/layout-formularios.css" media="screen"/>
    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- ICONOS Boostrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <!-- FUENTE Roboto -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    <title>Practica 2</title>
    <style>
        h1,form{
            transform: translateX(20%);
        }
        .a-tablas{
                height:100px;
                width:90px;
                background-color: #457b9d;
                border-radius:4px;
                display:flex;
                flex-direction:column;
                align-items:center;
                justify-content:center;
                cursor:pointer;
        }
        .i-tablas{
            font-size: 3em!important;
        }
    </style>
</head>
<body>
    <header>
        <p>MyAdmin-Embarcaciones</p>
        <a href="../../logout.php" style="margin-right:15px">
            <span>Salir &nbsp;<i class="bi bi-box-arrow-left" style="font-size:20px"></i></span>
        </a>
    </header>
    <main>
        <a href="../menu_inicial.php" id="btn-volver" style="transform:translate(20px,20px)!important">
            < Volver
        </a> 
        <h1>Consultar tablas de la BD</h1>
        <section style="display:flex;justify-content:center;gap:10px;">
            <a href="tabla_embarcaciones.php" class="a-tablas">
                <i class="bi bi-person i-tablas" ></i>
                <p class="name-app">Clientes</p>
            </a>
            <a href="tabla_facturas.php"  class="a-tablas">
                <i class="bi bi-receipt i-tablas"></i>
                <p class="name-app">Facturas</p>
            </a>
        </section>

    </main>
</body>
</html>