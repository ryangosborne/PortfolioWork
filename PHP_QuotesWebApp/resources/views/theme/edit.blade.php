@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">

                    <form action="/admin/themes/{{$theme->id}}" method="POST" class="pb-3">
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
                            <h4 class="pt-2">Edit Theme</h4>
                        </div>

                        <div class="form-group col-md mt-3">
                            <label for="name">Theme Name</label>
                            <input name="name" type="text" value="{{ old('name', $theme->name) }}" class="form-control" id="name" aria-describedby="nameHelp">
                            <small id="nameHelp" class="form-text text-muted">Enter the name for your theme, or edit it</small>
                            @error('name')
                                <div class="alert alert-danger"> {{ $message }}</div>
                            @enderror
                        </div>

                        <div class="form-group col-md">
                            <label for="themeLink">Theme URL</label>
                            <input type="url" name="themeLink" class="form-control" id="themeLink" value="{{ old('themeLink', $theme->cdn_url) }}">
                            <small id="themeLinkHelp" class="form-text text-muted">Enter the link for your theme, or edit it</small>
                            @error('themeLink')
                                <div class="alert alert-danger"> {{ $message }}</div>
                            @enderror
                        </div>

                        <div class="col-md mt-3">
                            <button type="submit" class="btn btn-success mr-2" style="float:left">Save</button>
                            <a href="/admin/themes" class="btn btn-primary">Back to Themes</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

@endsection
