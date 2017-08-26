package com.quzubuluo.quzulock.utils;

import android.content.Context;
import android.widget.Toast;

import com.quzubuluo.quzulock.App;


/**
 * @author kevin
 *         Toast工具类
 */
public class ToastUtils {

    private static final Context context;
    private static Toast toast;

    static {
        context = App.getInstance().getApplicationContext();
    }

    /**
     * 短时间显示  Toast
     *
     * @param sequence
     */
    public static void showShort(CharSequence sequence) {
        if (toast == null) {
            toast = Toast.makeText(context, sequence, Toast.LENGTH_SHORT);

        } else {
            toast.setText(sequence);
        }
        toast.show();
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(int message) {
        if (null == toast) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        if (null == toast) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(int message) {
        if (null == toast) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 自定义显示时间
     *
     * @param sequence
     * @param duration
     */
    public static void show(CharSequence sequence, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, sequence, duration);
        } else {
            toast.setText(sequence);
        }
        toast.show();

    }

    /**
     * 隐藏toast
     */
    public static void hideToast() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
