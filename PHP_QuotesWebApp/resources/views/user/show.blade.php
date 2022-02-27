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
                            <th>
                                <h4 class="pt-2">User Details</h4>
                            </th>
                            <th>
                                <a href="/admin/users" class="btn btn-primary" style="float:right">Back to List</a>
                            </th>
                        </tr>
                        <tbody>
                            <tr class="table-light">
                                <td colspan="8">Name: {{ $user->name}}<br>
                                Email: {{ $user->email}}<br>
                                Current Roles:<br>
                                    <ul>
                                        @foreach($user->roles as $role)<li>{{ $role->name }}</li>@endforeach
                                    </ul>
                                </td>
                            </tr>
                            <!--
                            <tr>
                                <td colspan="8">
                                        <a class="btn btn-warning" href="" role="button">Edit</a>
                                        <a class="btn btn-danger" href="" role="button">Delete</a>
                                </td>
                            </tr>
                            -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
@endsection
