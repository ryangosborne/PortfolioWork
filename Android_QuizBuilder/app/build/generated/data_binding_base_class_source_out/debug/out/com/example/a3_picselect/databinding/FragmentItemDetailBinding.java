// Generated by view binder compiler. Do not edit!
package com.example.a3_picselect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.a3_picselect.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentItemDetailBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final AppBarLayout appBar;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout-sw600dp/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   */
  @Nullable
  public final FloatingActionButton fabToDB;

  @NonNull
  public final TextView itemDetail;

  @NonNull
  public final CoordinatorLayout itemDetailContainer;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout-sw600dp/</li>
   * </ul>
   */
  @Nullable
  public final NestedScrollView itemDetailScrollView;

  @NonNull
  public final ImageView ivAlbumImageFull;

  private FragmentItemDetailBinding(@NonNull CoordinatorLayout rootView,
      @NonNull AppBarLayout appBar, @Nullable FloatingActionButton fabToDB,
      @NonNull TextView itemDetail, @NonNull CoordinatorLayout itemDetailContainer,
      @Nullable NestedScrollView itemDetailScrollView, @NonNull ImageView ivAlbumImageFull) {
    this.rootView = rootView;
    this.appBar = appBar;
    this.fabToDB = fabToDB;
    this.itemDetail = itemDetail;
    this.itemDetailContainer = itemDetailContainer;
    this.itemDetailScrollView = itemDetailScrollView;
    this.ivAlbumImageFull = ivAlbumImageFull;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentItemDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentItemDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_item_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentItemDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.app_bar;
      AppBarLayout appBar = ViewBindings.findChildViewById(rootView, id);
      if (appBar == null) {
        break missingId;
      }

      id = R.id.fab_toDB;
      FloatingActionButton fabToDB = ViewBindings.findChildViewById(rootView, id);

      id = R.id.item_detail;
      TextView itemDetail = ViewBindings.findChildViewById(rootView, id);
      if (itemDetail == null) {
        break missingId;
      }

      CoordinatorLayout itemDetailContainer = (CoordinatorLayout) rootView;

      id = R.id.item_detail_scroll_view;
      NestedScrollView itemDetailScrollView = ViewBindings.findChildViewById(rootView, id);

      id = R.id.iv_albumImageFull;
      ImageView ivAlbumImageFull = ViewBindings.findChildViewById(rootView, id);
      if (ivAlbumImageFull == null) {
        break missingId;
      }

      return new FragmentItemDetailBinding((CoordinatorLayout) rootView, appBar, fabToDB,
          itemDetail, itemDetailContainer, itemDetailScrollView, ivAlbumImageFull);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}