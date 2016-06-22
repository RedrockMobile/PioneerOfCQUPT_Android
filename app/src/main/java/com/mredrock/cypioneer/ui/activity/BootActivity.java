package com.mredrock.cypioneer.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.cfg.Config;
import com.mredrock.cypioneer.model.bean.PhotoBean;
import com.mredrock.cypioneer.model.bean.UserBean;
import com.mredrock.cypioneer.utils.SFUtil;

import java.util.ArrayList;

public class BootActivity extends Activity {
    public static final String TAG = "BootActivity123";
    private ArrayList<PhotoBean.DataBean> carouselFigures;//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_boot);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SFUtil.getInstance().getToken() != null) {
                    //TODO:觉得应该验证一下token是否过期
                    Config.user = new UserBean(SFUtil.getInstance().getUsername());
                    startActivity(new Intent(BootActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(BootActivity.this, R.string.not_login, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BootActivity.this, LoginActivity.class));
                }
                BootActivity.this.finish();
            }
        }, 1500);
    }

}