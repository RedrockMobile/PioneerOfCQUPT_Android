package com.mredrock.cypioneer.model.net;

import android.util.Log;

import com.google.gson.Gson;
import com.mredrock.cypioneer.model.bean.CommonWrapper;
import com.mredrock.cypioneer.model.bean.UserBean;
import com.mredrock.cypioneer.net.LoginNet;
import com.mredrock.cypioneer.net.SingleRetrofit;
import com.mredrock.cypioneer.utils.SFUtil;

import org.json.JSONException;
import org.json.JSONObject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by PinkD on 2016/6/16.
 * Login module
 */
public class LoginModule {
    private static final String TAG = "LoginModule";

    public void login(String username, String password, Action1<UserBean> success, Action1<Throwable> fail) {
        LoginNet loginNet = SingleRetrofit.getRetrofit().create(LoginNet.class);
        loginNet.login(username, password)
                .subscribeOn(Schedulers.io())
                .map(new Func1<CommonWrapper, UserBean>() {
                    @Override
                    public UserBean call(CommonWrapper commonWrapper) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject = new JSONObject(commonWrapper.getData());
                            SFUtil.getInstance().saveToken(jsonObject.getString("token"));
                        } catch (JSONException e) {
                            Log.d(TAG, "call: " + commonWrapper);
                            e.printStackTrace();
                        }
                        return new Gson().fromJson(jsonObject.toString(), UserBean.class);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success, fail);
    }
}
