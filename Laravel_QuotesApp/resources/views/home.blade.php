@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card pb-lg-2">
                <table class="table table-striped">
                    <tr>
                        <th colspan="10">
                            <h4 class="pt-2">The Feed</h4>
                        </th>
                        @if(Auth::check())
                            <th colspan="1">
                                <a href="/post/create" class="btn btn-primary" style="float:right">Create New Post</a>
                            </th>
                        @endif
                    </tr>
                    <tbody>
                        @if(Session::has('messageDanger'))
                            <div class="alert-danger" style="padding: 20px; font-size: medium">
                                {{ Session::get('messageDanger') }}
                            </div>
                        @endif
                        @if(Session::has('messageSuccess'))
                            <div class="alert-success" style="padding: 20px; font-size: medium">
                                {{ Session::get('messageSuccess') }}
                            </div>
                        @endif
                        @foreach($posts as $post)
                            <table class="table-light" style="border:4px double dodgerblue; margin: 8px 16px 8px 16px">
                                <tr>
                                    <th style="height:80px">
                                        <h5 class="pl-lg-3">{{$post->title}}</h5>
                                    </th>
                                </tr>
                                <tr>
                                    <td class="pl-lg-3 pr-lg-2 pb-3" style="font-size: medium">
                                        {{$post->quote}}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="pl-lg-3" style="height:50px; font-size: medium; font-style: italic">
                                        Posted by {{ $users[$post->created_by-1]->name }}
                                        on {{ $post->created_at->format("D M. d, Y H:i") }}
                                    </td>
                                </tr>
                                <!-- checking if a user is logged in -->
                                @auth
                                    <tr>
                                        <!-- checking if user created this post -->
                                        @if($users[$post->created_by-1]->id == Auth::user()->id)
                                            <td class="pl-lg-3 pb-3">
                                                <a class="btn btn-warning" style="width: 100px" href="/post/{{$post->id}}/edit" role="button">Edit</a>
                                            </td>
                                        @endif
                                        <!-- checking if user created this post OR user is Moderator -->
                                        @if(($users[$post->created_by-1]->id == Auth::user()->id) || ($users->find(Auth::user()->id)->roles()->find('2')))
                                            <td class="pb-3 pr-3" style="float: right">
                                                <form action="/post/{{$post->id}}" method="POST">
                                                    @method('DELETE')
                                                    @CSRF
                                                    <button class="btn btn-danger" style="width: 100px" type="submit">Delete</button>
                                                </form>
                                            </td>
                                        @endif
                                    </tr>
                                @endauth
                            </table>
                        @endforeach
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
@endsection
