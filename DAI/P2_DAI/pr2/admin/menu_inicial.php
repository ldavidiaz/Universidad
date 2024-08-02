<?php include '../auth.php' ?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="../style/layout.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="../style/layout-apps.css" media="screen"/>
    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- ICONOS Boostrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <!-- FUENTE Roboto -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    <title>Practica 2</title>
</head>
<body>
    <header>
        <p>MyAdmin-Embarcaciones</p>
        <a href="../logout.php" style="margin-right:15px">
            <span>Salir &nbsp;<i class="bi bi-box-arrow-left" style="font-size:20px"></i></span>
        </a>
    </header>
    <main>
        <h1>Aplicaciones</h1>
        <section class="layout-apps">
            <a href="clientes/gestion_clientes.php"  class="app1">
                <i class="bi bi-person-gear" ></i>
                <p class="name-app">Gestión de clientes</p>
            </a>
            <a href="embarcaciones/gestion_embarcaciones.php" class="app2">
                <i class="fas fa-ship" 
                style="color: #f0f0f0;padding-top:13px; font-size: 6em !important;"></i>
                <p class="name-app">Gestión de embarcaciones</p>
            </a>
            <a href="repuestos/gestion_repuestos.php" class="app3">
                <i class="bi bi-gear-wide-connected"></i>
                <p class="name-app">Gestión de repuestos</p>
            </a>
            <a href="facturas/gestion_facturas.php" class="app4">
                <i class="bi bi-receipt"></i>
                <p class="name-app">Gestión de facturas</p>
            </a>
            <a href="consultas/consultar_tablas.php" class="app5">
                <i class="bi bi-database"></i>
                <p class="name-app">Consultar BD</p>
            </a>
            <a href="../logout.php" class="sair">
                <i class="bi bi-box-arrow-left" style="padding-right:20px"></i>
                <p class="name-app">Cerrar sesión</p>
            </a>
        </section>
    </main>
<!--     <footer>
        Luis David Diaz Mesa
    </footer> -->
</body>
</html>