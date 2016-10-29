package com.bjw.livefield.presenter.impl;

import android.content.Context;

import com.bjw.livefield.MyApplication;
import com.bjw.livefield.baserx.RxCache;
import com.bjw.livefield.baserx.RxSchedulers;
import com.bjw.livefield.bean.ZhiHuDaily;
import com.bjw.livefield.bean.ZhihuDailyItem;
import com.bjw.livefield.constant.AppConstant;
import com.bjw.livefield.global.Config;
import com.bjw.livefield.model.impl.ZhiHuDailyModelImpl;
import com.bjw.livefield.presenter.IZhiHuPresenter;
import com.bjw.livefield.ui.view.implView.IZhiHuView;
import com.bjw.livefield.utils.CacheUtil;
import com.bjw.livefield.utils.LogUtils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * author: Administrator
 * created on: 2016/9/23 0023 18:54
 * description:知乎List的presenter
 */
public class ZhiHuPresenterImpl extends BasePresenterImpl implements IZhiHuPresenter {

    private IZhiHuView mIZhiHuView;
    private CacheUtil mCacheUtil;
    private ZhiHuDailyModelImpl mModel;
    private Gson mGson = new Gson();
    public Context mContext;


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
        mContext = context;
    }

    @Override
    public void getLastZhiHuNews() {
        mIZhiHuView.showProgressDialog();
        Subscription subscribe = mModel.getLastZhiHuDaily()
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
                        Logger.e(e,"错误");
                    }

                    @Override
                    public void onNext(ZhiHuDaily daily) {
                        mCacheUtil.put(Config.ZHIHU, mGson.toJson(daily));
                        mIZhiHuView.updateList(daily);
                        mIZhiHuView.hidenProgressDialog();
                    }
                });
        addSubscription(subscribe);
    }

    public void getZhiHuDailyOrCache(String date) {
        Observable<ZhiHuDaily> fromnet = mModel.getCurrentZhiHuDaily(date)
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
                .compose(RxSchedulers.<ZhiHuDaily>io_main());
        RxCache.load(MyApplication.getContext(), AppConstant.ZHI_HU_CACHE_KEY,
                AppConstant.CACHE_EXPIRE_TIME, fromnet, true)
                .subscribe(new Subscriber<ZhiHuDaily>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.loge("出错");
            }

            @Override
            public void onNext(ZhiHuDaily daily) {
                String date = daily.getDate();
                mIZhiHuView.onListLoadMore(daily);
                Logger.i(date);
                LogUtils.logd(date);
            }
        });
    }
    @Override
    public void getZhiHuDaily(String date) {
        Subscription subscription = mModel.getCurrentZhiHuDaily(date)
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
                .compose(RxSchedulers.<ZhiHuDaily>io_main())
                .subscribe(new Subscriber<ZhiHuDaily>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ZhiHuDaily daily) {
                        mIZhiHuView.onListLoadMore(daily);
                    }
                });
        addSubscription(subscription);
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
