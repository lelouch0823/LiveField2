package com.bjw.livefield.model.impl;

import com.bjw.livefield.api.ZhiHuService;
import com.bjw.livefield.bean.ZhiHuDaily;
import com.bjw.livefield.bean.ZhihuStory;
import com.bjw.livefield.model.IZhiHuDailyModel;
import com.bjw.livefield.net.NetManage;

import rx.Observable;

/**
 * author: Administrator
 * created on: 2016/10/1 0001 20:45
 * description:
 */
public class ZhiHuDailyModelImpl implements IZhiHuDailyModel {
/*    @Inject
    public ZhiHuDailyModelImpl() {
    }*/

    @Override
    public Observable<ZhiHuDaily> getLastZhiHuDaily() {
        ZhiHuService zhiHuService = NetManage.getInstance().getZhiHuService();
        return zhiHuService.getLastDaily();
    }

    @Override
    public Observable<ZhiHuDaily> getCurrentZhiHuDaily(String date) {
        ZhiHuService zhiHuService = NetManage.getInstance().getZhiHuService();
        return zhiHuService.getTheDaily(date);
    }

    @Override
    public Observable<ZhihuStory> getZhiHuStory(String id) {
        Observable<ZhihuStory> zhihuStory = NetManage.getInstance().getZhiHuService()
                .getZhihuStory(id);
        return zhihuStory;
    }

}
