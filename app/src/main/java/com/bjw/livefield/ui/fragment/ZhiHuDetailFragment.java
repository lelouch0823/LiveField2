package com.bjw.livefield.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bjw.livefield.R;
import com.bjw.livefield.bean.ZhihuStory;
import com.bjw.livefield.presenter.BasePresenter;
import com.bjw.livefield.presenter.impl.ZhiHuDetailPresenterImpl;
import com.bjw.livefield.ui.activity.ZhiHuDetailActivity;
import com.bjw.livefield.ui.view.implView.IZhiHuDetailView;
import com.bjw.livefield.utils.WebUtil;
import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description:
 * author: Administrator
 * createTime: 2016/10/11 0011 18:21
 */
public class ZhiHuDetailFragment extends BaseFragment implements IZhiHuDetailView {

    @BindView(R.id.iv_zhihu_detail_img)
    ImageView mImageView;
    @BindView(R.id.tb_zhuhu_detail)
    Toolbar mToolbar;
    @BindView(R.id.Coll_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.app_bar_zhihu_detail)
    AppBarLayout mAppBarLayout;
    public ZhiHuDetailPresenterImpl mPresenter;
    public String mUrl;
    public String mTitle;
    public String mId;
    @BindView(R.id.wv_zhihu_content)
    WebView mWvZhiHuContent;
    @BindView(R.id.nest)
    NestedScrollView nest;

    @Override
    protected void initialView() {
        mToolbar.setTitleTextColor(Color.BLACK);
        mToolbar.setTitle(mTitle);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        WebSettings settings = mWvZhiHuContent.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCachePath(mContext.getCacheDir().getAbsolutePath() + "/webViewCache");
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWvZhiHuContent.setWebChromeClient(new WebChromeClient());
        mPresenter.getZhiHuDetail(mId);
    }

    @Override
    protected void initialDate() {
        Intent intent = mContext.getIntent();
        if (mPresenter == null) {
            mPresenter = new ZhiHuDetailPresenterImpl(this);
        }
        if (intent != null) {
            mUrl = intent.getStringExtra(ZhiHuDetailActivity.DETAIL_IMG_URL);
            mTitle = intent.getStringExtra(ZhiHuDetailActivity.DETAIL_TITLE);
            mId = intent.getStringExtra(ZhiHuDetailActivity.DETAIL_ID);
        }
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        mPresenter = (ZhiHuDetailPresenterImpl) presenter;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_zhi_hu_detail;
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hidenProgressDialog() {

    }

    @Override
    public void showError(String error) {

    }

    public static ZhiHuDetailFragment newInstance() {
        ZhiHuDetailFragment fragment = new ZhiHuDetailFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void loadZhiHuDetail(ZhihuStory zhihuStory) {
        Logger.d(zhihuStory.toString());
        String image = zhihuStory.getImage();
        Glide.with(this).load(image)
                .fitCenter()
                .into(mImageView);
        String url = zhihuStory.getShareUrl();
        String body = zhihuStory.getBody();
        String[] css = zhihuStory.getCss();
        String data = WebUtil.buildHtmlWithCss(body, css, false);
        mWvZhiHuContent.loadDataWithBaseURL(WebUtil.BASE_URL, data,
                WebUtil.MIME_TYPE, WebUtil.ENCODING, WebUtil.FAIL_URL);
    }
}
