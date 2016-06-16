package com.mredrock.cypioneer.net;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by PinkD on 2016/6/16.
 */
public interface ChangePasswordNet {
    @FormUrlEncoded
    @POST("index.php?m=Home&c=Login&a=changepassword")
    Observable<String> changePassword(
            @Field("user_id") String user_id,
            @Field("oldpassword") String oldpassword,
            @Field("newpassword") String newpassword,
            @Field("token") String token
    );
}
