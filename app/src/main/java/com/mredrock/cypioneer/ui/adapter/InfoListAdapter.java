package com.mredrock.cypioneer.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.model.bean.NewsListBean;

import java.util.ArrayList;

/**
 * Created by xushuzhan on 2016/6/18.
 */
public class InfoListAdapter extends RecyclerView.Adapter<InfoListAdapter.MyViewHolder> implements View.OnClickListener {
    public ArrayList<NewsListBean.DataBean> newsListInfo;
    public OnRecyclerViewItemClickListener mOnItemClickListener = null;
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (String) v.getTag());
        }
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    public  interface OnRecyclerViewItemClickListener{
        void onItemClick(View v,String newsId);
    }

    public InfoListAdapter(ArrayList<NewsListBean.DataBean> newsListInfo){
        this.newsListInfo=newsListInfo;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String content=newsListInfo.get(position).getContent();
        holder.title.setText(newsListInfo.get(position).getTitle());
        holder.content.setText(content);
        holder.time.setText(newsListInfo.get(position).getTime());

        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(newsListInfo.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return newsListInfo.size();
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
