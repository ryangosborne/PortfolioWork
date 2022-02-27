@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">

                    <form action="/admin/users/{{$user->id}}" method="POST" class="pb-3">

                        @method('PUT')
                        @CSRF

                        @if(Session::has('messageSuccess'))
                            <div class="alert-success" style="padding: 20px; font-size: medium">
                                {{ Session::get('messageSuccess') }}
                            </div>
                        @endif
                        @if(Session::has('messageDanger'))
                            <div class="alert-danger" style="padding: 20px; font-size: medium">
                                {{ Session::get('messageDanger') }}
                            </div>
                        @endif

                        <div class="card-header col-md">
                            <h4 class="pt-2">Edit User</h4>
                        </div>

                        <div class="form-group col-md mt-3">
                            <label for="name">Name</label>
                            <input name="name" type="text" value="{{ old('name', $user->name) }}" class="form-control" id="name" aria-describedby="nameHelp">
                            <small id="nameHelp" class="form-text text-muted">Enter your name, or update it</small>
                            @error('name')
                                <div class="alert alert-danger">{{ $message }}</div>
                            @enderror
                        </div>

                        <div class="form-group col-md">
                            <label for="email">Email</label>
                            <input type="email" name="email" class="form-control" id="email" value="{{ old('email', $user->email) }}">
                            <small id="emailHelp" class="form-text text-muted">Enter your email, or update it</small>
                            @error('email')
                                <div class="alert alert-danger">{{ $message }}</div>
                            @enderror
                        </div>

                        <div class="form-group col-md">
                            <label for="roles">Roles</label>
                            <small id="rolesHelp" class="form-text text-muted">Change the user's roles</small>
                        </div>

                        <!-- looping through roles, checking if user has role, if yes, check box -->
                        <div class="form-group col-md">
                            @foreach($roles as $role)

                                <div>
                                    <input type="checkbox" id="{{ $role->name }}" name="roles[]" value="{{( $loop->index) + 1 }}"
                                    {{ $user->roles()->find($role)==true ? 'checked' : '' }}>

                                    <label for="{{$role->name}}">{{$role->name}}</label>
                                </div>

                            @endforeach
                            @error('roles')
                                <div class="alert alert-danger">{{ $message }}</div>
                            @enderror
                        </div>


                        <div class="col-md mt-3">
                            <button type="submit" class="btn btn-success mr-2" style="float:left">Submit</button>
                            <a href="/admin/users" class="btn btn-primary">Back to List</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
@endsection
