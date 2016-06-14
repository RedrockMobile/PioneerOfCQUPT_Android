package com.mredrock.cypioneer.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by RD on 2016/6/14.
 * save token
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
}
