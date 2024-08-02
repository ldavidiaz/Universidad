<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| This file is where you may define all of the routes that are handled
| by your application. Just tell Laravel the URIs it should respond
| to using a Closure or controller method. Build something great!
|
*/
Route::get('/', function () {
    return view('welcome');
});
Route::get('actualizar_embarcacion/{matricula}/{anyo}','ActualizarEmbarcacionController@actualizar');
Route::get('datoscliente/{id_cliente}', 'DatosClienteController@ver');

/*
Route::get('/tienda/productos/{id_producto}', function ($id_producto) {
    return "Mostrando el producto $id_producto de la tienda";
});

Route::get('/agenda/{mes}/{anyo}', function ($mes,$anyo) {
    return "Viendo la agenda de $mes de $anyo";
});

Route::get('listarclientes', function () {
    return view('listarclientes');
});

Route::get('listarclientes/poblacion/{poblacion}', function ($poblacion) {
    return view('clientes_por_poblacion',['poblacion'=>$poblacion]);
});

Route::get('eliminarcliente/{nombre}', function ($nombre) {
    return view('eliminar_cliente',['nombre'=>$nombre]);
});
 Route::get('nuevocliente/{dni}/{nombre}/{apellido}', function ($dni,$nombre,$apellido1) {
    return view('nuevo_cliente',['dni'=>$dni,'nombre'=>$nombre,'apellido1'=>$apellido1]);
}); */
?>