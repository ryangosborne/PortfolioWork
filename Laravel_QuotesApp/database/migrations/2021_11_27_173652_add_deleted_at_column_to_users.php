<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddDeletedAtColumnToUsers extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('users', function (Blueprint $table) {
            $table->timestamp('deleted_at')->nullable();
        });
        Schema::table('posts', function (Blueprint $table){
           $table->timestamp('deleted_at')->nullable();
        });
        Schema::table('themes', function (Blueprint $table){
            $table->timestamp('deleted_at')->nullable();
        });
    }

    public function down()
    {
        //
    }
}
