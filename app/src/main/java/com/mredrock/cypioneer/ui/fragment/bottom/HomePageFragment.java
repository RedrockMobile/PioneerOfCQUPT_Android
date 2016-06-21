package com.mredrock.cypioneer.ui.fragment.bottom;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.model.bean.PhotoBean;
import com.mredrock.cypioneer.net.HttpMethods;
import com.mredrock.cypioneer.ui.activity.HomePageDtailActivity;
import com.mredrock.cypioneer.ui.adapter.HomePagePictureAdapter;
import com.mredrock.cypioneer.utils.SFUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import rx.Subscriber;


/**
 * Created by xushuzhan on 2016/6/14.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "HomePageFragment";
    private ArrayList<PhotoBean.DataBean> carouselFigures;//轮播图实体类数组

    //朱大的库太方便了
    RollPagerView mRollPagerView;
    HomePagePictureAdapter homePagePictureAdapter;
    View view;
    CardView partyRules_c;
    CardView seriesSpeech_c;
    CardView qualifiedMembers_c;
    CardView internetActvitis_c;
    CardView advancedModel_c;
    CardView classicalMovie_c;
    Button partyRules;
    Button seriesSpeech;
    Button qualifiedMembers;
    Button internetActvitis;
    Button advancedModel;
    Button classicalMovie;

    Button bt1;
    Button bt2;
    Button bt3;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_page, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        carouselFigures = new ArrayList<>();
        setRollPagerView();
        initView();
        getPhotos();
    }

    private void initView() {
        partyRules_c = (CardView) view.findViewById(R.id.button_party_rules_c);
        seriesSpeech_c = (CardView) view.findViewById(R.id.button_series_speech_c);
        qualifiedMembers_c = (CardView) view.findViewById(R.id.button_qualified_members_c);
        internetActvitis_c = (CardView) view.findViewById(R.id.button_internet_activity_c);
        advancedModel_c = (CardView) view.findViewById(R.id.button_advanced_model_c);
        classicalMovie_c = (CardView) view.findViewById(R.id.button_classic_movie_c);
        partyRules_c.setOnClickListener(this);
        seriesSpeech_c.setOnClickListener(this);
        qualifiedMembers_c.setOnClickListener(this);
        internetActvitis_c.setOnClickListener(this);
        advancedModel_c.setOnClickListener(this);
        classicalMovie_c.setOnClickListener(this);

        partyRules = (Button) view.findViewById(R.id.button_party_rules);
        seriesSpeech = (Button) view.findViewById(R.id.button_series_speech);
        qualifiedMembers = (Button) view.findViewById(R.id.button_qualified_members);
        internetActvitis = (Button) view.findViewById(R.id.button_internet_activity);
        advancedModel = (Button) view.findViewById(R.id.button_advanced_model);
        classicalMovie = (Button) view.findViewById(R.id.button_classic_movie);
        partyRules.setOnClickListener(this);
        seriesSpeech.setOnClickListener(this);
        qualifiedMembers.setOnClickListener(this);
        internetActvitis.setOnClickListener(this);
        advancedModel.setOnClickListener(this);
        classicalMovie.setOnClickListener(this);

        bt1 = (Button) view.findViewById(R.id.button_1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://lxyz.12371.cn/"));
                startActivity(intent);
            }
        });

        bt2 = (Button) view.findViewById(R.id.button_2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.12371.gov.cn/Category_1130/Index.aspx"));
                startActivity(intent);
            }
        });

        bt3= (Button) view.findViewById(R.id.button_3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.cqedu.cn/Category_308/Index.aspx"));
                startActivity(intent);
            }
        });

    }

    private void setRollPagerView() {
        if (mRollPagerView == null) {//初始化，从SharedPreference读取地址，从磁盘读取缓存
            mRollPagerView = (RollPagerView) view.findViewById(R.id.Carousel_figure);
            Set<String> tmp = SFUtil.getInstance().getUrls();
            if (tmp != null) {
                for (String s : tmp) {
                    carouselFigures.add(new PhotoBean.DataBean(s));
                    Log.d(TAG, s);
                }
            }
            homePagePictureAdapter = new HomePagePictureAdapter(HomePageFragment.this, carouselFigures);
            mRollPagerView.setAdapter(homePagePictureAdapter);
        }
        homePagePictureAdapter.notifyDataSetChanged();
    }

    public void getPhotos() {
        carouselFigures.clear();
        homePagePictureAdapter.notifyDataSetChanged();
        Subscriber<PhotoBean.DataBean> subscriber = new Subscriber<PhotoBean.DataBean>() {
            @Override
            public void onError(Throwable e) {
                //发生了错误的回调
                Toast.makeText(getContext(), "出错啦/(ㄒoㄒ)/~~", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(PhotoBean.DataBean dataBean) {
                //这里可以获取到我们想要的实体类
                //在Activity里new一个list，把dataBean扔进去就行了
                //举个例子:
                carouselFigures.add(dataBean);
                Log.d(TAG, "onNext: " + carouselFigures.get(0).getImgurl());
            }

            @Override
            public void onCompleted() {
                //请求完成，换句话说，所有的newslistBean都仍到list里面去了
                //然后就可以执行把arrayList给recyclerView的adapter之类的操作了
                Log.d(TAG, "onCompleted: 请求完成啦！！！");
                Set<String> urls = new HashSet<>();
                for (PhotoBean.DataBean tmp : carouselFigures) {
                    urls.add(tmp.getImgurl());
                }
                SFUtil.getInstance().saveUrls(urls);
                setRollPagerView();//启动轮播图
            }
        };

        HttpMethods.getInstance().getPhotos(subscriber);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_party_rules_c:
            case R.id.button_party_rules :
                Intent intent = new Intent(getContext(), HomePageDtailActivity.class);
                intent.putExtra("url","http://lxyz.12371.cn/dzdg/");
                intent.putExtra("title","学党规党章");
                startActivity(intent);

                break;
            case R.id.button_series_speech_c:
            case R.id.button_series_speech:
                Intent intent1 = new Intent(getContext(), HomePageDtailActivity.class);
                intent1.putExtra("url","http://www.12371.cn/special/xjpzyls/xxxjpzyls/");
                intent1.putExtra("title","学系列讲话");
                startActivity(intent1);
                break;
            case R.id.button_qualified_members_c:
            case R.id.button_qualified_members:
                Intent intent2 = new Intent(getContext(), HomePageDtailActivity.class);
                intent2.putExtra("url","http://lxyz.12371.cn/xjdx/");
                intent2.putExtra("title","做合格党员");
                startActivity(intent2);
                break;
            case R.id.button_internet_activity_c:
            case R.id.button_internet_activity:
                Intent intent3 = new Intent(getContext(), HomePageDtailActivity.class);
                intent3.putExtra("url","https://redrock.cqupt.edu.cn/lxyz_activity/");
                intent3.putExtra("title","网络活动");
                startActivity(intent3);
                break;
            case R.id.button_advanced_model_c:
            case R.id.button_advanced_model:
                Intent intent4 = new Intent(getContext(), HomePageDtailActivity.class);
                intent4.putExtra("url","http://lxyz.12371.cn/xjdx/");
                intent4.putExtra("title","先进典型");
                startActivity(intent4);
                break;
            case R.id.button_classic_movie_c:
            case R.id.button_classic_movie:
                Intent intent5 = new Intent(getContext(), HomePageDtailActivity.class);
                intent5.putExtra("url","http://lxyz.12371.cn/jdyx/");
                intent5.putExtra("title","经典影像");
                startActivity(intent5);
                break;
            default:
                break;


        }
    }
}
