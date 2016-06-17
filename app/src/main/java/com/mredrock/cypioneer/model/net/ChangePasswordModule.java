package com.mredrock.cypioneer.model.net;

import com.mredrock.cypioneer.cfg.Api;
import com.mredrock.cypioneer.cfg.Config;
import com.mredrock.cypioneer.net.ChangePasswordNet;
import com.mredrock.cypioneer.utils.SFUtil;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by PinkD on 2016/6/16.
 * Login module
 */
public class ChangePasswordModule {
    public void changePassword(String oldPassword, String newPassword, Action1<String> success, Action1<Throwable> fail) {
        if (Config.user == null) {
            fail.call(new NullPointerException("Not Login"));
            return;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ChangePasswordNet changePasswordNet = retrofit.create(ChangePasswordNet.class);
        changePasswordNet.changePassword(Config.user.getUser_id(), oldPassword, newPassword, SFUtil.getInstance().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success, fail);
    }
}
