package com.bjw.livefield.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.bjw.livefield.R;
import com.bjw.livefield.bean.ZhihuDailyItem;
import com.bjw.livefield.ui.holder.ZhiHuHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * description:
 * author: Administrator
 * createTime: 2016/10/26 0026 20:01
 */
public class ZhiHuListAdapter extends RecyclerArrayAdapter<ZhihuDailyItem> {

    public Context mContext;

    public ZhiHuListAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ZhiHuHolder(parent, R.layout.item_zhi_hu_list,mContext);
    }
}
