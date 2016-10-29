package com.bjw.livefield.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.bjw.livefield.R;
import com.bjw.livefield.presenter.impl.ZhiHuPresenterImpl;
import com.bjw.livefield.ui.fragment.ZhiHuFragment;

import butterknife.BindView;

public class MainActivity extends SingleFragmentActivity {


    public ZhiHuFragment mZhiHuFragment;
    @BindView(R.id.tb_main)
    Toolbar mToolbar;

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mToolbar.setNavigationIcon(R.drawable.ic_menu_black_36dp);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected Fragment createFragment() {

        if (mZhiHuFragment == null) {
            mZhiHuFragment = ZhiHuFragment.newInstance();
        }
        ZhiHuPresenterImpl presenter = new ZhiHuPresenterImpl(this, mZhiHuFragment);
        mZhiHuFragment.setPresenter(presenter);
        return mZhiHuFragment;
    }


    /**
     * 开启本Activity的方法
     *
     * @param context 开启本Activity的上下文
     */
    public static void start(Context context) {

        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

}
