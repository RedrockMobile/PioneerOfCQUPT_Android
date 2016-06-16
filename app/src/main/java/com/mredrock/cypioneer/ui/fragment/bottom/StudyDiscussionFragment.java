package com.mredrock.cypioneer.ui.fragment.bottom;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.cfg.Api;
import com.mredrock.cypioneer.model.bean.BBSListBean;
import com.mredrock.cypioneer.net.IBBSNet;
import com.mredrock.cypioneer.ui.adapter.BBSListAdapter;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by xushuzhan on 2016/6/14.
 */
public class StudyDiscussionFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLayoutManager;
    private FloatingActionButton mFab;
    private BBSListAdapter mBBSListAdapter;
    private ArrayList<BBSListBean.DataBean> mBBSListBeen;
    private ProgressDialog mProgressDialog;

    public static final String TAG = "StudyDiscussionFragment";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_study_dicussion, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        getList(0);
    }

    private void getList(final int page) {
        showProgressDialog();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        IBBSNet ibbsNet = retrofit.create(IBBSNet.class);

        ibbsNet.getBBSList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<BBSListBean, Observable<BBSListBean.DataBean>>() {
                    @Override
                    public Observable<BBSListBean.DataBean> call(BBSListBean bbsListBean) {
                        return Observable.from(bbsListBean.getData());
                    }
                })
                .subscribe(new Subscriber<BBSListBean.DataBean>() {
                    @Override
                    public void onCompleted() {
                        cancelProgressDialog();
                        if (page == 0) {
                            mRecyclerView
                                    .setAdapter(mBBSListAdapter = new BBSListAdapter(mBBSListBeen));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                    }

                    @Override
                    public void onNext(BBSListBean.DataBean dataBean) {
                        mBBSListBeen.add(dataBean);
                    }
                });
    }

    private void initView(View v) {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_bbs_list);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("正在加载");
        mBBSListBeen = new ArrayList<>();
        mRecyclerView.setLayoutManager(mLayoutManager=new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
    }

    private void showProgressDialog() {
        mProgressDialog.show();
    }

    private void cancelProgressDialog() {
        mProgressDialog.cancel();
    }
}
