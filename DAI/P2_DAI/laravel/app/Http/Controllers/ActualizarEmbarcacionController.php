<?php
namespace App\Http\Controllers;
use App\Http\Controllers\Controller;
use App;

class ActualizarEmbarcacionController extends Controller{
    public function actualizar($matricula,$anyo){
        $embarcaciones =App\Embarcacion::where('Matricula',$matricula)
                                    ->update(['Año'=>$anyo]);
        $embarcaciones = App\Embarcacion::where('Matricula',$matricula)
                                    ->get();
        return view('actualizar_embarcacion',['embarcaciones'=>$embarcaciones]);
    }
}

?>