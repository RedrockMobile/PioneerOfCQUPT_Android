package com.mredrock.cypioneer.net;

import com.mredrock.cypioneer.cfg.Api;
import com.mredrock.cypioneer.model.bean.NewsDetailBean;
import com.mredrock.cypioneer.model.bean.NewsListBean;
import com.mredrock.cypioneer.model.bean.PhotoBean;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by simonla on 2016/6/17.
 * Have a good day.
 */
public class HttpMethods {
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit mRetrofit;
    private INet mINet;

    private HttpMethods() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Api.BASE_URL)
                .build();
        mINet = mRetrofit.create(INet.class);
    }

    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 资讯列表
     * @param page 起始数字为1
     * @param id 2->通知公告 3->工作动态 4->基层行动 5->学习资料
     */
    public void getNewsList(Subscriber<NewsListBean.DataBean> subscriber, int page, int id) {
        mINet.getNewsList(page, id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<NewsListBean, Observable<NewsListBean.DataBean>>() {
                    @Override
                    public Observable<NewsListBean.DataBean> call(NewsListBean newsListBean) {
                        return Observable.from(newsListBean.getData());
                    }
                })
                .subscribe(subscriber);
    }

    /**
     * 首页轮播图，最多三张
     */
    public void getPhotos(Subscriber<PhotoBean.DataBean> subscriber) {
        mINet.getPhotos()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<PhotoBean, Observable<PhotoBean.DataBean>>() {
                    @Override
                    public Observable<PhotoBean.DataBean> call(PhotoBean photoBean) {
                        return Observable.from(photoBean.getData());
                    }
                })
                .subscribe(subscriber);
    }

    /**
     *  资讯正文
     * @param id 从列表中拿，不区分类型
     */
    public void getNewsDetail(Subscriber<NewsDetailBean> subscriber, int id) {
        mINet.getNewsDetail(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
