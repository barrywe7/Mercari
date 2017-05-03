package com.barryirvine.mercari.model;

public class Item {

    private String id;
    private String name;
    private Status status;
    private int numLikes;
    private int numComments;
    private int price;
    private String photo;

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public int getNumComments() {
        return numComments;
    }

    public int getPrice() {
        return price;
    }

    public String getPhoto() {
        return photo;
    }
}
