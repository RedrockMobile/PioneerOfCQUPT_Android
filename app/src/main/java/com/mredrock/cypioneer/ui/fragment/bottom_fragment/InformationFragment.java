package com.mredrock.cypioneer.ui.fragment.bottom_fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.ui.adapter.InfoFixedPageAdapter;
import com.mredrock.cypioneer.ui.fragment.InfoViewPagerFragment.InfoPageFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xushuzhan on 2016/6/14.
 */
public class InformationFragment extends Fragment {
    public TabLayout mTabLayout;
    public ViewPager mViewPager;
    private InfoFixedPageAdapter infoFixedPageAdapter;

    //设置ViewPager的4个fragmrnt位置的数组
    int ViewPagerPosion[]  = new int[]{0,1,2,3};
    //储存fragment的数组
    private List<android.support.v4.app.Fragment> mFragments;
    //tab条目中的标题
    private String[]titles={"通知公告","工作动态","基层行动","学习资料"};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        mTabLayout = (TabLayout)view.findViewById(R.id.tabLayout);
        mViewPager = (ViewPager)view.findViewById(R.id.viewPager);
        initData();
        return view;
    }

    private void initData() {
        infoFixedPageAdapter=new InfoFixedPageAdapter(getChildFragmentManager());
        infoFixedPageAdapter.setTitles(titles);//标题
        mFragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            //传入标题和位置
            mFragments.add(InfoPageFragment.newInstance(titles[i],i));
        }
        //把要显示的fragment集合传给adapter
        infoFixedPageAdapter.setFragments(mFragments);

        //设置tablayout的模式()
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //给viewPager设置适配器
        mViewPager.setAdapter(infoFixedPageAdapter);
        //TabLayout绑定ViewPager
        mTabLayout.setupWithViewPager(mViewPager);

    }

}
