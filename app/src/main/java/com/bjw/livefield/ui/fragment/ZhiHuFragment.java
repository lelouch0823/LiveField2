package com.bjw.livefield.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.bjw.livefield.R;
import com.bjw.livefield.bean.ZhiHuDaily;
import com.bjw.livefield.presenter.IZhiHuPresenter;
import com.bjw.livefield.ui.adapter.ZhiHuAdapter;
import com.bjw.livefield.ui.view.implView.IZhiHuView;

import butterknife.BindView;
import butterknife.OnClick;


public class ZhiHuFragment extends BaseFragment implements IZhiHuView {

    @BindView(R.id.rv_zhihu_dailies)
    RecyclerView mRecyclerView;
    @BindView(R.id.pg_loading)
    ProgressBar mPbLoading;
    @BindView(R.id.imgBtn_retry)
    ImageButton mImgBtnRetry;

    private OnFragmentInteractionListener mListener;
    public IZhiHuPresenter mPresenter;
    public ZhiHuAdapter mAdapter;


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
        mListener = null;
    }

    @Override
    public void setPresenter(IZhiHuPresenter presenter) {
        mPresenter = presenter;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_zhi_hu;
    }

    @Override
    public void updateList(ZhiHuDaily daily) {
        if (mAdapter != null) {
            mAdapter.addItem(daily.getStories());
        }
    }

    @Override
    public void showProgressDialog() {
        mPbLoading.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hidenProgressDialog() {
        mPbLoading.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String error) {
        mImgBtnRetry.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initialView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        loadData();
    }

    private void loadData() {
        if (mAdapter.getItemCount() > 0) {
            mAdapter.clearDate();
        }
        mPresenter.getLastZhiHuNews();
    }

    @Override
    protected void initialDate() {
        mAdapter = new ZhiHuAdapter();
        //mPresenter = new ZhiHuPresenterImpl(mContext, this);
    }

    @OnClick(R.id.imgBtn_retry)
    public void onClick() {
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
