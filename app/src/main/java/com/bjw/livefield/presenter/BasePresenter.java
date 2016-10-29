package com.bjw.livefield.presenter;

import rx.Subscription;

/**
 * author: Administrator
 * created on: 2016/9/23 0023 17:21
 * description:Presenter的基类
 */
public interface BasePresenter {

    void addSubscription(Subscription subscription);

    void unsubscribe();
}
