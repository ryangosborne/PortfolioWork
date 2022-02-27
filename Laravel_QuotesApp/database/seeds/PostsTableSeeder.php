<?php

use Illuminate\Database\Seeder;

class PostsTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('posts')->insert([
            'title'=>'Jane\'s Fav Quote',
            'quote'=>'We suffer more in imagination, than in reality',
            'created_by'=>'1',
            'created_at' => now(),
            'updated_at' => now()
        ]);
        DB::table('posts')->insert([
            'title'=>'Bob\'s Fav Quote',
            'quote'=>'Knowledge is the compound interest of curiosity',
            'created_by'=>'2',
            'created_at' => now(),
            'updated_at' => now()
        ]);
        DB::table('posts')->insert([
            'title'=>'Susan\'s Fav Quote',
            'quote'=>'if you don’t prioritize your life, someone else will',
            'created_by'=>'3',
            'created_at' => now(),
            'updated_at' => now()
        ]);
        DB::table('posts')->insert([
            'title'=>'Steve Jobs on creativity',
            'quote'=>'Creativity is just connecting things. When you ask creative people how they did something, they
            feel a little guilty because they didn\'t really do it, they just saw something. It seemed obvious to them
            after a while. That\'s because they were able to connect experiences they\'ve had and synthesize new things',
            'created_by'=>'4',
            'created_at' => now(),
            'updated_at' => now()
        ]);
    }
}
