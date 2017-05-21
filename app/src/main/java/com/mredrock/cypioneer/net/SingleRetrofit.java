package com.mredrock.cypioneer.net;

import com.mredrock.cypioneer.config.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PinkD on 2017/5/10.
 * SingleRetrofit
 */

public class SingleRetrofit {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
