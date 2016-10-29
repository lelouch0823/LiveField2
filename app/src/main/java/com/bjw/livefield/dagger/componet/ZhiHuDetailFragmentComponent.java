package com.bjw.livefield.dagger.componet;

import com.bjw.livefield.dagger.module.ZhiHuDetailFragmentModule;
import com.bjw.livefield.ui.fragment.ZhiHuDetailFragment;

import dagger.Component;

/**
 * description:
 * author: Administrator
 * createTime: 2016/10/29 0029 20:42
 */
@Component(modules = ZhiHuDetailFragmentModule.class)
public interface ZhiHuDetailFragmentComponent {
    void in(ZhiHuDetailFragment fragment);
}
