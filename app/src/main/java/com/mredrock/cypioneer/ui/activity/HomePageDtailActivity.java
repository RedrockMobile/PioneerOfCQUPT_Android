package com.mredrock.cypioneer.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.mredrock.cypioneer.R;

public class HomePageDtailActivity extends AppCompatActivity{
    TextView tv;
    TextView sub_tv;
    WebView webView;
    String mUrl;
    String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_dtail);
        initView();
        //获取URL和title
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        mTitle = intent.getStringExtra("title");

    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv_title_toolbar);
        tv.setText(mTitle);
        tv.setTextSize(18);

        sub_tv= (TextView) findViewById(R.id.tv_sub_title_toolbar);
        sub_tv.setText("");

        webView = (WebView) findViewById(R.id.web_view_homepage);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String
                    url) {
                view.loadUrl(url); // 根据传入的参数再去加载新的网页
                return true; // 表示当前WebView可以处理打开新网页的请求，不用借助系统浏览器
            }
        });
        webView.loadUrl(mUrl);



    }

}
