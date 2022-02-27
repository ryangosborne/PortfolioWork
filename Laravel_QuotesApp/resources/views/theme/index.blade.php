@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <table class="table table-striped">
                        <tr>
                            <th>
                                <h4 class="pt-2">Theme Administration</h4>
                            </th>
                            <th>
                                <a class="btn btn-primary" href="/admin/themes/create" style="float:right">Create New Theme</a>
                            </th>
                        </tr>
                        <tr>
                            <th>Name</th>
                            <th>Actions</th>
                        </tr>
                        <tbody>
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
                        @foreach($themes as $theme)
                            <tr>
                                <td>{{ $theme->name }}</td>
                                <td colspan="1" class="col-md-6">
                                    <a class="btn btn-success" href="/admin/themes/{{$theme->id}}" role="button">Info</a>
                                    <a class="btn btn-warning ml-4" href="/admin/themes/{{$theme->id}}/edit" style="float: inside" role="button">Edit</a>
                                    <form action="/admin/themes/{{$theme->id}}" method="POST" style="float:right" class="col-md-6 mr-2">
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
