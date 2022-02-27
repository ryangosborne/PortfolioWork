@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <table class="table table-striped">

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

                        <tr>
                            <th colspan="3">
                                <h4 class="pt-2">User Administration</h4>
                            </th>
                            <th colspan="1">
                                <a class="btn btn-primary" href="/admin/users/create" style="float:right">Create New User Admin</a>
                            </th>
                        </tr>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Roles</th>
                            <th scope="col">Actions</th>
                        </tr>
                        <tbody>
                            @foreach($users as $user)
                                <tr>
                                    <td>{{ $user->name }}</td>
                                    <td>{{ $user->email }}</td>
                                    <td>
                                        <ul>
                                            @foreach($user->roles as $role)<li>{{ $role->name }}</li>@endforeach
                                        </ul>
                                    </td>
                                    <td>
                                        <a class="btn btn-success" href="/admin/users/{{ $user->id }}" role="button">Show</a>
                                        <a class="btn btn-warning" href="/admin/users/{{ $user->id }}/edit" role="button">Edit</a>
                                        <form action="/admin/users/{{ $user->id }}" method="POST" style="float:right">
                                            @method('DELETE')
                                            @CSRF
                                            <button class="btn btn-danger" type="submit">Delete</button>
                                        </form>
                                    </td>
                                </tr>
                            @endforeach
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
@endsection
