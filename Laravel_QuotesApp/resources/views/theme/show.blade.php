@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <table class="table table-striped">
                        <tr>
                            <th>
                                <h4 class="pt-2">Theme Details for {{$theme->name}}</h4>
                            </th>
                            <th>
                                <a href="/admin/themes" class="btn btn-primary" style="float:right">Back to List</a>
                            </th>
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
                            <tr class="table-light">
                                <td colspan="8">Name: {{ $theme->name}}<br>
                                    CDN URL: {{ $theme->cdn_url}}<br>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
@endsection
