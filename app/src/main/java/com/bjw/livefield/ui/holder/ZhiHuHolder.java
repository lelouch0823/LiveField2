package com.bjw.livefield.ui.holder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjw.livefield.MyApplication;
import com.bjw.livefield.R;
import com.bjw.livefield.bean.ZhihuDailyItem;
import com.bjw.livefield.ui.activity.ZhiHuDetailActivity;
import com.bjw.livefield.utils.DensityUtil;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * description:
 * author: Administrator
 * createTime: 2016/10/29 0029 8:49
 */
public class ZhiHuHolder extends BaseViewHolder<ZhihuDailyItem> {

    ImageView mIvPic;
    TextView mTvTitle;
    private CardView mItem;
    float width;
    int widthPx;
    int heighPx;
    public Context mContext;

    public ZhiHuHolder(ViewGroup parent, @LayoutRes int res, Context context) {
        super(parent, res);
        mIvPic = $(R.id.iv_pic);
        mTvTitle = $(R.id.tv_title);
        mItem = $(R.id.cv_item);
        mContext = context;
    }

    @Override
    public void setData(final ZhihuDailyItem data) {
        super.setData(data);
        width = mContext.getResources().
                getDimension(R.dimen.zhihu_list_item_pic_width);
        widthPx = DensityUtil.dip2px(mContext, width);
        heighPx = widthPx * 3 / 4;
        final String imgUrl = data.getImages()[0];
        final String title = data.getTitle();
        mTvTitle.setText(title);
        Glide.with(MyApplication.getContext())
                .load(imgUrl)
                .centerCrop().override(widthPx, heighPx)
                .into(mIvPic);
        mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = data.getId();
                mTvTitle.setTextColor(Color.GRAY);
                ZhiHuDetailActivity.start(mContext,id,title,imgUrl);
            }
        });
    }
}
