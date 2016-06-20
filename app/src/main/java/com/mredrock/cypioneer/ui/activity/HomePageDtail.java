package com.mredrock.cypioneer.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.mredrock.cypioneer.R;

public class HomePageDtail extends AppCompatActivity {
    TextView tv;
    TextView sub_tv;
    WebView webView;
    String mUrl;
    String mTitle;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_dtail);

        //获取URL和title
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        mTitle = intent.getStringExtra("title");
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
