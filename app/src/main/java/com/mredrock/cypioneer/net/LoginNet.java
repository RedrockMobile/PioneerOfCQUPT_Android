package com.mredrock.cypioneer.net;

import com.mredrock.cypioneer.bean.CommonWrapper;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by PinkD on 2016/6/16.
 */
public interface LoginNet {
    @FormUrlEncoded
    @POST("index.php?m=Home&c=Login&a=login")
    Observable<CommonWrapper> login(
            @Field("idcard") String idcard,
            @Field("password") String password
    );
}
