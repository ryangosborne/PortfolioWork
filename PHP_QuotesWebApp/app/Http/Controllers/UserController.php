<?php

namespace App\Http\Controllers;

use App\User;
use App\Role;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;

class UserController extends Controller
{

    public function index()
    {
        //OPTION 1
        $users = User::with('roles')->has('roles')->get();
        //OPTION 2
//        $users = User::has('roles')->get();
//        $users->load('roles'); // lazy load
        return view('user.index', compact('users'));
    }

    public function create()
    {
        $roles = Role::all();
        return view('user.create', compact('roles'));
    }

    public function store(Request $request)
    {
        $data = request()->validate([
            'name' => 'required',
            'email' => 'required|email',
            'password' => 'required|min:8',
            'roles' => 'required|min:1'
        ]);

        $user = new User();
        $user->name = request('name');
        $user->email = request('email');
        $user->password = Hash::make(request('password'));
        $user->save();

        foreach(request()->roles as $role){ // makeMany? better way to do this?
            DB::table('role_user')->insert([
                'role_id' => $role,
                'user_id' => $user->id
            ]);
        }

        return redirect('/admin/users')->with('messageSuccess', 'New Admin User created successfully');
    }


    public function show(User $user)
    {
//        $user->load('role');
//        $user = User::with('roles')->has('roles')->get();
//        $user = User::has('roles')->get();
        $user->load('roles'); // lazy load
        return view('user.show', compact('user'));
    }

    public function edit($id)
    {
        $user = User::find($id)->load('roles');
        $roles = Role::all();
        return view('user.edit', compact('user', 'roles'));
    }

    public function update(Request $request, $id)
    {
        $data = request()->validate([
            'name' => 'required',
            'email' => 'required|email',
            'roles' => 'required|min:1'
        ]);

        // this works, but doesn't fire timestamps on pivot table.
        $user = User::find($id);
        $user->name = $request->input('name');
        $user->email = $request->input('email');
        $user->roles()->sync($request->roles);
        $user->save();
        return redirect('/admin/users')->with('messageSuccess', 'User edited successfully');
    }

    public function destroy($id)
    {
        $user = User::find($id);
        $user->update(['deleted_by'=>auth()->id()]);
        $user->save();
        $user->delete();
        return redirect('/admin/users')->with('messageSuccess', 'User deleted successfully');
    }

    // implementing middleware
    public function __construct(){
        $this->middleware('CheckAdmin');
    }
}
