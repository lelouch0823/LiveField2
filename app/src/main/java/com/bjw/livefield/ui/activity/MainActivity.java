package com.bjw.livefield.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.bjw.livefield.R;

public class MainActivity extends SingleFragmentActivity {


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected Fragment createFragment() {
        return null;
    }



    /**
     * 开启本Activity的方法
     * @param context  开启本Activity的上下文
     */
    public static void start(Context context) {

        Intent starter = new Intent(context, ZhiHuActivity.class);
        context.startActivity(starter);
    }
}
