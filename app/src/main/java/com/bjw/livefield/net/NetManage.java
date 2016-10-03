package com.bjw.livefield.net;

import com.bjw.livefield.MyApplication;
import com.bjw.livefield.api.ZhiHuService;
import com.bjw.livefield.utils.NetWorkUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * description:网络访问封装类
 * author: Administrator
 * createTime: 2016/3 0003 15:45
 */
public class NetManage {
    private static NetManage sManage;
    private ZhiHuService mZhiHuService;
    /**
     * 自定义拦截器及有无网络的缓存过期时间
     */
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (NetWorkUtil.isNetWorkAvailable(MyApplication.getContext())) {
                int maxAge = 60; // 在线缓存在1分钟内可读取
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

    private static File httpCacheDirectory = new File(MyApplication
            .getContext().getCacheDir(), "zhihuCache");
    private static int cacheSize = 1024 * 1024 * 10;
    private static Cache sCache = new Cache(httpCacheDirectory, cacheSize);
    private static OkHttpClient sClient = new OkHttpClient.Builder()
            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .cache(sCache)
            .build();

    private static final String ZHI_HU_BASE_URL = "http://news-at.zhihu.com";

    public static NetManage getInstance() {
        if (sManage == null) {
            synchronized (NetManage.class) {
                if (sManage == null) {
                    sManage = new NetManage();
                }
            }
        }
        return sManage;
    }


    public ZhiHuService getZhiHuService() {
        if (mZhiHuService == null) {
            synchronized (NetManage.class) {
                if (mZhiHuService == null) {
                    mZhiHuService = new Retrofit.Builder()
                            .baseUrl(ZHI_HU_BASE_URL)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(sClient)
                            .build().create(ZhiHuService.class);
                }
            }
        }
        return mZhiHuService;
    }
}
