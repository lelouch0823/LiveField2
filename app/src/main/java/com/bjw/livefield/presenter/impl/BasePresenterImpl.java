package com.bjw.livefield.presenter.impl;

import com.bjw.livefield.presenter.BasePresenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * description:
 * author: Administrator
 * createTime: 2016/10/5 0005 19:43
 */
public class BasePresenterImpl implements BasePresenter {

    public CompositeSubscription mSubscription;

    @Override
    public void addSubscription(Subscription subscription) {
        if (mSubscription == null) {
            mSubscription = new CompositeSubscription();
        }
        mSubscription.add(subscription);
    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null&&!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
