package com.mredrock.cypioneer.ui.adapter;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.mredrock.cypioneer.model.bean.PhotoBean;
import java.util.ArrayList;

/**
 * 首页轮播图的adapter
 * Created by xushuzhan on 2016/6/16.
 */
public class HomePagePictureAdapter extends StaticPagerAdapter {
    Fragment fragment;//待传入的的fragment
    ArrayList<PhotoBean.DataBean> carouselFigures;//轮播图的图片实体类
    public  HomePagePictureAdapter(Fragment fragment, ArrayList<PhotoBean.DataBean> carouselFigures){
        this.fragment = fragment;
        this.carouselFigures=carouselFigures;
    }
    @Override
    public View getView(ViewGroup container, int position) {
        Log.d("HomePagePictureAdapter", "getView: position"+carouselFigures.get(position).getImgurl());
        ImageView view = new ImageView(container.getContext());
        Glide.with(fragment)
                .load(carouselFigures.get(position).getImgurl())
                .into(view);

        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getCount() {
        return carouselFigures.size();
    }
}
