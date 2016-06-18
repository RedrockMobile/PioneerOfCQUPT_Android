package com.mredrock.cypioneer.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.ant.liao.GifView;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.mredrock.cypioneer.BuildConfig;
import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.cfg.Config;

import java.io.IOException;
import java.util.Stack;

import static com.mredrock.cypioneer.utils.PinkUtils.getTargetGifInputStream;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AboutActivity";
    private Stack<Config.PLAY> stack = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        SwipeBackHelper.onCreate(this);
        init();
    }

    private void init() {
        findViewById(R.id.play_up).setOnClickListener(this);
        findViewById(R.id.play_down).setOnClickListener(this);
        findViewById(R.id.play_left).setOnClickListener(this);
        findViewById(R.id.play_right).setOnClickListener(this);
        findViewById(R.id.play_a).setOnClickListener(this);
        findViewById(R.id.play_b).setOnClickListener(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_up:
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "onClick: up");
                }
                if (stack.isEmpty()) {
                    stack.push(Config.PLAY.up);
                } else if (stack.peek() != Config.PLAY.up) {
                    stack.clear();
                } else {
                    stack.push(Config.PLAY.up);
                }
                break;
            case R.id.play_down:
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "onClick: down");
                }
                if (!stack.isEmpty() && stack.peek() != Config.PLAY.down && stack.peek() != Config.PLAY.up) {
                    stack.clear();
                } else {
                    stack.push(Config.PLAY.down);
                }
                break;
            case R.id.play_left:
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "onClick: left");
                }
                if (!stack.isEmpty() && stack.peek() != Config.PLAY.down && stack.peek() != Config.PLAY.right) {
                    stack.clear();
                } else {
                    stack.push(Config.PLAY.left);
                }
                break;
            case R.id.play_right:
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "onClick: right");
                }
                if (!stack.isEmpty() && stack.peek() != Config.PLAY.left) {
                    stack.clear();
                } else {
                    stack.push(Config.PLAY.right);
                }
                break;
            case R.id.play_a:
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "onClick: a");
                }
                if (!stack.isEmpty() && stack.peek() != Config.PLAY.b) {
                    stack.clear();
                } else {
                    stack.push(Config.PLAY.a);
                    if (stack.equals(Config.PLAY.getTargetStack())) {
                        Snackbar.make(v, "     ???", Snackbar.LENGTH_SHORT).show();
                        try {
                            Dialog dialog = new Dialog(this);
//                            dialog.setContentView(R.layout.gif_test);
//                            GifView gifView = (GifView) dialog.findViewById(R.id.test);
                            GifView gifView = new GifView(this);
                            gifView.setGifImage(getTargetGifInputStream(this));
                            dialog.setContentView(gifView, new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            dialog.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case R.id.play_b:
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "onClick: b");
                }
                if (!stack.isEmpty() && stack.peek() != Config.PLAY.right && stack.peek() != Config.PLAY.a) {
                    stack.clear();
                } else {
                    stack.push(Config.PLAY.b);
                }
                break;
        }
    }
}
