package com.mredrock.cypioneer.net;

import com.mredrock.cypioneer.model.bean.BBSDetailBean;
import com.mredrock.cypioneer.model.bean.BBSListBean;
import com.mredrock.cypioneer.model.bean.BBSStatusBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by simonla on 2016/6/16.
 * Have a good day.
 */
public interface IBBSNet {

    @FormUrlEncoded
    @POST("index.php?m=Home&c=Chat&a=index")
    Observable<BBSListBean> getBBSList(
            @Field("page") int page
    );

    @FormUrlEncoded
    @POST("index.php?m=Home&c=Chat&a=mobilechatdetail")
    Observable<BBSDetailBean> getBBSDetail(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("index.php?m=Home&c=Chat&a=mobilechat")
    Observable<BBSStatusBean> sendBBS(
            @Field("user_id") int userId,
            @Field("token") String token,
            @Field("title") String title,
            @Field("content") String content
    );

    @FormUrlEncoded
    @POST("index.php?m=Home&c=Chat&a=mobilechat")
    Observable<BBSStatusBean> sendComment(
            @Field("user_id") int userId,
            @Field("token") String token,
            @Field("content") String content,
            @Field("id") int id
    );
}
