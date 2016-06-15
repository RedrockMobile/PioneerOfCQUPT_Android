package com.mredrock.cypioneer.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.mredrock.cypioneer.R;

/**
 * 首页轮播图的adapter
 * Created by xushuzhan on 2016/6/16.
 */
public class HomePagePictureAdapter extends StaticPagerAdapter {
    private int[] imgs = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,

    };
    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setImageResource(imgs[position]);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }
}
