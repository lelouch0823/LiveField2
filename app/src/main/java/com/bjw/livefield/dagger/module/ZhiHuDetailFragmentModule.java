package com.bjw.livefield.dagger.module;

import com.bjw.livefield.ui.view.implView.IZhiHuDetailView;

import dagger.Module;
import dagger.Provides;

/**
 * description:
 * author: Administrator
 * createTime: 2016/10/29 0029 20:38
 */
@Module
public class ZhiHuDetailFragmentModule {
    public IZhiHuDetailView mView;

    public ZhiHuDetailFragmentModule(IZhiHuDetailView view) {
        mView = view;
    }

    @Provides
    IZhiHuDetailView provideIZhiHuDetailView() {
        return mView;
    }
}
