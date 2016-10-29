package com.bjw.livefield.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.bjw.livefield.R;
import com.bjw.livefield.bean.ZhiHuDaily;
import com.bjw.livefield.presenter.BasePresenter;
import com.bjw.livefield.presenter.impl.ZhiHuPresenterImpl;
import com.bjw.livefield.ui.adapter.ZhiHuListAdapter;
import com.bjw.livefield.ui.view.implView.IZhiHuView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ZhiHuFragment extends BaseFragment implements IZhiHuView {

    @BindView(R.id.rv_zhihu_dailies)
    EasyRecyclerView mRecyclerView;
    @BindView(R.id.pg_loading)
    ProgressBar mPbLoading;
    @BindView(R.id.imgBtn_retry)
    ImageButton mImgBtnRetry;
    @BindView(R.id.srl_zhihu_refresh)
    SwipeRefreshLayout mRefreshLayout;


    public ZhiHuListAdapter mAdapter;
    public ZhiHuPresenterImpl mPresenter;
    public String mDate;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * 获取实例
     *
     * @return the zhi hu fragment
     */
    public static ZhiHuFragment newInstance() {
        ZhiHuFragment fragment = new ZhiHuFragment();
        return fragment;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.unsubscribe();
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_zhi_hu;
    }

    @Override
    public void updateList(ZhiHuDaily daily) {
        mDate = daily.getDate();
        if (mAdapter != null) {
            if (mRefreshLayout.isRefreshing()) {
                mAdapter.clear();
                mAdapter.addAll(daily.getStories());
            } else {
                mAdapter.addAll(daily.getStories());
            }
        }
    }

    @Override
    public void onListLoadMore(ZhiHuDaily daily) {
        if (mAdapter != null) {
            mDate = daily.getDate();
            mAdapter.addAll(daily.getStories());
        }
    }

    @Override
    public void showProgressDialog() {
        if (!mRefreshLayout.isRefreshing()) {
            mPbLoading.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void hidenProgressDialog() {
        if (!mRefreshLayout.isRefreshing()) {
            mImgBtnRetry.setVisibility(View.GONE);
            mPbLoading.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showError(String error) {
        mRecyclerView.setVisibility(View.GONE);
        mImgBtnRetry.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initialView() {

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.setRefreshing(true);
                mPresenter.getLastZhiHuNews();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setMore(R.layout.item_loading, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //mPresenter.getZhiHuDaily(mDate);
                mPresenter.getZhiHuDailyOrCache(mDate);
            }
        });
        loadData();
    }

    private void loadData() {
        if (mAdapter.getItemCount() > 0) {
            mImgBtnRetry.setVisibility(View.GONE);
            mAdapter.clear();
        }
        mPresenter.getLastZhiHuNews();
    }

    @Override
    protected void initialDate() {
        mAdapter = new ZhiHuListAdapter(mContext);
        if (mPresenter == null) {
            mPresenter = new ZhiHuPresenterImpl(mContext, this);
        }
    }

    @Override
    public void setPresenter(@NonNull BasePresenter presenter) {
        mPresenter = (ZhiHuPresenterImpl) presenter;
    }

    @OnClick(R.id.imgBtn_retry)
    public void onClick() {
        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


}