package com.mredrock.cypioneer.ui.fragment.bottom;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.DynamicPagerAdapter;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.ui.adapter.HomePagePictureAdapter;


/**
 * Created by xushuzhan on 2016/6/14.
 */
public class HomePageFragment extends Fragment {
    //朱大的库太方便了
    RollPagerView mRollPagerView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        mRollPagerView= (RollPagerView) view.findViewById(R.id.Carousel_figure);
        mRollPagerView.setAdapter(new HomePagePictureAdapter());
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
