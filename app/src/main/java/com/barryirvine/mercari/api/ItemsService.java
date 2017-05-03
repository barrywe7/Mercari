package com.barryirvine.mercari.api;

import android.content.Context;

import com.barryirvine.mercari.util.MockResponseInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemsService {

    private static volatile ItemsAPI sItemsAPI;

    public static ItemsAPI get(final Context applicationContext) {
        if (sItemsAPI == null) {
            synchronized (ItemsService.class) {
                if (sItemsAPI == null) {
                    final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new MockResponseInterceptor(applicationContext)).build();
                    sItemsAPI = new Retrofit.Builder()
                            .client(client)
                            .baseUrl(ItemsAPI.BASE_URL)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
                            .build()
                            .create(ItemsAPI.class);


                }
            }
        }
        return sItemsAPI;
    }
}
