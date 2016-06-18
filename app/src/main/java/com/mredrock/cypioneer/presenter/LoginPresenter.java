package com.mredrock.cypioneer.presenter;

import com.mredrock.cypioneer.cfg.Config;
import com.mredrock.cypioneer.model.bean.UserBean;
import com.mredrock.cypioneer.model.net.LoginModule;
import com.mredrock.cypioneer.ui.view.LoginView;
import com.mredrock.cypioneer.utils.SFUtil;

import rx.functions.Action1;

/**
 * Created by PinkD on 2016/6/16.
 * LoginPresenter
 */
public class LoginPresenter {
    private LoginView loginView;
    private Action1<UserBean> success;
    private Action1<Throwable> fail;
    private LoginModule loginModule;

    public LoginPresenter(final LoginView loginView) {
        this.loginView = loginView;
        loginModule = new LoginModule();
        success = new Action1<UserBean>() {
            @Override
            public void call(UserBean userBean) {
                Config.user = userBean;
                SFUtil.getInstance().saveUsername(userBean.getName());
                loginView.LoginSuccess();
            }
        };
        fail = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                loginView.LoginFail(-1, throwable);
                loginView.showProgress(false);
            }
        };
    }

    public void login(String username, String password) {
        loginView.showProgress(true);
        loginModule.login(username, password, success, fail);
    }
}
