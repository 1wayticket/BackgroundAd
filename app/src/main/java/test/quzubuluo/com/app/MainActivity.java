package test.quzubuluo.com.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import test.quzubuluo.com.app.utils.LogUtil;
import test.quzubuluo.com.app.utils.ToastUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean keyBackFlag;
    private Button btnStart;
    private ScreenLockReceiver screenLockReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnStart = (Button) findViewById(R.id.btn_start_lock_ad);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (keyBackFlag) {
            super.onBackPressed();
            return;
        } else {
            Toast toast = Toast.makeText(this, "再次点击退出", Toast.LENGTH_LONG);
            toast.show();
            keyBackFlag = true;
            Observable.timer(1000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            keyBackFlag = false;
                        }
                    });
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_lock_ad:
                ToastUtils.showShort("正在开启中...");
                btnStart.setOnClickListener(null);
                screenLockReceiver = new ScreenLockReceiver();
                IntentFilter filter = new IntentFilter();
                filter.addAction(Intent.ACTION_SCREEN_OFF);
                filter.addAction(Intent.ACTION_SCREEN_ON);
                registerReceiver(screenLockReceiver, filter);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (screenLockReceiver!=null) {
            unregisterReceiver(screenLockReceiver);
        }
    }

    public class ScreenLockReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isScreenOff = false;
            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                isScreenOff = true;
                LogUtil.d("screen off");
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                isScreenOff = false;
                LogUtil.d("screen on");
            }
        }
    }
}
