package com.mredrock.cypioneer.model.net;

import com.google.gson.Gson;
import com.mredrock.cypioneer.cfg.Api;
import com.mredrock.cypioneer.model.bean.UserBean;
import com.mredrock.cypioneer.net.LoginNet;
import com.mredrock.cypioneer.utils.SFUtil;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by PinkD on 2016/6/16.
 * Login module
 */
public class LoginModule {
    public void login(String username, String password, Action1<UserBean> success, Action1<Throwable> fail) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        LoginNet loginNet = retrofit.create(LoginNet.class);
        loginNet.login(username, password)
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, UserBean>() {
                    @Override
                    public UserBean call(String s) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject = new JSONObject(s).getJSONObject("data");
                            SFUtil.getInstance().saveToken(jsonObject.getString("token"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return new Gson().fromJson(jsonObject.toString(), UserBean.class);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success, fail);
    }
}
