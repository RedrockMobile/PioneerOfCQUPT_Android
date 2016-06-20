package com.mredrock.cypioneer.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.model.bean.NewsDetailBean;
import com.mredrock.cypioneer.model.bean.NewsListBean;
import com.mredrock.cypioneer.net.HttpMethods;

import rx.Subscriber;

public class NewsDetailActivity extends AppCompatActivity {
    TextView tv_title;
    TextView tv_sub_title;
    int newsId;
    String newsDtail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        tv_title = (TextView) findViewById(R.id.tv_title_toolbar);
        tv_sub_title = (TextView) findViewById(R.id.tv_sub_title_toolbar);

        tv_sub_title.setText("");

        Intent intent  = getIntent();
        newsId = intent.getIntExtra("news_id",0);
    }

}
