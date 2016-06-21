package com.mredrock.cypioneer.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.model.bean.NewsDetailBean;
import com.mredrock.cypioneer.model.bean.NewsListBean;
import com.mredrock.cypioneer.net.HttpMethods;

import rx.Subscriber;

public class NewsDetailActivity extends AppCompatActivity {
    TextView tvTitle;
    TextView tvSubTitle;
    String newsId;
    String newsDtail;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        tvTitle = (TextView) findViewById(R.id.tv_title_toolbar);
        tvSubTitle = (TextView) findViewById(R.id.tv_sub_title_toolbar);

        tvSubTitle.setText("");

        Intent intent  = getIntent();
        newsId = intent.getStringExtra("news_id");

        webView = (WebView) findViewById(R.id.web_view_news_dtail);

        getNews();
    }

    private void getNews() {
        Subscriber<NewsDetailBean> subscriber = new Subscriber<NewsDetailBean>() {


            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(NewsDetailActivity.this, "网络好像不太顺畅...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(NewsDetailBean newsDetailBean) {
                newsDtail=newsDetailBean.getData().getContent();
                tvTitle.setText(newsDetailBean.getData().getTitle());
                webView.loadDataWithBaseURL(null, newsDetailBean.getData().getTime()+newsDtail, "text/html", "utf-8", null);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebChromeClient(new WebChromeClient());

            }
        };
        HttpMethods.getInstance().getNewsDetail(subscriber,Integer.parseInt(newsId));
    }


}
