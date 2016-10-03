package com.bjw.livefield.presenter;

/**
 * author: Administrator
 * created on: 2016/9/23 0023 18:32
 * description:
 */
public interface IZhiHuPresenter extends BasePresenter {
    void getLastZhiHuNews();

    void getZhiHuDaily(String date);

    void getLastFromCache();
}
