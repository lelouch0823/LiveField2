package com.bjw.livefield.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bjw.livefield.MyApplication;
import com.bjw.livefield.R;
import com.bjw.livefield.bean.ZhihuDailyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author: Administrator
 * createTime: 2016/10/3 0003 19:55
 */
public class ZhiHuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ZhihuDailyItem> mStories = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ZhiHuHolder zhiHuHolder = new ZhiHuHolder(LayoutInflater.from(
                MyApplication.getContext()).inflate(R.layout.item_zhihu_list,parent,false));
        return zhiHuHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindDate((ZhiHuHolder) holder,position);
    }

    private void bindDate(ZhiHuHolder holder, int position) {
        ZhihuDailyItem zhihuDailyItem = mStories.get(position);
        holder.mTextView.setText(zhihuDailyItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return mStories.size();
    }


    public void clearDate() {
        if (mStories != null) {
            mStories.clear();
        }
        notifyDataSetChanged();
    }

    public void addItem(List<ZhihuDailyItem> stories) {
        mStories.addAll(stories);
        notifyDataSetChanged();
    }

    private class ZhiHuHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        ZhiHuHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
