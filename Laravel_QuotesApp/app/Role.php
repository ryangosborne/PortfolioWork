<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Role extends Model
{
    protected $guarded = []; // turning of mass assignment protection

    function users(){
        return $this->belongsToMany(User::class);
    }
}
