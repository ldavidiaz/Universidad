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
    </style>
</head>
<body>
    <header>
        <p>MyAdmin-Embarcaciones</p>
        <a href="../../logout.php" style="margin-right:15px">
            <span>Salir &nbsp;<i class="bi bi-box-arrow-left" style="font-size:20px"></i></span>
        </a>
    </header>
    <!-- Obtener EL ID's y DNI's de los clientes -->
    <main>
        <a href="../menu_inicial.php" id="btn-volver" style="transform:translate(20px,20px)!important">
            < Volver
        </a> 
        <h1>Gestión de embarcaciones</h1>
        <form action="borrar_embarcacion.php" method="POST" class="ctd-app">
            <div id="ctd-btns">
                <a href="add_embarcacion.php" id="btn-add-cliente">
                    Añadir embarcacion
                </a>
                <input type="submit" id="btn-eliminar-cliente" value="Eliminar" disabled/>
                    <!-- <i></i> -->
            </div>
            <table style="display:flex;
                          align-items:space-between;
                          justify-content:space-between;">
                <tr>
                    <th>Seleccionar</th>
                    <th>Matricula embarcacion</th>
                </tr>
            <!-- Conexion con la BD y pintar tabla -->
            <?php
                include("../conexion.php");
                //definimos la cadena de la consulta
                $sql = "SELECT Matricula FROM embarcaciones ORDER BY Matricula";
                $result = $conexion->query($sql);
                $rows = $result->fetchAll();

                // Como los valores están en un array asolciativo,
                // usamos foreach para extraer los valores de $rows
                foreach($rows as $row){
                    $matricula = $row['Matricula'];
                    echo "<tr>
                                <td>
                                    <center>
                                        <input type='checkbox'
                                        name='borrar[]' value='$matricula'>
                                    </center>  
                                </td>
                                <td>
                                    <a href='editar_embarcacion.php?matricula=$matricula' style='color: darkblue;text-decoration:underline!important;'>
                                        <center>$matricula</center>
                                    </a>
                                </td>
                            </tr>";
                }
            ?>
            </table>    
        </form>
    </main>
    <!-- TODO eliminar footer, aumentar height del main -->
<!--     <footer>
        Luis David Diaz Mesa
    </footer> -->
    <script>
        const btnEliminar =document.getElementById("btn-eliminar-cliente");
        const checkbox = document.getElementsByTagName("input");
        for(var j=0;j<checkbox.length;j++){
            checkbox[j].addEventListener("click",()=>changeActiveBtn());
        }
        function changeActiveBtn(){
            let i=0
            let cont=0
            for(i;i<checkbox.length;i++){
                if(checkbox[i].checked==true){
                    btnEliminar.classList.add("btn-active")
                    btnEliminar.removeAttribute("disabled")
                    cont++
                }
            }
            if(cont==0){
                    btnEliminar.classList.remove("btn-active");
                    btnEliminar.setAttribute("disabled","true")
                }
        }
    </script>
</body>
</html>