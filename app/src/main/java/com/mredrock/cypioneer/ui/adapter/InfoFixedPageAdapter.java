package com.mredrock.cypioneer.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * 资讯界面的ViewPager的Adapter
 * Created by xushuzhan on 2016/6/15.
 */
public class InfoFixedPageAdapter extends FragmentStatePagerAdapter {
    private String[] titles;
    private List<Fragment> mFragments;

    /**
     * 接收传入的Tab选项卡的标题
     *
     * @param titles
     */
    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    /**
     * 接收传入的需要显示的Fragment
     *
     * @param fragments
     */
    public void setFragments(List<Fragment> fragments) {
        mFragments = fragments;
    }

    /**
     * 这个是在继承FragmentStatePagerAdapter时写入的构造方法
     *
     * @param fm
     */
    public InfoFixedPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    /**
     * 返回要显示的Fragment的个数
     *
     * @return
     */
    @Override
    public int getCount() {
        return mFragments.size();
    }

    /**
     * 返回Tab选项卡里的Title
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}