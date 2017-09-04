package com.quzubuluo.quzulock.view;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
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
    private View captureView;
    private int MAX_SLIDE_DISTANCE = 150;//解锁所需的最大滑动距离 单位:dp
    private IUnlock iUnlock;
    private int distance;
    private float rawY;
    private TranslateAnimation animation;

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
        rawY = 0;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    private void startAnim(final int distance) {
        animation = new TranslateAnimation(0, 0, 0, distance);
        animation.setDuration(200);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                captureView.scrollTo(0, 0);
                captureView.setAlpha(1f);
                captureView.startAnimation(new TranslateAnimation(0, 0, 0, 0));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        captureView.startAnimation(animation);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rawY = event.getRawY();
                distance = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                float moveRawY = event.getRawY();
                if (event.getHistorySize() > 0) {
                    if (Math.abs(event.getHistoricalX(0) - event.getRawX()) * 2 > Math.abs(event.getHistoricalY(0) - event.getRawY())) {
                        break;
                    }
                }
                distance = (int) ((rawY - moveRawY) * event.getYPrecision());
                captureView.scrollTo(0, distance);
                float mAlpha = 1f - distance * 1.0f / DensityUtils.dip2px(context, MAX_SLIDE_DISTANCE);
                if (mAlpha >= 0 && mAlpha <= 1) {
                    captureView.setAlpha(mAlpha);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                rawY = 0;
                break;
            case MotionEvent.ACTION_UP:
                if (DensityUtils.px2dip(context, distance) > MAX_SLIDE_DISTANCE) {
                    unlock();
                } else {
                    startAnim(distance);
                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    private void unlock() {
        iUnlock.unLock();
    }

}
