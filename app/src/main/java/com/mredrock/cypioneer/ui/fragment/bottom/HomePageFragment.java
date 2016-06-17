package com.mredrock.cypioneer.ui.fragment.bottom;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.DynamicPagerAdapter;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.model.bean.CarouselFigure;
import com.mredrock.cypioneer.ui.adapter.HomePagePictureAdapter;
import com.mredrock.cypioneer.utils.AnalyzeAPI;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by xushuzhan on 2016/6/14.
 */
public class HomePageFragment extends Fragment {
    public static final int SUCCESS = 0;

    ArrayList<CarouselFigure> carouselFigures = new ArrayList<>();//轮播图类的数据结构
    public static ArrayList<Bitmap> pictures = new ArrayList<>();//轮播图图片的数组
    //朱大的库太方便了
    RollPagerView mRollPagerView;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        mRollPagerView= (RollPagerView) view.findViewById(R.id.Carousel_figure);
        mRollPagerView.setAdapter(new HomePagePictureAdapter(pictures));
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AnalyzeAPI analyzeAPI = new AnalyzeAPI("http://202.202.43.42/lxyz/index.php?m=Home&c=index&a=mobilepic", new AnalyzeAPI.AnalyzeAPIListener() {
            @Override
            public void onSuccess() throws MalformedURLException {

            }

            @Override
            public void onFailed() {

            }
        },carouselFigures);


    }

}
