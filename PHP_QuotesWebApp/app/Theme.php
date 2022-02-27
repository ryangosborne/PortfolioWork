<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Theme extends Model
{
    use SoftDeletes;
    protected $guarded = [];

    function user(){
        return $this->belongsTo(User::class, 'id');
    }
}
