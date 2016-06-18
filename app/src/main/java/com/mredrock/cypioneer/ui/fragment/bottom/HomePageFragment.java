package com.mredrock.cypioneer.ui.fragment.bottom;


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
import com.mredrock.cypioneer.ui.adapter.HomePagePictureAdapter;
import java.util.ArrayList;

import rx.Subscriber;


/**
 * Created by xushuzhan on 2016/6/14.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "HomePageFragment";
    private ArrayList<PhotoBean.DataBean> carouselFigures;//轮播图实体类数组

    //朱大的库太方便了
    RollPagerView mRollPagerView;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_page, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        carouselFigures=new ArrayList<>();
        initView();
        getPhotos();
    }

    private void initView() {
        partyRules_c= (CardView) view.findViewById(R.id.button_party_rules_c);
        seriesSpeech_c= (CardView) view.findViewById(R.id.button_series_speech_c);
        qualifiedMembers_c= (CardView) view.findViewById(R.id.button_qualified_members_c);
        internetActvitis_c= (CardView) view.findViewById(R.id.button_internet_activity_c);
        advancedModel_c= (CardView) view.findViewById(R.id.button_advanced_model_c);
        classicalMovie_c= (CardView) view.findViewById(R.id.button_classic_movie_c);
        partyRules_c.setOnClickListener(this);
        seriesSpeech_c.setOnClickListener(this);
        qualifiedMembers_c.setOnClickListener(this);
        internetActvitis_c.setOnClickListener(this);
        advancedModel_c.setOnClickListener(this);
        classicalMovie_c.setOnClickListener(this);

        partyRules= (Button) view.findViewById(R.id.button_party_rules);
        seriesSpeech= (Button) view.findViewById(R.id.button_series_speech);
        qualifiedMembers= (Button) view.findViewById(R.id.button_qualified_members);
        internetActvitis= (Button) view.findViewById(R.id.button_internet_activity);
        advancedModel= (Button) view.findViewById(R.id.button_advanced_model);
        classicalMovie= (Button) view.findViewById(R.id.button_classic_movie);
        partyRules.setOnClickListener(this);
        seriesSpeech.setOnClickListener(this);
        qualifiedMembers.setOnClickListener(this);
        internetActvitis.setOnClickListener(this);
        advancedModel.setOnClickListener(this);
        classicalMovie.setOnClickListener(this);

    }

    private void setRollPagerView() {
        mRollPagerView= (RollPagerView) view.findViewById(R.id.Carousel_figure);
        mRollPagerView.setAdapter(new HomePagePictureAdapter(HomePageFragment.this,carouselFigures));
    }

    public void getPhotos() {
        Subscriber<PhotoBean.DataBean> subscriber = new Subscriber<PhotoBean.DataBean>() {
            @Override
            public void onError(Throwable e) {
                //发生了错误的回调
                Toast.makeText(getContext(), "出错啦/(ㄒoㄒ)/~~", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onError: "+e.getMessage());
            }

            @Override
            public void onNext(PhotoBean.DataBean dataBean) {
                //这里可以获取到我们想要的实体类
                //在Activity里new一个list，把dataBean扔进去就行了
                //举个例子:

                carouselFigures.add(dataBean);
                Log.d(TAG, "onNext: "+carouselFigures.get(0).getImgurl());
            }

            @Override
            public void onCompleted() {
                //请求完成，换句话说，所有的newslistBean都仍到list里面去了
                //然后就可以执行把arrayList给recyclerView的adapter之类的操作了
                Log.d(TAG, "onCompleted: 请求完成啦！！！");
                Log.d(TAG, "onCompleted: "+carouselFigures.get(0).getLink());
                Log.d(TAG, "onCompleted: "+carouselFigures.get(0).getImgurl());
                Log.d(TAG, "onCompleted: "+carouselFigures.get(0).getTitle());
                setRollPagerView();//启动轮播图的方法
            }
        };

        HttpMethods.getInstance().getPhotos(subscriber);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_party_rules_c:
            case R.id.button_party_rules :
                Toast.makeText(getContext(), "党规党章", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_series_speech_c:
            case R.id.button_series_speech:
                Toast.makeText(getContext(), "系列讲话", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_qualified_members_c:
            case R.id.button_qualified_members:
                Toast.makeText(getContext(), "合格党员", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_internet_activity_c:
            case R.id.button_internet_activity:
                Toast.makeText(getContext(), "网络活动", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_advanced_model_c:
            case R.id.button_advanced_model:
                Toast.makeText(getContext(), "先进典型", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_classic_movie_c:
            case R.id.button_classic_movie:
                Toast.makeText(getContext(), "经典影像", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;


        }
    }
}
