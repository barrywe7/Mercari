package com.barryirvine.mercari.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.barryirvine.mercari.model.Item;
import com.barryirvine.mercari.model.Status;


/**
 * View Model for {@link Item} Remember to use the {@link Bindable} annotation for all getters and to do
 * {@link #notifyPropertyChanged(int)} after a value is set in a setter.
 */

public class ItemViewModel extends BaseObservable {

    private final Item mItem;
    private final Context mContext;

    public ItemViewModel(@NonNull final Context context, @NonNull final Item item) {
        mContext = context;
        mItem = item;
    }

    @Bindable
    public String getName() {
        return mItem.getName();
    }

    @Bindable
    public String getPriceAsString() {
        return "$ " + mItem.getPrice();
    }

    @Bindable
    public String getPhoto() {
        // This is a bit of a hack because Picasso doesn't like the http request for dummy images.
        // Not sure why, might be a Nougat thing
        return mItem.getPhoto().replace("http", "https");
    }

    @Bindable
    public String getNumCommentsAsString() {
        return String.valueOf(mItem.getNumComments());
    }

    @Bindable
    public String getNumLikesAsString() {
        return String.valueOf(mItem.getNumLikes());
    }

    @Bindable
    public int getSoldOutVisibility() {
        return mItem.getStatus() == Status.SOLD_OUT ? View.VISIBLE : View.GONE;
    }

    public void onClick() {
        Toast.makeText(mContext, "You've clicked the " + getName() + " card", Toast.LENGTH_SHORT).show();
    }

    public void onCommentClick() {
        Toast.makeText(mContext, getName() + " has " + getNumCommentsAsString() + " comments", Toast.LENGTH_SHORT).show();
    }

    public void onLikeClick() {
        Toast.makeText(mContext, getName() + " has " + getNumLikesAsString() + " comments", Toast.LENGTH_SHORT).show();
    }
}
