package com.bjw.livefield.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.bjw.livefield.R;
import com.bjw.livefield.presenter.impl.ZhiHuDetailPresenterImpl;
import com.bjw.livefield.ui.fragment.ZhiHuDetailFragment;

public class ZhiHuDetailActivity extends SingleFragmentActivity {

    public static final String DETAIL_TITLE = "title";
    public static final String DETAIL_ID = "id";
    public static final String DETAIL_IMG_URL = "url";
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_zhi_hu_detail;
    }

    @Override
    protected Fragment createFragment() {
        ZhiHuDetailFragment fragment = ZhiHuDetailFragment.newInstance();
        ZhiHuDetailPresenterImpl presenter = new ZhiHuDetailPresenterImpl(fragment);
        return fragment;
    }

    public static void start(Context context, String id, String title, String url) {
        Intent starter = new Intent(context, ZhiHuDetailActivity.class);
        starter.putExtra(DETAIL_ID,id);
        starter.putExtra(DETAIL_TITLE,title);
        starter.putExtra(DETAIL_IMG_URL,url);
        context.startActivity(starter);
    }
}
