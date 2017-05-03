package com.barryirvine.mercari.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FeedResponse {

    private String result;

    @SerializedName("data")
    private ArrayList<Item> mItems;

    public ArrayList<Item> getItems() {
        return mItems;
    }
}
