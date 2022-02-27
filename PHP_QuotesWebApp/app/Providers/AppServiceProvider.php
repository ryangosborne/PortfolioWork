<?php

namespace App\Providers;

use Illuminate\Support\Facades\App;
use Illuminate\Support\Facades\URL;
use Illuminate\Support\ServiceProvider;

class AppServiceProvider extends ServiceProvider
{
    /**
     * Register any application services.
     *
     * @return void
     */
    public function register()
    {
        //
    }

    /**
     * Bootstrap any application services.
     *
     * @return void
     */
    public function boot()
    {
        // this function is called very early into the app.

        // configuration
        // forcing HTTPS for every request in application IF environment is production (not local)
        if(App::environment('production')) {
            URL::forceScheme('https');
        }

        // query for data and may pass to your application
    }
}
