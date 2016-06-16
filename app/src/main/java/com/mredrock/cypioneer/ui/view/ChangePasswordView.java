package com.mredrock.cypioneer.ui.view;

/**
 * Created by PinkD on 2016/6/14.
 * View for ChangePassword
 */
public interface ChangePasswordView {
     int LOGIN_FAIL_VERIFY = 0;
    int LOGIN_FAIL_NET = 1;

    void ChangePasswordSuccess();

    void ChangePasswordFail(int failCode, Throwable throwable);

    void showProgress(final boolean show);
}
