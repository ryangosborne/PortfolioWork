package com.example.a3_picselect.placeholder;

import com.example.a3_picselect.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.res.Resources;


public class AlbumContent {

    public static List<AlbumItem> ITEMS = new ArrayList<>();
//    public static List<String> album_names_array = new ArrayList<>();
    public static final Map<String, AlbumItem> ITEM_MAP = new HashMap<>();
    private static final int COUNT = 5; // this is what determines how many rows to list / show

    // getting album names array from strings.xml location
    static String[] albumNames = {"22 A Million", "After Hours", "Brighter Wounds", "Evermore", "In Rainbows"};
//    static String[] albumNames = res.getStringArray(R.array.album_names_array);

    // hard-coding image locations within array
    static Integer[] imagePath = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5};
    static Integer[] iconPath = {R.drawable.pic1icon, R.drawable.pic2icon, R.drawable.pic3icon, R.drawable.pic4icon, R.drawable.pic5icon};

    static {
        for (int i = 0; i < COUNT; i++) {
            addItem(createAlbumItem(i));
        }
    } // loop to create COUNT number of album items

    private static void addItem(AlbumItem item) {
        ITEMS.add(item); // adding new item to list
        ITEM_MAP.put(item.name, item); // uses hashmap to store item.id (#), and item
    } // end addItem

    private static AlbumItem createAlbumItem(int position) {
//        AlbumItem.ITEMS.get(position).name = album_names[i];
        // this calls the constructor for AlbumItem and returns a new AlbumItem
        // icon file path, full size file patch, image name
        return new AlbumItem(albumNames[position], imagePath[position], iconPath[position]);
    } // end createAlbumItem

    public static class AlbumItem { // properties
        public String name; // album name
        public final int image; // path to full size image
        public final int icon; // path to icon image

        public AlbumItem(String name, int image, int icon) { // constructor
            this.name = name;
            this.image = image;
            this.icon = icon;
        }
    } // end named inner class PlaceHolderItem
} // end class