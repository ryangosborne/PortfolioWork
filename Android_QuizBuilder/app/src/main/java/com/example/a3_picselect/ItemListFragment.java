package com.example.a3_picselect;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3_picselect.databinding.FragmentItemListBinding;
import com.example.a3_picselect.databinding.ItemListContentBinding;

import com.example.a3_picselect.placeholder.AlbumContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A fragment representing a list of Items. This fragment
 * has different presentations for handset and larger screen devices. On
 * handsets, the fragment presents a list of items, which when touched,
 * lead to a {@link ItemDetailFragment} representing
 * item details. On larger screens, the Navigation controller presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListFragment extends Fragment {

    private SharedPreferences prefs; // preferences object
    private static final String ALBUM_KEY = "picList"; // this will need to change
    private static List<String> chosenAlbums; // will hold names of pics that have been selected (for preferences)

    ViewCompat.OnUnhandledKeyEventListenerCompat unhandledKeyEventListenerCompat = (v, event) -> {
        if (event.getKeyCode() == KeyEvent.KEYCODE_Z && event.isCtrlPressed()) {
            Toast.makeText(
                    v.getContext(),
                    "Undo (Ctrl + Z) shortcut triggered",
                    Toast.LENGTH_LONG
            ).show();
            return true;
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_F && event.isCtrlPressed()) {
            Toast.makeText(
                    v.getContext(),
                    "Find (Ctrl + F) shortcut triggered",
                    Toast.LENGTH_LONG
            ).show();
            return true;
        }
        return false;
    };

    private FragmentItemListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentItemListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // getting string array from strings.xml for album names - not functioning
//        Resources res = getResources();
//        String[] album_names1 = res.getStringArray(R.array.album_names_resource);
//        for(int i =0; i < 5; i++){
//            AlbumContent.album_names_array.add(album_names1[i]);
//        }

        ViewCompat.addOnUnhandledKeyEventListener(view, unhandledKeyEventListenerCompat);

        RecyclerView recyclerView = binding.itemList;

        // Leaving this not using view binding as it relies on if the view is visible the current
        // layout configuration (layout, layout-sw600dp)
        View itemDetailFragmentContainer = view.findViewById(R.id.item_detail_nav_container);

        /* Click Listener to trigger navigation based on if you have
         * a single pane layout or two pane layout
         */
        View.OnClickListener onClickListener = itemView -> {
            AlbumContent.AlbumItem item = (AlbumContent.AlbumItem) itemView.getTag();

            // will determine if album has been previously clicked by user
            boolean previouslySelected = (chosenAlbums.contains(item.name));

            if(previouslySelected){ // if it HAS been selected
                Toast.makeText(itemView.getContext(), "Already selected " + item.name, Toast.LENGTH_LONG).show();
            }
            else{ // if it HAS NOT been selected
                // add selected item to chosenAlbums list and save to preferences
                chosenAlbums.add(item.name);
                savePreferences();
                Bundle arguments = new Bundle();
                arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.name);

                // then going to detail fragment
                if (itemDetailFragmentContainer != null) {
                    Navigation.findNavController(itemDetailFragmentContainer)
                            .navigate(R.id.fragment_item_detail, arguments);
                } else {
                    Navigation.findNavController(itemView).navigate(R.id.show_item_detail, arguments);
                }
            }
        }; // end itemView

        /*
         * Context click listener to handle Right click events from mice and trackpad input to
         * provide a more native experience on larger screen devices
         */
        View.OnContextClickListener onContextClickListener = itemView -> {
            AlbumContent.AlbumItem item = (AlbumContent.AlbumItem) itemView.getTag();
            Toast.makeText(itemView.getContext(), "Context click of item " + item.name, // changed from id to name
                    Toast.LENGTH_LONG).show();
            return true;
        };

        setupRecyclerView(recyclerView, onClickListener, onContextClickListener);
        loadPreferences(); // calls the preferences to be loaded. the data persists until it is cleared with FAB
        String[] names = getResources().getStringArray(R.array.album_names_resource); // for pic locations i think

        FloatingActionButton fab_toDB2 = view.findViewById(R.id.fab_toDB);
        fab_toDB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenAlbums.clear(); // clearing list (for reset)
                // creating intent to go to DB activity
                Intent intent = new Intent("DBActivity");
                startActivityForResult(intent, 1);
            }
        }); // end handler for FAB
    } // end onViewCreated

    private void setupRecyclerView(
            RecyclerView recyclerView,
            View.OnClickListener onClickListener,
            View.OnContextClickListener onContextClickListener
    ) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(
                AlbumContent.ITEMS,
                onClickListener,
                onContextClickListener
        ));
    } // end setupRecyclerView

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    } // end onDestroyView

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<AlbumContent.AlbumItem> mAlbums;
        private final View.OnClickListener mOnClickListener;
        private final View.OnContextClickListener mOnContextClickListener;

        SimpleItemRecyclerViewAdapter(List<AlbumContent.AlbumItem> items,
                                      View.OnClickListener onClickListener,
                                      View.OnContextClickListener onContextClickListener) {
            mAlbums = items;
            mOnClickListener = onClickListener;
            mOnContextClickListener = onContextClickListener;
        } // end SimpleItemRecyclerViewAdapter

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ItemListContentBinding binding =
                    ItemListContentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolder(binding);
        } // end onCreateViewHolder

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mAlbumIcon.setImageResource(mAlbums.get(position).icon);
            holder.mContentView.setText(mAlbums.get(position).name);
            holder.itemView.setTag(mAlbums.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);

            // checking if albums have been selected and greying them out
            if(chosenAlbums.contains(holder.mContentView.getText())){
                holder.itemView.setBackgroundColor(Color.LTGRAY);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.itemView.setOnContextClickListener(mOnContextClickListener);
            }
            holder.itemView.setOnLongClickListener(v -> {
                // Setting the item id as the clip data so that the drop target is able to
                // identify the id of the content
                ClipData.Item clipItem = new ClipData.Item(mAlbums.get(position).name);
                ClipData dragData = new ClipData(((AlbumContent.AlbumItem) v.getTag()).name,
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, clipItem
                );
                if (Build.VERSION.SDK_INT >= 24) {
                    v.startDragAndDrop(dragData, new View.DragShadowBuilder(v), null, 0);
                } else {
                    v.startDrag(dragData, new View.DragShadowBuilder(v),null,0);
                }
                return true;
            });
        } // end onBindViewHolder

        @Override
        public int getItemCount() {
            return mAlbums.size();
        } // end getItemCount

        static class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView mAlbumIcon;
            final TextView mContentView;

            ViewHolder(ItemListContentBinding binding) {
                super(binding.getRoot());
                mAlbumIcon = binding.ivAlbumIcon;
                mContentView = binding.tvAlbumTitle;
            } // end ViewHolder constructor
        } // end inner class ViewHolder
    } // end named inner class SimpleItemRecyclerViewAdapter

    public void loadPreferences(){
        if(chosenAlbums == null) {
            chosenAlbums = new ArrayList<>(); // if list is null, initialize as new array list
        }
        prefs = getActivity().getPreferences(Context.MODE_PRIVATE); // loading saved preferences
        String[] guards = prefs.getString(ALBUM_KEY, "").split(","); // splitting prefs string at "," (delimiter)
        chosenAlbums = new ArrayList<>(Arrays.asList(guards));
    } // end loadPreferences

    public void savePreferences(){
        // for each album in list, make new string (using stringbuilder) and add name to list, using "," as delimiter
        // i.e: "pic1,pic2,pic3" etc.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chosenAlbums.size(); i++){
            sb.append(chosenAlbums.get(i)).append(",");
        }
        prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ALBUM_KEY, sb.toString()); // storing new string from stringbuilder
        editor.commit(); // saving / committing to preferences
    } // end savePreferences
} // end ItemListFragment