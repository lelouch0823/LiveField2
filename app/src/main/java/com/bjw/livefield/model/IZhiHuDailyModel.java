package com.bjw.livefield.model;

import com.bjw.livefield.bean.ZhiHuDaily;

import rx.Observable;

/**
 * author: Administrator
 * created on: 2016/9/29 0029 20:05
 * description:
 */
public interface IZhiHuDailyModel {
    Observable<ZhiHuDaily> getLastZhiHuDaily();
}
