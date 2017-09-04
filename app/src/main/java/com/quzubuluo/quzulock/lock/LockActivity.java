package com.quzubuluo.quzulock.lock;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.quzubuluo.quzulock.R;
import com.quzubuluo.quzulock.utils.BarUtils;
import com.quzubuluo.quzulock.utils.LogUtil;
import com.quzubuluo.quzulock.view.UnlockView;

import java.util.ArrayList;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGABannerUtil;


public class LockActivity extends AppCompatActivity implements IUnlock {

    private UnlockView rvParent;
    private ImageView ivUnlock;
    private BGABanner bannerAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BarUtils.setStatusBar4ImageView(this, null);
        super.onCreate(savedInstanceState);
        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//解除系统锁屏
        setContentView(R.layout.activity_lock);
        initView();
    }

    private void initView() {
        rvParent = (UnlockView) findViewById(R.id.rv_parent);
        ivUnlock = (ImageView) findViewById(R.id.iv_unlock);
        rvParent.setCaptureView(ivUnlock);
        bannerAd = (BGABanner) findViewById(R.id.banner_ad);
        ArrayList<View> ads = new ArrayList<>();
        ads.add(BGABannerUtil.getItemImageView(this, R.drawable.banner0));
        ads.add(BGABannerUtil.getItemImageView(this, R.drawable.banner1));
        ads.add(BGABannerUtil.getItemImageView(this, R.drawable.banner2));
        ads.add(BGABannerUtil.getItemImageView(this, R.drawable.banner3));
        ads.add(BGABannerUtil.getItemImageView(this, R.drawable.banner4));
        ads.add(BGABannerUtil.getItemImageView(this, R.drawable.banner5));
        bannerAd.setData(ads);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    public void unLock() {
        finish();
    }
}
