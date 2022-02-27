<?php

namespace App\Http\Middleware;

use App\User;
use Closure;
use Illuminate\Support\Facades\Auth;

class CheckThemeManager
{
    public function handle($request, Closure $next)
    {
        $users = User::with('roles')->get();
        if(Auth::user() && $users->find(Auth::user()->id)->roles()->find('3')){
            return $next($request);
        }
        return back()->with('messageDanger', 'Denied! You are not authorized to visit the page you requested');
    }
}
