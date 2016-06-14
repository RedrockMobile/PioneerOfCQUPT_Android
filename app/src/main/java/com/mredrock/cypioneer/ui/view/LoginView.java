package com.mredrock.cypioneer.ui.view;

/**
 * Created by RD on 2016/6/14.
 */
public interface LoginView {
     int LOGIN_FAIL_VERIFY = 0;
    int LOGIN_FAIL_NET = 1;

    void LoginSuccess();

    void Logging();//show progress

    void LoginFail(int failCode);
}
