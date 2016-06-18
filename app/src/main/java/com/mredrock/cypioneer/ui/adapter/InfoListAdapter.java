package com.mredrock.cypioneer.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mredrock.cypioneer.R;

import java.util.ArrayList;

/**
 * Created by xushuzhan on 2016/6/18.
 */
public class InfoListAdapter extends RecyclerView.Adapter<InfoListAdapter.MyViewHolder> {
    //public InfoListAdapter(ArrayList<>){};

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        TextView time;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_info_title);
            content= (TextView) itemView.findViewById(R.id.item_info_content);
            time = (TextView) itemView.findViewById(R.id.item_info_time);
        }
    }
}
