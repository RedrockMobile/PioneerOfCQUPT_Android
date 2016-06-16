package com.mredrock.cypioneer.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.model.bean.BBSListBean;

import java.util.ArrayList;

/**
 * Created by simonla on 2016/6/16.
 * Have a good day.
 */
public class BBSListAdapter extends RecyclerView.Adapter<BBSListAdapter.ViewHolder> {

    private ArrayList<BBSListBean.DataBean> mDataBeen;

    public BBSListAdapter(ArrayList<BBSListBean.DataBean> dataBeen) {
        mDataBeen = dataBeen;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bbs_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDataBeen.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
