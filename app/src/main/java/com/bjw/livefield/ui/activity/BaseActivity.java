package com.bjw.livefield.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bjw.livefield.utils.SystemBarTintManager;

/**
 * author: Administrator
 * created on: 2016/9/23 0023 19:08
 * description:基类Activity
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //6.0及以上系统切换至白底黑字状态栏
        SystemBarTintManager.myStatusBar(this);
    }
}
