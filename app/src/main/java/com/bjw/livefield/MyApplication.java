package com.bjw.livefield;

import android.app.Application;
import android.content.Context;

/**
 * author: Administrator
 * created on: 2016/9/29 0029 19:22
 * description:
 */
public class MyApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
