package com.mredrock.cypioneer;

import android.app.Application;
import android.content.Context;

import com.mredrock.cypioneer.cfg.Config;
import com.mredrock.cypioneer.model.bean.UserBean;
import com.mredrock.cypioneer.utils.SFUtil;

/**
 * Created by PinkD on 2016/6/14.
 * Init tools
 */
public class App extends Application{
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        SFUtil.getInstance().init(this);
        Config.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static Context getAppContext(){
        return mContext;
    }
}
