package com.bjw.livefield.api;

import com.bjw.livefield.bean.ZhiHuDaily;
import com.bjw.livefield.bean.ZhihuStory;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * author: Administrator
 * created on: 2016/9/29 0029 20:44
 * description:
 */
public interface ZhiHuService {
    @GET("/api/4/news/latest")
    Observable<ZhiHuDaily> getLastDaily();

    @GET("/api/4/news/before/{date}")
    Observable<ZhiHuDaily> getTheDaily(@Path("date") String date);

    @GET("/api/4/news/{id}")
    Observable<ZhihuStory> getZhihuStory(@Path("id") String id);
}
