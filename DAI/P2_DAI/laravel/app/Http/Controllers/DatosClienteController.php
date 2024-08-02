<?php
namespace App\Http\Controllers;
use App\Http\Controllers\Controller;
use App;

class DatosClienteController extends Controller{
    public function ver($id_cliente){
        $clientes = App\Cliente::where('Id_Cliente',$id_cliente)
                                ->get();

        return view('datoscliente',['clientes'=>$clientes]);
    }
}

?>