package com.mredrock.cypioneer.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        BBSListBean.DataBean dataBean = mDataBeen.get(position);
        holder.mName.setText(dataBean.getUser_id());
        holder.mTitle.setText(dataBean.getTitle());
        holder.mTime.setText(dataBean.getTime());
    }

    @Override
    public int getItemCount() {
        return mDataBeen.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public TextView mTime;
        public TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.tv_bbs_list_name);
            mTime = (TextView) itemView.findViewById(R.id.tv_bbs_list_time);
            mTitle = (TextView) itemView.findViewById(R.id.tv_bbs_list_title);
        }
    }
}
