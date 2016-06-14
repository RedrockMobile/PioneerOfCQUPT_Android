package com.mredrock.cypioneer;

import android.app.Application;

import com.mredrock.cypioneer.utils.SFUtil;

/**
 * Created by RD on 2016/6/14.
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SFUtil.getInstance().init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
