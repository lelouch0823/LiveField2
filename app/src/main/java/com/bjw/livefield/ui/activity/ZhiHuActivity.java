package com.bjw.livefield.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ZhiHuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_zhihu);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ZhiHuActivity.class);
        context.startActivity(starter);
    }
}
