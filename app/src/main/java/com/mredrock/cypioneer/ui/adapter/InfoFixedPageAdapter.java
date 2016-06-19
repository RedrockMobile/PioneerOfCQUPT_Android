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
     * 设置标题
     *
     * @param titles
     */
    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public void setFragments(List<Fragment> fragments) {
        mFragments = fragments;
    }

    /**
     * 这个是在继承FragmentStatePagerAdapter时强制写入的
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
     * 返回可以用的view的个数
     *
     * @return
     */
    @Override
    public int getCount() {
        return mFragments.size();
    }

    /**
     * 这个同destroyItem（）相反，是对于给定的位置创建视图，适配器往container中添加
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = null;
        fragment = (Fragment) super.instantiateItem(container, position);
        return fragment;
    }

    /**
     * 移除给定位置的数据，适配器负责从container（容器）中取出，但是这个必须保证是在finishUpdate（view）
     * 返回的时间内完成
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);

    }

    /**
     * 得到滑动页面的Title
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }


}