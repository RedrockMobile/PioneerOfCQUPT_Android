package com.mredrock.cypioneer.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mredrock.cypioneer.App;
import com.mredrock.cypioneer.R;
import com.mredrock.cypioneer.model.bean.NewsListBean;

import java.util.ArrayList;

/**
 * Created by xushuzhan on 2016/6/18.
 */
public class InfoListAdapter extends RecyclerView.Adapter<InfoListAdapter.MyViewHolder> {

    public ArrayList<NewsListBean.DataBean> newsListInfo;

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

        try{
            String content=newsListInfo.get(position).getContent().replaceAll("&.{4,5};|[\r\n\t]","").replaceAll("^\\s+","").replaceAll("&.{1,5}[a-z]","");
            Log.d("onBindViewHolder", "onBindViewHolder: +"+content);
        holder.title.setText(newsListInfo.get(position).getTitle());
        holder.content.setText(content);
        holder.time.setText(newsListInfo.get(position).getTime());
        }catch (Exception e){
            Log.d("InfoListAdapter", "onBindViewHolder: "+e.getMessage());
            Toast.makeText(App.mContext, "抱歉，出了点小问题/(ㄒoㄒ)/~~", Toast.LENGTH_SHORT).show();
        }
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
