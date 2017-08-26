package com.quzubuluo.quzulock.lock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.quzubuluo.quzulock.R;
import com.quzubuluo.quzulock.utils.BarUtils;
import com.quzubuluo.quzulock.view.UnlockView;


public class LockActivity extends AppCompatActivity implements IUnlock {

    private UnlockView rvParent;
    private ImageView ivUnlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BarUtils.setTransparentStatusBar(this);
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
