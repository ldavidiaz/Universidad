<?php 
    include '../../auth.php';
    require_once '../conexion.php';
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
        .table-active{
            display:none!important;
        }
        h2{
            margin: 0;
            padding: 0;
        }
        .del-line-factura{
            background-color: #E64946;
            border: none;
            border-radius: 3px;
            color: #EDEDED;
            font-weight: bold;
        }
    </style>
</head>
<body onload="addLine()">
    <header>
        <p>MyAdmin-Embarcaciones</p>
        <a href="../../logout.php" style="margin-right:15px">
            <span>Salir &nbsp;<i class="bi bi-box-arrow-left" style="font-size:20px"></i></span>
        </a>
    </header>
    <!-- Obtener EL ID's y DNI's de los clientes -->
    <main>
        <a href="gestion_facturas.php" id="btn-volver">
            < Volver
        </a> 
        <h1 style="width:45%!important;height:10%!important">AÑADIR NUEVA FACTURA</h1>

        <form action="add-update-factura.php" method="POST" enctype="multipart/form-data" 
        class="ctd-app" style="width: 45%!important">

            <table style="display:flex;
                          align-items:center;
                          justify-content:start;">
                <tr>
                    <td>Numero de factura:</td>    
                    <td>
                        <input type="text" name="num_factura" required>
                    </td>
                </tr>
                <tr>                    
                    <td>Matricula:</td>    
                    <td>
                        <select name="matricula" required>
                        <?php
                            $sql = "SELECT Matricula FROM embarcaciones ORDER BY Matricula";
                            $resultado = $conexion->query($sql);
                            $rows = $resultado->fetchAll();
                            foreach($rows as $row){
                                $matricula = $row['Matricula'];
                                echo "
                                    <option value='$matricula'>$matricula</option>
                                ";
                            }

                        ?>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Mano de obra (horas):</td>    
                    <td>
                        <input type="text"  name="mano_obra" required>
                    </td>
                </tr>
                <tr>
                    <td>Precio por hora:</td>    
                    <td>
                        <input type="text"  name="precio_hora" required>
                    </td>
                </tr>
                <tr>
                    <td>Fecha de emision:</td>    
                    <td>
                        <input type="date" name="fechaE" id="fecha1" required>
                    </td>
                </tr>
                <tr>
                    <td>Fecha de pago:</td>    
                    <td>
                        <input type="date" name="fechaP" id="fecha2">
                    </td>
                </tr>               
                <tr>
                    <td>IVA:</td>    
                    <td>
                        <input type="text" name="iva" min="0">
                    </td>
                </tr>      
            </table> 

            <table id="tb-datos-factura"  style="display:flex;
                          align-items:center;
                          justify-content:start;"
                          class="table-active">
                <tbody id="tbody-datos-factura">
                    <tr><td colspan="4"><h2>Linea de detalles</h2></td></tr>
                    <tr>
                        <td></td>
                        <td><em>Repuesto<em></td>
                        <td><em>Unidades<em></td>
                    </tr>   
                </tbody>         
            </table> 

            <table  style="display:flex;
                          align-items:center;
                          justify-content:start;">
                <tr>
                    <td>
                        <button id="add-line" onclick="addLine()">Añadir linea de detalles</button>
                    </td>
                </tr>
                <tr>
                <td  style="display:none"><input name="add_update" value="añadir"></td>
                <td>
                        <input type="submit" value="Añadir factura"
                        class="inputs-add-update" >
                    </td>
                    <td>
                        <input type="reset" value="Limpiar formulario"
                        class="inputs-clear" >
                    </td>
                </tr>
            </table>
        </form>
    </main>

    <script>
        const tabla = document.getElementById("tb-datos-factura")
        const tbody = document.getElementById("tbody-datos-factura")
        
        function deleteLine(event) {
            var fila = event.target.parentNode.parentNode;
            var tbody22 = fila.parentNode;
            tbody.removeChild(fila);

            if (tbody.rows.length === 2) {
                tabla.classList.add("table-active");
                tbody.classList.add("table-active");
            }
        }

        function addLine(){
            /* 
                        Linea de detalles
                " " ID_DET_FACT NUM_FAC REF UNIDADES
                            (JAVASCRIPT)
                X   """""       """""   ""  """""
                X   """""       """""   ""  """""
                X   """""       """""   ""  """""            
            */

            //quitamos clase table-active
            tabla.classList.remove("table-active");
            tbody.classList.remove("table-active")
              // Crea una nueva fila
            var fila = document.createElement("tr");

            // Crea las celdas de la fila

            //BUTTON PARA ELIMINAR LINEA
            var celda1 = document.createElement("td");
            var btnCelda1 =document.createElement("button")
            btnCelda1.classList.add("del-line-factura")
            btnCelda1.textContent = "X"
            btnCelda1.addEventListener('click',()=>deleteLine(event))
            celda1.appendChild(btnCelda1)
            

            //CAMPO REFERENCIA
            var celda4 = document.createElement("td");
            var selectCelda4 = document.createElement("select")
            selectCelda4.name= "referencias[]"
            selectCelda4.required="true";
            <?php
            // Código PHP para generar las opciones del select
            // Supongamos que tienes un array de referencias llamado $referencias
            $sql2 = "SELECT Referencia,Descripcion FROM repuestos";
            $result = $conexion->query($sql2);
            $rows = $result->fetchAll();
            foreach ($rows as $row) {
                echo "var option = document.createElement('option');";
                echo "option.value = '" . $row['Referencia'] . "';";
                echo "option.textContent = '" . $row['Descripcion'] . "';";
                echo "selectCelda4.appendChild(option);";
            }
            ?>


            celda4.appendChild(selectCelda4)

            //CAMPO UNIDADES
            var celda5 = document.createElement("td");
            var inputCelda5 = document.createElement("input")
            inputCelda5.type = "number"
            inputCelda5.name= "unidades[]"
            inputCelda5.required="true";
            celda5.appendChild(inputCelda5)

            // Añade las celdas a la fila
            fila.appendChild(celda1);
            fila.appendChild(celda4);
            fila.appendChild(celda5);
            // Añade la fila a la tabla
            tbody.appendChild(fila);
        }
    </script>
    <script>
        const fecha1 = document.getElementById('fecha1');
        const fecha2 = document.getElementById('fecha2');

        // Agrega un evento de cambio al primer campo de fecha
        fecha1.addEventListener('change', () => {
            // Verifica si se ha seleccionado una fecha en el primer campo
            if (fecha1.value !== '') {
               /*  fecha2.setAttribute('required', 'true'); */
                fecha2.setAttribute('min', fecha1.value);
            } else {
                /* fecha2.removeAttribute('required'); */
                fecha2.removeAttribute('min');
            }
        });
    </script>

    <!-- TODO eliminar footer, aumentar height del main -->
<!--     <footer>
        Luis David Diaz Mesa
    </footer> -->
</body>
</html>