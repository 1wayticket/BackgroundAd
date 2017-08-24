package test.quzubuluo.com.app.lock;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import test.quzubuluo.com.app.utils.LogUtil;
import test.quzubuluo.com.app.utils.ToastUtils;

/**
 * author：Teemo
 * time：2017/8/23 15:14
 * email：cluchao@163.com
 * description：
 */

public class LockService extends Service {
    private ScreenLockReceiver screenLockReceiver;
    public static boolean hasRegister = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("onCreate");
        screenLockReceiver = new ScreenLockReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(screenLockReceiver, filter);
        ToastUtils.showShort("开启成功!");
        hasRegister = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d("onStartCommand");
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("onDestroy");
        ToastUtils.showShort("关闭后台广告!");
        hasRegister = false;
        if (screenLockReceiver != null) {
            unregisterReceiver(screenLockReceiver);
        }
    }

    public class ScreenLockReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_SCREEN_OFF)) {
                LogUtil.d("screen off");
            } else if (action.equals(Intent.ACTION_SCREEN_ON)) {
                LogUtil.d("screen on");
                Intent lockIntent = new Intent(LockService.this, LockActivity.class);
                lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(lockIntent);
            } else if (action.equals("android.intent.action.BOOT_COMPLETED")) {
                LogUtil.d("开机启动");
                Intent service = new Intent(context, LockService.class);
                startService(service);
            }
        }
    }

}
