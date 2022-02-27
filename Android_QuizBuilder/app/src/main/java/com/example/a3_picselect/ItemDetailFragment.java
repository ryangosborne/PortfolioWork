package com.example.a3_picselect;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *  Hey David! The two import statements below are really strange and someimtes go red.
 *  A solution I've found is to simply comment them, then uncomment them and it seems to be okay
 */

import com.example.a3_picselect.placeholder.AlbumContent;
import com.example.a3_picselect.databinding.FragmentItemDetailBinding;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListFragment}
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private AlbumContent.AlbumItem mItem;
    private ImageView mAlbumImage;
    private TextView mAlbumName;

    private final View.OnDragListener dragListener = (v, event) -> {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            ClipData.Item clipDataItem = event.getClipData().getItemAt(0);
            mItem = AlbumContent.ITEM_MAP.get(clipDataItem.getText().toString());
            updateContent(); // call method to populate fragment
        }
        return true;
    };
    private FragmentItemDetailBinding binding;

    public ItemDetailFragment() {} // constructor (default)

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = AlbumContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    } // end onCreate

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentItemDetailBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        mAlbumImage = rootView.findViewById(R.id.iv_albumImageFull);
        mAlbumName = binding.itemDetail;
        updateContent();
        rootView.setOnDragListener(dragListener);
        return rootView;
    } // end onCreateView

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    } // end onDestroyView

    private void updateContent() {
        // populating detail view with corresponding properties and calling animation
        if (mItem != null) {
            mAlbumName.setText(mItem.name);
            if (mAlbumImage != null) {
                mAlbumImage.setImageResource(mItem.image);
            }
            animation();
            mItem = null;
        }
    } // end updateContent

    private void animation(){
        // animation method
        Animation myAnimation = AnimationUtils.loadAnimation(this.getContext(), R.anim.rotate);
        mAlbumImage.startAnimation(myAnimation);
    } // end animation
} // end ItemDetailFragment