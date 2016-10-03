package com.bjw.livefield.api;

import com.bjw.livefield.bean.ZhiHuDaily;

import retrofit2.http.GET;
import rx.Observable;

/**
 * author: Administrator
 * created on: 2016/9/29 0029 20:44
 * description:
 */
public interface ZhiHuService {
    @GET("/api/4/news/latest")
    Observable<ZhiHuDaily> getLastDaily();
}
