package com.mredrock.cypioneer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by PinkD on 2016/6/14.
 * Shared Preference Util
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
