package com.mredrock.cypioneer.ui.fragment.pager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.bean.NewsListBean;
import com.mredrock.cypioneer.net.HttpMethods;
import com.mredrock.cypioneer.ui.activity.NewsDetailActivity;
import com.mredrock.cypioneer.ui.adapter.InfoListAdapter;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * Created by xushuzhan on 2016/6/15.
 */
public class InfoPageFragment extends Fragment {
    private static final String TAG = "InfoPageFragment";
    private static final String PAGER_ID = "position";
    private View mView;

    private SwipeRefreshLayout swiperefreshlayout;
    public RecyclerView newsList;
    public InfoListAdapter newsListAdapter;
    private ArrayList<NewsListBean.DataBean> newsListInfo;


    private int fragment_data_id;//viewpager的fragment数据的id

    /**
     * 在这里提供一个静态的方法来实例化PageFragment
     * 在这里我们传入一个参数，用来得到pageId
     *
     * @param pageId
     * @return
     */
    public static InfoPageFragment newInstance(int pageId) {
        //利用bundle传值
        Bundle bundle = new Bundle();
        bundle.putInt(PAGER_ID, pageId);
        //实例化
        InfoPageFragment fragment = new InfoPageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        newsListInfo = new ArrayList<>();
        //取出保存在Bundle中的值
        Bundle bundle = getArguments();
        if (bundle != null) {
            fragment_data_id = bundle.getInt(PAGER_ID);
        }
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_info_viewpager_page, container, false);
        }
        getNewList(1, fragment_data_id);
        setRecyclerView();
        return mView;
    }

    public void setRecyclerView() {
        newsList = (RecyclerView) mView.findViewById(R.id.info_recyclerview);
        newsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsListAdapter = new InfoListAdapter(newsListInfo);
        newsList.setAdapter(newsListAdapter);

        newsListAdapter.setOnItemClickListener(new InfoListAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, String newsId) {
                Intent intent = new Intent(getContext(), NewsDetailActivity.class);
                intent.putExtra("news_id",newsId);
                startActivity(intent);
            }
        });

        swiperefreshlayout=(SwipeRefreshLayout)mView.findViewById(R.id.swiperefreshlayout);
        //设置刷新时动画的颜色，可以设置4个
        swiperefreshlayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swiperefreshlayout.setColorSchemeResources(
                android.R.color.holo_red_light
        );
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //取出保存在Bundle中的值
                Bundle bundle = getArguments();
                int  pageId= bundle.getInt(PAGER_ID);
                newsListInfo.clear();
                getNewList(1, pageId);
                swiperefreshlayout.setRefreshing(false);
            }
        });
    }

    public void getNewList(int page, int id) {
        Subscriber<NewsListBean.DataBean> subscriber = new Subscriber<NewsListBean.DataBean>() {
            @Override
            public void onError(Throwable e) {
                //发生了错误的回调
                Toast.makeText(getContext(), "网络好像有点问题", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(NewsListBean.DataBean dataBean) {
                //这里可以获取到我们想要的实体类
                //在Activity里new一个list，把dataBean扔进去就行了
                //举个例子:
                newsListInfo.add(dataBean);

            }

            @Override
            public void onCompleted() {
                //请求完成，换句话说，所有的newslistBean都仍到list里面去了
                //数据加载完成，启动recyclerview
                setRecyclerView();
            }
        };

        HttpMethods.getInstance().getNewsList(subscriber, page, id);
    }
}
