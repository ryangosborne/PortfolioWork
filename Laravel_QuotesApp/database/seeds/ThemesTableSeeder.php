<?php

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class ThemesTableSeeder extends Seeder
{
    public function run()
    {
        DB::table('themes')->insert([
            'name' => 'Cerulean',
            'cdn_url' => 'https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/cerulean/bootstrap.min.css',
            'created_by' => '1',
            'created_at' => now(),
            'updated_at' => now()
        ]);

        DB::table('themes')->insert([
            'name' => 'Cosmo',
            'cdn_url' => 'https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/cosmo/bootstrap.min.css',
            'created_by' => '2',
            'created_at' => now(),
            'updated_at' => now()
        ]);

        DB::table('themes')->insert([
            'name' => 'Sketchy',
            'cdn_url' => 'https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/sketchy/bootstrap.min.css',
            'created_by' => '3',
            'created_at' => now(),
            'updated_at' => now()
        ]);

        DB::table('themes')->insert([
            'name' => 'Minty',
            'cdn_url' => 'https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/minty/bootstrap.min.css',
            'created_by' => '4',
            'created_at' => now(),
            'updated_at' => now()
        ]);

        DB::table('themes')->insert([
            'name' => 'Lumen',
            'cdn_url' => 'https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/lumen/bootstrap.min.css',
            'created_by' => '5',
            'created_at' => now(),
            'updated_at' => now()
        ]);
    }
}

// used this as a resource for css links: https://www.bootstrapcdn.com/bootswatch/
