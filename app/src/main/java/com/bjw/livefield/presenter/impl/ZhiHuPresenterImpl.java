package com.bjw.livefield.presenter.impl;

import android.content.Context;

import com.bjw.livefield.bean.ZhiHuDaily;
import com.bjw.livefield.bean.ZhihuDailyItem;
import com.bjw.livefield.global.Config;
import com.bjw.livefield.model.impl.ZhiHuDailyModelImpl;
import com.bjw.livefield.presenter.IZhiHuPresenter;
import com.bjw.livefield.ui.view.implView.IZhiHuView;
import com.bjw.livefield.utils.CacheUtil;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * author: Administrator
 * created on: 2016/9/23 0023 18:54
 * description:知乎的presenter
 */
public class ZhiHuPresenterImpl extends BasePresenterImpl implements IZhiHuPresenter {

    private IZhiHuView mIZhiHuView;
    private CacheUtil mCacheUtil;
    private ZhiHuDailyModelImpl mModel;
    private Gson mGson = new Gson();


    /**
     * 初始化方法并完成缓存工具类,View,Model的初始化.
     *
     * @param context    the context
     * @param IZhiHuView the zhi hu view
     */
    public ZhiHuPresenterImpl(Context context, IZhiHuView IZhiHuView) {
        mCacheUtil = CacheUtil.get(context);
        mIZhiHuView = IZhiHuView;
        mModel = new ZhiHuDailyModelImpl();
    }

    @Override
    public void getLastZhiHuNews() {
        mIZhiHuView.showProgressDialog();
        Subscription subscribe = mModel.getLastZhiHuDaily()
        //Subscription subscribe = NetManage.getInstance().getZhiHuService().getLastDaily()
                .map(new Func1<ZhiHuDaily, ZhiHuDaily>() {
                    @Override
                    public ZhiHuDaily call(ZhiHuDaily daily) {
                        String date = daily.getDate();
                        Logger.i(date);
                        ArrayList<ZhihuDailyItem> stories = daily.getStories();
                        for (ZhihuDailyItem story : stories) {
                            story.setDate(date);
                        }
                        return daily;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhiHuDaily>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mIZhiHuView.hidenProgressDialog();
                        mIZhiHuView.showError(e.getMessage());
                        Logger.e(e,"错误","的");
                    }

                    @Override
                    public void onNext(ZhiHuDaily daily) {
                        mIZhiHuView.hidenProgressDialog();
                        mCacheUtil.put(Config.ZHIHU, mGson.toJson(daily));
                        mIZhiHuView.updateList(daily);
                    }
                });
        addSubscription(subscribe);
    }

    @Override
    public void getZhiHuDaily(String date) {

    }

    @Override
    public void getLastZhiHuFromCache() {
        if (mCacheUtil != null ) {
            JSONObject object = mCacheUtil.getAsJSONObject(Config.ZHIHU);
            if (object != null) {
                ZhiHuDaily daily = mGson.fromJson(object.toString(), ZhiHuDaily.class);
                mIZhiHuView.updateList(daily);
            }
        }
    }
}
