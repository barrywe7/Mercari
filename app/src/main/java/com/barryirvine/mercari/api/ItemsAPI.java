package com.barryirvine.mercari.api;


import com.barryirvine.mercari.model.FeedResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ItemsAPI {

    String BASE_URL = "https://dummy.com/";

    @GET("{category}")
    Observable<FeedResponse> getItems(@Path("category") final String category);

}
