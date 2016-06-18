package com.mredrock.cypioneer;

import android.app.Application;

import com.mredrock.cypioneer.cfg.Config;
import com.mredrock.cypioneer.model.bean.UserBean;
import com.mredrock.cypioneer.utils.SFUtil;

/**
 * Created by PinkD on 2016/6/14.
 * Init tools
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SFUtil.getInstance().init(this);
        Config.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
