package com.bjw.livefield.presenter.impl;

import com.bjw.livefield.bean.ZhihuStory;
import com.bjw.livefield.model.impl.ZhiHuDailyModelImpl;
import com.bjw.livefield.presenter.IZhiHuDetailPresenter;
import com.bjw.livefield.ui.view.implView.IZhiHuDetailView;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * description:
 * author: Administrator
 * createTime: 2016/10/10 0010 20:24
 */
public class ZhiHuDetailPresenterImpl extends BasePresenterImpl implements IZhiHuDetailPresenter {

    public IZhiHuDetailView mView;
    public ZhiHuDailyModelImpl mModel;

    public ZhiHuDetailPresenterImpl(IZhiHuDetailView view) {
        mView = view;
        mModel = new ZhiHuDailyModelImpl();
    }

    @Override
    public void getZhiHuDetail(String id) {
        Subscription subscribe = mModel.getZhiHuStory(id)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showProgressDialog();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhihuStory>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hidenProgressDialog();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ZhihuStory zhihuStory) {
                        mView.loadZhiHuDetail(zhihuStory);
                        mView.hidenProgressDialog();
                    }
                });
        
        addSubscription(subscribe);
    }
}
