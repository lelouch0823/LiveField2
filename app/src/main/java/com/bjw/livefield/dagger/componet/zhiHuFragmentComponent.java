package com.bjw.livefield.dagger.componet;

import com.bjw.livefield.dagger.module.ZhiHuFragmentModule;
import com.bjw.livefield.ui.fragment.ZhiHuFragment;

import dagger.Component;

/**
 * description:
 * author: Administrator
 * createTime: 2016/10/29 0029 18:22
 */
@Component(modules = ZhiHuFragmentModule.class)
public interface zhiHuFragmentComponent {
    void in(ZhiHuFragment fragment);
}
