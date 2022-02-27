@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">

                    <form action="/post" method="POST" class="pb-3">
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
                            <h4 class="pt-2">Create New Post</h4>
                        </div>

                        <div class="form-group col-md mt-3">
                            <label for="title">Post Title</label>
                            <input name="title" type="text" value="{{ old('title') }}" class="form-control" id="title" aria-describedby="titleHelp">
                            <small id="titleHelp" class="form-text text-muted">Enter a title for your post</small>
                            @error('title')
                                <div class="alert alert-danger"> {{ $message }}</div>
                            @enderror
                        </div>

                        <div class="form-group col-md">
                            <label for="quote">Quote</label>
                            <input type="text" name="quote" class="form-control" id="quote" value="{{ old('quote') }}">
                            <small id="quoteHelp" class="form-text text-muted">Enter the quote you want to share</small>
                            @error('quote')
                                <div class="alert alert-danger"> {{ $message }}</div>
                            @enderror
                        </div>

                        <div class="col-md mt-3">
                            <button type="submit" class="btn btn-success mr-2" style="float:left">Create</button>
                            <a href="/home" class="btn btn-primary">Back to Feed</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

@endsection
