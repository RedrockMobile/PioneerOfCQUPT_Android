package com.mredrock.cypioneer.ui.activity;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.model.bean.NewsDetailBean;
import com.mredrock.cypioneer.model.bean.NewsListBean;
import com.mredrock.cypioneer.net.HttpMethods;
import com.mredrock.cypioneer.utils.FileUtil;

import java.io.File;

import rx.Subscriber;

public class NewsDetailActivity extends AppCompatActivity {
    TextView tvToolbarTitle;
    TextView tvToolbarDownload;
    TextView tvToolbarSubTitle;
    TextView tvNewsTitle;
    Button btBack;
    String newsId;
    String newsDtail;
    String url;
    WebView webView;
    String fileName;
    FileUtil fileUtil;
    final String fileDir = "/PoineerOfCQUPT/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initView();
        fileUtil = new FileUtil(NewsDetailActivity.this);
        Intent intent = getIntent();
        newsId = intent.getStringExtra("news_id");


        getNews();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        tvNewsTitle = (TextView) findViewById(R.id.tv_news_detail_title);
        tvToolbarTitle = (TextView) findViewById(R.id.tv_title_toolbar);
        tvToolbarSubTitle = (TextView) findViewById(R.id.tv_sub_title_toolbar);
        tvToolbarDownload = (TextView) findViewById(R.id.tv_news_detail_download);
        btBack = (Button) findViewById(R.id.bt_toolbar_back);
        btBack.setBackground(getDrawable(R.drawable.back));
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvToolbarSubTitle.setText("");
        tvToolbarTitle.setText("资讯详情");
        tvToolbarTitle.setTextSize(19);
        webView = (WebView) findViewById(R.id.web_view_news_dtail);
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
                newsDtail = newsDetailBean.getData().getContent();
                tvNewsTitle.setText(newsDetailBean.getData().getTitle());
                webView.loadDataWithBaseURL(null, newsDetailBean.getData().getTime() + newsDtail, "text/html", "utf-8", null);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebChromeClient(new WebChromeClient());

                url = newsDetailBean.getData().getFile().get(0).getAddress();
                Log.d("1234567", "onNext: " + url);
                if (newsDetailBean.getData().getFile().size() != 0) {
                    /**注册下载完成广播**/
                    registerReceiver(downloadCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                    tvToolbarDownload.setText("下载附件");
                    tvToolbarDownload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showDialog();
                        }
                    });


                }


            }
        };
        HttpMethods.getInstance().getNewsDetail(subscriber, Integer.parseInt(newsId));
    }

    /**
     * 下载附件
     **/

    public void downloaDattachment() {
        Uri uri = Uri.parse(url);
        //获取文件的名字
        fileName = url.substring(url.lastIndexOf("/") + 1);
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        // 设置允许使用的网络类型，这里是移动网络和wifi都可以
        request.setAllowedNetworkTypes(request.NETWORK_MOBILE | request.NETWORK_WIFI);
        //设置是否允许漫游
        request.setAllowedOverRoaming(false);
        //设置文件类型
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        //文件的mime类型
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
        Log.d("1234567", "downloaDattachment: " + mimeString);
        request.setMimeType(mimeString);
        //在通知栏中显示
        request.setNotificationVisibility(request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle(fileName);
        request.setVisibleInDownloadsUi(true);
        //sdcard目录下的download文件夹
        request.setDestinationInExternalPublicDir(fileDir, fileName);
        // 将下载请求放入队列
        downloadManager.enqueue(request);
    }

    private BroadcastReceiver downloadCompleteReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(NewsDetailActivity.this);
            dialog.setTitle("提示：");
            dialog.setMessage("下载完成，是否打开？");
            dialog.setCancelable(false);
            dialog.setPositiveButton("是", new DialogInterface.
                    OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    /**下载完成后显示**/
                    try {
                        fileUtil.openFile(new File(fileDir + fileName));
                    } catch (Exception e) {
                        Log.d("NewsDetailActivity", "onReceive: " + e.getMessage() + ">>>" + fileDir + fileName);

                    }
                }
            });
            dialog.setNegativeButton("否", new DialogInterface.
                    OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();

        }
    };

    public void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(NewsDetailActivity.this);
        dialog.setTitle("提示！");
        dialog.setMessage("请确认是否下载");
        dialog.setCancelable(false);
        dialog.setPositiveButton("是", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                downloaDattachment();
                Toast.makeText(NewsDetailActivity.this, "开始下载...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton("否", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(NewsDetailActivity.this, "取消下载...", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (downloadCompleteReceiver != null) {
                unregisterReceiver(downloadCompleteReceiver);
            }
        } catch (Exception e) {
            Log.d("NewsDetailActivity", "onDestroy: " + e.getMessage());
        }

    }


}
