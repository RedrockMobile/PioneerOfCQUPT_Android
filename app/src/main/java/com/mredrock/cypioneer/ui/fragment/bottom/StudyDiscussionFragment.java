package com.mredrock.cypioneer.ui.fragment.bottom;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.model.bean.NewsListBean;
import com.mredrock.cypioneer.model.bean.PhotoBean;
import com.mredrock.cypioneer.net.HttpMethods;

import java.util.ArrayList;

import rx.Subscriber;


/**
 * Created by xushuzhan on 2016/6/14.
 */
public class StudyDiscussionFragment extends Fragment {

    public static final String TAG = "StudyDiscussionFragment";

    private ArrayList<PhotoBean.DataBean> mDataBeen;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_study_dicussion, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDataBeen = new ArrayList<>();
        getNewList();
    }

    public void getNewList() {
        Subscriber<PhotoBean.DataBean> subscriber = new Subscriber<PhotoBean.DataBean>() {
            @Override
            public void onError(Throwable e) {
                //发生了错误的回调
            }

            @Override
            public void onNext(PhotoBean.DataBean dataBean) {
                //这里可以获取到我们想要的实体类
                //在Activity里new一个list，把dataBean扔进去就行了
                //举个例子:
                mDataBeen.add(dataBean);

            }

            @Override
            public void onCompleted() {
                //请求完成，换句话说，所有的newslistBean都仍到list里面去了
                //然后就可以执行把arrayList给recyclerView的adapter之类的操作了
                Toast.makeText(getContext(), "我测试一下是否能进行UI操作", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onCompleted: 请求完成啦！！！");
                Log.d(TAG, "onCompleted: "+mDataBeen.get(0).getTitle());
            }
        };

        HttpMethods.getInstance().getPhotos(subscriber);
    }
}
