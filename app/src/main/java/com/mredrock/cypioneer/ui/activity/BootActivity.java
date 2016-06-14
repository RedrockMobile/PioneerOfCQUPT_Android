package com.mredrock.cypioneer.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.utils.SFUtil;

public class BootActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_boot);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SFUtil.getInstance().getToken() != null) {
                    //TODO:觉得应该验证一下token是否过期
                    startActivity(new Intent(BootActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(BootActivity.this, R.string.not_login, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BootActivity.this, LoginActivity.class));

                    //这是为了方便测试，直接跳转到MainActivity
                    //startActivity(new Intent(BootActivity.this, MainActivity.class));
                    //这个activity应该关掉吧
                    finish();
                }
            }
        }, 3000);
    }
}
