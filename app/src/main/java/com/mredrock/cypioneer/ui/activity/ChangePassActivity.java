package com.mredrock.cypioneer.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.presenter.ChangePasswordPresenter;
import com.mredrock.cypioneer.ui.view.ChangePasswordView;
import com.mredrock.cypioneer.utils.PinkUtils;


public class ChangePassActivity extends AppCompatActivity implements ChangePasswordView {
    private static final String TAG = "ChangePassActivity--->";

    private ChangePasswordPresenter changePasswordPresenter;
    private EditText oldPasswordView;
    private EditText newPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        SwipeBackHelper.onCreate(this);
        changePasswordPresenter = new ChangePasswordPresenter(this);
        oldPasswordView = (EditText) findViewById(R.id.change_password_old);
        newPasswordView = (EditText) findViewById(R.id.change_password_new);
        newPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        findViewById(R.id.change_password_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.change_password_form);
        mProgressView = findViewById(R.id.change_password_progress);
    }


    private void attemptLogin() {

        // Reset errors.
        oldPasswordView.setError(null);
        newPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String oldPassword = oldPasswordView.getText().toString();
        String newPassword = newPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid newPassword, if the user entered one.
        if (!TextUtils.isEmpty(newPassword) && !isPasswordValid(newPassword)) {
            newPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = newPasswordView;
            cancel = true;
        }

        // Check for oldPassword.
        if (TextUtils.isEmpty(oldPassword)) {
            oldPasswordView.setError(getString(R.string.error_field_required));
            focusView = oldPasswordView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            changePasswordPresenter.login(oldPassword, newPassword);
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void ChangePasswordSuccess() {
        showProgress(false);
        Toast.makeText(ChangePassActivity.this, R.string.change_password_success, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ChangePassActivity.this, MainActivity.class));
        this.finish();
    }

    @Override
    public void ChangePasswordFail(int failCode, Throwable throwable) {
        PinkUtils.LogD(TAG, "login--->" + throwable.toString());
        throwable.printStackTrace();
        newPasswordView.setError(getString(R.string.login_fail));
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        SwipeBackHelper.onDestroy(this);
        super.onDestroy();
    }
}

