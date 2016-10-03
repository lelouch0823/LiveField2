package com.bjw.livefield.ui.activity;

import android.os.Bundle;

import com.bjw.livefield.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * author: Administrator
 * created on: 2016/9/23 0023 19:19
 * description:
 */
public class SplashActivity extends BaseActivity {

    /**
     * 延迟2秒显示内容
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        MainActivity.start(SplashActivity.this);
                        finish();
                    }
                });
    }
}
