package com.barryirvine.mercari.model;

import com.google.gson.annotations.SerializedName;

public enum Status {
    @SerializedName("on_sale")
    ON_SALE,
    @SerializedName("sold_out")
    SOLD_OUT;
}

