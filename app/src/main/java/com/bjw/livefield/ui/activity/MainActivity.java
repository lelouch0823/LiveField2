package com.bjw.livefield.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

import com.bjw.livefield.R;
import com.bjw.livefield.presenter.impl.ZhiHuPresenterImpl;
import com.bjw.livefield.ui.fragment.ZhiHuFragment;

public class MainActivity extends SingleFragmentActivity implements ZhiHuFragment.OnFragmentInteractionListener{



    public ZhiHuFragment mZhiHuFragment;

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
     * @param context  开启本Activity的上下文
     */
    public static void start(Context context) {

        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public interface LoadingMoreItem {
        void loadFinish();

        void loadStart();
    }
}
