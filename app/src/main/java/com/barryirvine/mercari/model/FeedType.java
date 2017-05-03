package com.barryirvine.mercari.model;

import android.support.annotation.StringRes;

import com.barryirvine.mercari.R;

public enum FeedType {

    MEN("men", R.string.men),
    ALL("all", R.string.all),
    WOMEN("women", R.string.women);

    @StringRes
    private final int mDescription;
    private final String mPath;


    FeedType(final String path, @StringRes final int description) {
        mDescription = description;
        mPath = path;
    }

    public String getPathSegment() {
        return mPath;
    }

    @StringRes
    public int getDescription() {
        return mDescription;
    }

}

