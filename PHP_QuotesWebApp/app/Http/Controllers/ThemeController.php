<?php

namespace App\Http\Controllers;

use App\Theme;
use Illuminate\Http\Request;

class ThemeController extends Controller
{
    public function index()
    {
        $themes = Theme::all();
        return view('theme.index', compact('themes'));
    }

    public function create()
    {
        return view('theme.create');
    }

    public function store(Request $request)
    {
        request()->validate([
            'name' => 'required',
            'themeLink' => 'required|URL|ends_with:css'
        ]);
        $theme = new Theme();
        $theme->name = request('name');
        $theme->cdn_url = request('themeLink');
        $theme->created_by = auth()->id();
        $theme->save();
        return redirect('/admin/themes')->with('messageSuccess', 'Theme created successfully');
    }

    public function show(Theme $theme)
    {
        $theme->load('user');
        return view('theme.show', compact('theme'));
    }

    public function edit(Theme $theme)
    {
        $theme->load('user');
        return view('theme.edit', compact('theme'));
    }

    public function update(Request $request, Theme $theme)
    {
        request()->validate([
            'name' => 'required',
            'themeLink' => 'required|URL|ends_with:css'
        ]);
        $theme->update([
            'name'=>request('name'),
            'cdn_url'=>request('themeLink'),
            'updated_by'=>auth()->id()
        ]);
        $theme->save();
        return redirect('/admin/themes')->with('messageSuccess', 'Theme edited successfully');
    }

    public function destroy(Theme $theme)
    {
        $theme->update(['deleted_by'=>auth()->id()]);
        $theme->save();
        $theme->delete();
        return redirect('/admin/themes')->with('messageSuccess', 'Theme deleted successfully');
    }

    // switching theme
    public function switch(Theme $theme){
        // functionality to set cookie OR delete cookie if user sets to default
        return $theme;
    }

    // implementing middleware
    public function __construct(){
        $this->middleware('CheckThemeManager');
    }
}
