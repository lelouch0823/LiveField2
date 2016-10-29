package com.bjw.livefield.dagger.module;

import com.bjw.livefield.ui.view.implView.IZhiHuView;

import dagger.Module;
import dagger.Provides;

/**
 * description:
 * author: Administrator
 * createTime: 2016/10/29 0029 18:14
 */
@Module
public class ZhiHuFragmentModule {
    private final IZhiHuView mFragment;

    public ZhiHuFragmentModule(IZhiHuView fragment) {
        mFragment = fragment;
    }

    @Provides
    IZhiHuView provideZhiHuPresenterImpl() {
        return mFragment;
    }
}
