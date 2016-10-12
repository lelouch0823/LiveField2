package com.bjw.livefield.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjw.livefield.MyApplication;
import com.bjw.livefield.R;
import com.bjw.livefield.bean.ZhihuDailyItem;
import com.bjw.livefield.ui.activity.ZhiHuDetailActivity;
import com.bjw.livefield.utils.DensityUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author: Administrator
 * createTime: 2016/10/3 0003 19:55
 */
public class ZhiHuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ZhihuDailyItem> mStories = new ArrayList<>();
    float width;
    int widthPx;
    int heighPx;
    public Context mContext;

    public ZhiHuAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ZhiHuHolder zhiHuHolder = new ZhiHuHolder(LayoutInflater.from(
                MyApplication.getContext()).inflate(R.layout.item_zhihu_list, parent, false));
        return zhiHuHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindDate((ZhiHuHolder) holder, position);
    }

    private void bindDate(final ZhiHuHolder holder, int position) {
        width = MyApplication.mContext.getResources().getDimension(R.dimen.zhihu_list_item_pic_width);
        widthPx = DensityUtil.dip2px(MyApplication.mContext, width);
        heighPx = widthPx * 3 / 4;

        final ZhihuDailyItem zhihuDailyItem = mStories.get(position);
        final String imgUrl = zhihuDailyItem.getImages()[0];
        final String title = zhihuDailyItem.getTitle();
        holder.mTextView.setText(title);
        Glide.with(MyApplication.getContext())
                .load(imgUrl)
                .centerCrop().override(widthPx, heighPx)
                .into(holder.mImageView);
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = zhihuDailyItem.getId();
                holder.mTextView.setTextColor(Color.GRAY);
                ZhiHuDetailActivity.start(mContext,id,title,imgUrl);
            }
        });
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
        ImageView mImageView;
        LinearLayout mLayout;

        ZhiHuHolder(View itemView) {
            super(itemView);
            mLayout = (LinearLayout) itemView.findViewById(R.id.ll_zhihu_item);
            mTextView = (TextView) itemView.findViewById(R.id.tv_title);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_pic);
        }
    }
}
