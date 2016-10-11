package com.bjw.livefield.ui.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * description:
 * author: Administrator
 * createTime: 2016/10/9 0009 16:33
 */
public class SwipeRefreshView extends SwipeRefreshLayout {
    RecyclerView mRecyclerView;
    public float mDownY;
    public float mMoveY;
    public float mUpY;

    public SwipeRefreshView(Context context) {
        this(context, null);
    }

    public SwipeRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (getChildCount() != 0) {
            if (getChildAt(0) instanceof RecyclerView) {
                mRecyclerView = (RecyclerView) getChildAt(0);
                setRecyclerListener();
            }
        }
    }

    private void setRecyclerListener() {
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int itemCount = recyclerView.getAdapter().getItemCount();
                boolean isLast = newState == itemCount;
                boolean isPull = mDownY - mUpY >= -3;
                if (isLast && isPull) {

                }
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mMoveY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                mUpY = ev.getY();
                break;
            default :
            
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean canLoadMore() {
        int itemCount = mRecyclerView.getAdapter().getItemCount();
        boolean isPull = mDownY - mUpY >= -3;

        return false;
    }
}
