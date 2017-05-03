package com.barryirvine.mercari.ui;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.barryirvine.mercari.R;
import com.squareup.picasso.Picasso;

public class DataBindingUtils {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(@NonNull final ImageView view, @NonNull final String imageUrl) {

       Picasso.with(view.getContext())
                .load(imageUrl)
                .resizeDimen(R.dimen.photo_image_size, R.dimen.photo_image_size)
                .placeholder(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_image_grey_24dp))
                .centerCrop()
                .into(view);
    }
}
