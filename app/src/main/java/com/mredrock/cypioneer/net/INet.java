package com.mredrock.cypioneer.net;

import com.mredrock.cypioneer.model.bean.NewsListBean;
import com.mredrock.cypioneer.model.bean.NewsDetailBean;
import com.mredrock.cypioneer.model.bean.PhotoBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by simonla on 2016/6/16.
 * Have a good day.
 */
public interface INet {

    /**
     * @param page 起始1
     * @param id 2->通知公告 3->工作动态 4->基层行动 5->学习资料
     * @return 资讯列表
     */
    @FormUrlEncoded
    @POST("index.php?m=Home&c=index&a=mobilearticlelist")
    Observable<NewsListBean> getNewsList(
            @Field("page") int page,
            @Field("id") int id
    );

    /**
     * @return 首页轮播图（最多3张）
     */
    @GET("index.php?m=Home&c=index&a=mobilepic")
    Observable<PhotoBean> getPhotos();

    /**
     * @param id 从列表中拿，不区分类型
     * @return 资讯正文
     */
    @FormUrlEncoded
    @POST("index.php?m=Home&c=Article&a=mobilearticle ")
    Observable<NewsDetailBean> getNewsDetail(
            @Field("id") int id
    );
}
