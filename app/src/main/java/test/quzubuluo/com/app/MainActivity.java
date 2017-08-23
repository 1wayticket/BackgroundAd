package test.quzubuluo.com.app;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import test.quzubuluo.com.app.lock.LockService;
import test.quzubuluo.com.app.utils.LogUtil;
import test.quzubuluo.com.app.utils.ToastUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean keyBackFlag;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnStart = (Button) findViewById(R.id.btn_start_lock_ad);
        btnStart.setOnClickListener(this);
        if (LockService.hasRegister) {
            btnStart.setText("关闭锁屏广告");
        } else {
            btnStart.setText("开启锁屏广告");
        }
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
                Intent service = new Intent(this, LockService.class);
                if (!LockService.hasRegister) {
                    startService(service);
                    ToastUtils.showShort("正在开启中...");
                    btnStart.setText("关闭锁屏广告");
                } else {
                    btnStart.setText("开启锁屏广告");
                    stopService(service);
                }
                break;
        }
    }


}
