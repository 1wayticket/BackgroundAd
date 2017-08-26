package com.quzubuluo.quzulock.view;

import android.content.Context;
import android.graphics.Point;
import android.nfc.NdefRecord;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.quzubuluo.quzulock.lock.IUnlock;
import com.quzubuluo.quzulock.utils.DensityUtils;
import com.quzubuluo.quzulock.utils.LogUtil;

/**
 * author：Teemo
 * time：2017/8/26 11:43
 * email：cluchao@163.com
 * description：自定义View继承ReativeLayout通过ViewDragHelper实现滑动解锁功能
 */

public class UnlockView extends RelativeLayout {
    private Context context;
    private float mSensitivity = 1.0f;//ViewDragHelper的敏感度
    private ViewDragHelper mDragHelper;
    private View captureView;
    private Point captureViewPoints = new Point();
    private int MAX_SLIDE_DISTANCE = 180;//解锁所需的最大滑动距离 单位:dp
    private IUnlock iUnlock;

    public UnlockView(Context context) {
        this(context, null);
    }

    public UnlockView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnlockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        iUnlock = (IUnlock) context;
        initView();
    }

    public void setCaptureView(View captureView) {
        this.captureView = captureView;
    }

    private void initView() {
        mDragHelper = ViewDragHelper.create(this, mSensitivity, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                if (captureView == child) {
                    return true;
                }
                return false;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return 0;
//                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if (releasedChild == captureView) {
                    if (DensityUtils.px2dip(context, captureViewPoints.y - captureView.getTop()) > MAX_SLIDE_DISTANCE) {
                        unlock();
                    } else {
                        mDragHelper.settleCapturedViewAt(captureViewPoints.x, captureViewPoints.y);
                        invalidate();
                    }
                }
                super.onViewReleased(releasedChild, xvel, yvel);
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        captureViewPoints.x = captureView.getLeft();
        captureViewPoints.y = captureView.getTop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    private void unlock() {
        iUnlock.unLock();
    }

    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

}
