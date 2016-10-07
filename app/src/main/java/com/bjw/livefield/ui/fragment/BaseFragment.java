package com.bjw.livefield.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bjw.livefield.presenter.IZhiHuPresenter;
import com.bjw.livefield.ui.view.BaseView;

import butterknife.ButterKnife;

/**
 * description:
 * author: Administrator
 * createTime: 2016/10/3 0003 16:33
 */
public abstract class BaseFragment extends Fragment implements BaseView{

    public Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialDate();
        initialView();
    }

    protected abstract void initialView();

    protected abstract void initialDate();

    public abstract void setPresenter(IZhiHuPresenter presenter);


    /**
     * 返回需要加载的布局的ID.
     *
     * @return the layout res id
     */
    @LayoutRes
    protected abstract int getLayoutResId();

    @Override
    public void onDetach() {
        super.onDetach();
        if (mContext != null) {
            mContext = null;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
