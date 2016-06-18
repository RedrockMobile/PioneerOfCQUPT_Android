package com.mredrock.cypioneer.ui.fragment.pager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.ui.adapter.InfoListAdapter;

/**
 * Created by xushuzhan on 2016/6/15.
 */
public class InfoPageFragment extends Fragment {
    private static final String KEY_TITLE = "title";
    private static final String KEY_POSITION = "position";
    private View mView;

    public RecyclerView newsList;
    public InfoListAdapter newsListAdapter;

    private TextView mTextView;//测试时用的文本框
    private String title;//设置给测试文本的标题
    private int f_position;//viewpager的fragment的位置

    /**
     * 在这里提供一个静态的方法来实例化PageFragment
     * 在这里我们传入一个参数，用来得到title，和position
     * 然后我们拿到这个title设置给内容（测试时用）
     *
     * @param title
     * @param position
     * @return
     */
    public static InfoPageFragment newInstance(String title, int position) {
        //利用bundle传值
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, title);
        bundle.putInt(KEY_POSITION, position);
        //实例化
        InfoPageFragment fragment = new InfoPageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //取出保存在Bundle中的值
        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString(KEY_TITLE);
            f_position = bundle.getInt(KEY_POSITION);
        }
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_info_viewpager_page, container, false);
        }
        initView();
        return mView;
    }

    private void initView() {
        newsList = (RecyclerView) mView.findViewById(R.id.info_recyclerview);
        newsList.setLayoutManager(new LinearLayoutManager(getContext()));
        newsListAdapter = new InfoListAdapter();
        newsList.setAdapter(newsListAdapter);
    }
}
