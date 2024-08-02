<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Cliente extends Model
{
    //Definimos la tabla que vamos a usar
    protected $table = "Clientes";


    public $timestamps = false;
}
