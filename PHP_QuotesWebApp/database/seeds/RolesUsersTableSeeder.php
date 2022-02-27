<?php

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class RolesUsersTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        // retrieve roles
        $admin = DB::table('roles')->where('name', '=', 'User Administrator')->first();
        $moderator = DB::table('roles')->where('name', '=', 'Moderator')->first();
        $themeManager = DB::table('roles')->where('name', '=', 'Theme Manager')->first();

        // retrieve users
        $jane = DB::table('users')->where('name', '=', 'Jane UserAdmin')->first();
        $bob = DB::table('users')->where('name', '=', 'Bob Moderator')->first();
        $susan = DB::table('users')->where('name', '=', 'Susan ThemeAdmin')->first();
        $billy = DB::table('users')->where('name', '=', 'Billy Admin')->first();

        DB::table('role_user')->insert([
            'user_id' => $jane->id,
            'role_id' => $admin->id,
            'created_at' => now(),
            'updated_at' => now()
        ]);

        DB::table('role_user')->insert([
            'user_id' => $bob->id,
            'role_id' => $moderator->id,
            'created_at' => now(),
            'updated_at' => now()
        ]);

        DB::table('role_user')->insert([
            'user_id' => $susan->id,
            'role_id' => $themeManager->id,
            'created_at' => now(),
            'updated_at' => now()
        ]);

        DB::table('role_user')->insert([
            'user_id' => $billy->id,
            'role_id' => $admin->id,
            'created_at' => now(),
            'updated_at' => now()
        ]);
    }
}
