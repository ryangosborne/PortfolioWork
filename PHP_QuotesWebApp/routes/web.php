<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {return view('welcome');});

Auth::routes();

Route::get('/home', 'PostController@index');

Route::resource('/admin/users', 'UserController');

Route::resource('/post', 'PostController');

Route::resource('/admin/themes', 'ThemeController');
Route::post('/theme/switch/{theme}', 'ThemeController@switch');
