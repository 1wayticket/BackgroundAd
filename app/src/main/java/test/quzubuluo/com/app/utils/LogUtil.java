package test.quzubuluo.com.app.utils;

import android.util.Log;

/**
 * Created by Administrator on 2017/7/17.
 * 打印log
 */
public class LogUtil {

    private static final String TAG = "quzu";

    public static void d(String msg) {
        if (msg == null || msg.isEmpty()) {
            Log.d(TAG, "msg is null or empty");
        } else {
            Log.d(TAG, msg);
        }
    }
}
