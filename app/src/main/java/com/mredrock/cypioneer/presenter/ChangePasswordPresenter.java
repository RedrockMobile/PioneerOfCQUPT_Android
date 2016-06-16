package com.mredrock.cypioneer.presenter;

import android.util.Log;

import com.mredrock.cypioneer.model.bean.UserBean;
import com.mredrock.cypioneer.model.net.ChangePasswordModule;
import com.mredrock.cypioneer.ui.view.ChangePasswordView;

import rx.functions.Action1;

/**
 * Created by PinkD on 2016/6/16.
 * LoginPresenter
 */
public class ChangePasswordPresenter {
    private static final String TAG = "ChangePassPresenter--->";
    private ChangePasswordView changePasswordView;
    private Action1<String> success;
    private Action1<Throwable> fail;
    private ChangePasswordModule changePasswordModule;

    public ChangePasswordPresenter(final ChangePasswordView changePasswordView) {
        this.changePasswordView = changePasswordView;
        changePasswordModule = new ChangePasswordModule();
        success = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, s);
                changePasswordView.ChangePasswordSuccess();
            }
        };
        fail = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                changePasswordView.ChangePasswordFail(-1, throwable);
                changePasswordView.showProgress(false);
            }
        };
    }

    public void login(String oldPassword, String newPassword) {
        changePasswordView.showProgress(true);
        changePasswordModule.changePassword(oldPassword, newPassword, success, fail);
    }
}
