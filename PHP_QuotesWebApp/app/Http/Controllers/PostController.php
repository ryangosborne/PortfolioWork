<?php

namespace App\Http\Controllers;

use App\Post;
use App\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;


class PostController extends Controller
{
    public function index()
    {
        $posts = Post::with('user')->orderBy('created_at', 'DESC')->get();
        $users = User::with('roles')->get();
        return view('/home', compact('posts', 'users'));
    }

    public function create()
    {
        return view('post.create');
    }

    public function store(Request $request)
    {
        $request = request()->validate([
            'title' => 'required|min:3',
            'quote' => 'required|min:15',
        ]);
        $post = new Post();
        $post->title = request('title');
        $post->quote = request('quote');
        $post->created_by = auth()->id();
        $post->save();
        return redirect('/home')->with('messageSuccess', 'Post created successfully');
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Post  $post
     * @return \Illuminate\Http\Response
     */
    public function show(Post $post)
    {
        // not implemented
    }

    public function edit($id)
    {
        $post = Post::find($id)->load('user');
        return view ('post.edit', compact('post'));
    }

    public function update(Request $request, Post $post)
    {
        $data = request()->validate([
            'title' => 'required|min:3',
            'quote' => 'required|min:15',
        ]);
        $post->title = $request->input('title');
        $post->quote = $request->input('quote');
        $post->save();
        return redirect('/home')->with('messageSuccess', 'Post edited successfully');
    }

    public function destroy(Post $post)
    {
        $post->update(['deleted_by'=>auth()->id()]);
        $post->save();
        $post->delete();
        return redirect('/home')->with('messageSuccess', 'Post deleted successfully');
    }
}
