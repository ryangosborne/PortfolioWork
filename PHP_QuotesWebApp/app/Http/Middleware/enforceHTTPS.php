<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Support\Facades\App;

class enforceHTTPS
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @return mixed
     */
    public function handle($request, Closure $next)
    {
        // only running middleware if in production and over http (determined with secure())
        if(App::environment('production') && !$request->secure()){
            // same path but making request secure / https
            return redirect()->secure($request->getRequestUri());
        }
        return $next($request);
    }
}
