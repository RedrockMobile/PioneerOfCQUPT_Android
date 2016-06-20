package com.mredrock.cypioneer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.mredrock.cypioneer.cfg.Config;
import com.mredrock.cypioneer.model.bean.UserBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by PinkD on 2016/6/14.
 * save and read token
 */
public class SFUtil {
    private Context context;
    private SharedPreferences sharedPreferences;

    public static SFUtil getInstance() {
        return thisHolder.sfUtil;
    }

    public void init(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
    }

    private static class thisHolder {
        static SFUtil sfUtil = new SFUtil();
    }

    public String getToken() {
        if (thisHolder.sfUtil.context == null) {
            throw new NullPointerException("init first");
        }
        return sharedPreferences.getString("token", null);
    }

    public void saveToken(String token) {
        if (thisHolder.sfUtil.context == null) {
            throw new NullPointerException("init first");
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public String getUsername() {
        if (thisHolder.sfUtil.context == null) {
            throw new NullPointerException("init first");
        }
        return sharedPreferences.getString("username", null);
    }

    public void saveUsername(String username) {
        if (thisHolder.sfUtil.context == null) {
            throw new NullPointerException("init first");
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.apply();
    }
    public Set<String> getUrls() {
        if (thisHolder.sfUtil.context == null) {
            throw new NullPointerException("init first");
        }
        return sharedPreferences.getStringSet("urls", null);
    }

    public void saveUrls(Set<String> urls) {
        if (thisHolder.sfUtil.context == null) {
            throw new NullPointerException("init first");
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("urls", urls);
        editor.apply();
    }
}
