package com.bjw.livefield.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.bjw.livefield.R;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/7/15 0015.
 */
public abstract class SingleFragmentActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            manager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    protected abstract void initView();

    /**
     * 返回需要加载的布局的ID.
     *
     * @return the layout res id
     */
    @LayoutRes
    protected abstract int getLayoutResId();


    /**
     *返回需要加载的Fragment
     *
     * @return the fragment
     */
    protected abstract Fragment createFragment();

}
